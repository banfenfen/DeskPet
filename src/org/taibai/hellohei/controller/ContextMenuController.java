package org.taibai.hellohei.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.state.TotalState;
import org.taibai.hellohei.ui.Action;
import org.taibai.hellohei.ui.ActionExecutor;
import org.taibai.hellohei.ui.InterfaceFunction;
import org.taibai.hellohei.ui.MainNode;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;


/**
 * <p>Creation Time: 2021-09-25 10:38:00</p>
 * <p>Description: 右键菜单的控制器</p>
 *
 * @author 太白
 */
public class ContextMenuController {

    /**
     * 点击按钮后应当隐藏一级菜单
     */
    private Stage preStage;
    /**
     * 打开的菜单左上角X坐标，为了打开二级菜单
     */
    private double screenX;
    /**
     * 打开的菜单左上角Y坐标，为了打开二级菜单
     */
    private double screenY;

    private final Stage stage = MainNode.getInstance().getStage();
    private final ActionExecutor actionExecutor = ActionExecutor.getInstance();

    public void Init(Stage stage, double screenX, double screenY) {
        this.preStage = stage;
        this.screenX = screenX;
        this.screenY = screenY;
    }

    @FXML
    public void eat() {
        preStage.close();
        showItemsWindow(ItemsWindowController.FoodTitle);
    }

    @FXML
    public void bath() {
        preStage.close();
        showItemsWindow(ItemsWindowController.BathTitle);
    }

    @FXML
    public void drug() {
        preStage.close();
        showItemsWindow(ItemsWindowController.DrugTitle);
    }

    @FXML
    public void sleep() {
        preStage.close();
        setSleepState();
    }

    @FXML
    public void study() {
        preStage.close();
        setStudyState();
    }


    private void showItemsWindow(String title) {
        // ====== 设置名义上的stage，避免在任务栏生成一个小窗口 ======
        final Stage nominalStage = new Stage();
        nominalStage.initStyle(StageStyle.UTILITY);
        nominalStage.setOpacity(0);
        final Stage stage = new Stage();
        stage.initOwner(nominalStage);
        stage.initStyle(StageStyle.TRANSPARENT);    // 设定窗口透明且无边框
        stage.setAlwaysOnTop(true);                 // 设置窗口总显示在最前

        // ====== 设置菜单出现的位置，默认出现在光标的右下方，但是有两种超出屏幕边缘的情况 ======
        stage.setX(screenX);
        stage.setY(screenY);

        // ====== 获得fxml文件 ======
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/taibai/hellohei/fxml/ItemsWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ====== 获得控制器实例 ======
        ItemsWindowController controller = loader.getController();   //获取Controller的实例对象
        controller.Init(title, stage);

        // ====== 在stage中装入scene，并为scene设置css样式 ======
        Scene scene = new Scene(Objects.requireNonNull(root));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("/org/taibai/hellohei/fxml/ItemsWindow.css")).toExternalForm());

        // ====== 当失去焦点的时候设置隐藏stage ======
        stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!stage.isFocused()) {
                stage.close();
            }
        });

        // ====== 展示菜单 ======
        nominalStage.show();
        stage.show();
    }

    private void setSleepState() {
        if(!TotalState.getInstance().getStaminaState().canIncrease())
        {
            InterfaceFunction.getInstance().say("睡什么睡起来嗨！", Constant.UserInterface.SayingRunTime);
            return;
        }
        InterfaceFunction.getInstance().say("遇到困难睡大觉zzz", Constant.UserInterface.SayingRunTime);
        /*Action action = Action.creatTemporaryUninterruptibleAction(
                "/org/taibai/hellohei/img/before sleep.gif",
                Constant.UserInterface.ActionRunTime,
                "/org/taibai/hellohei/img/sleeping.gif"
        );
        ActionExecutor.getInstance().execute(action);*/
        Action action = Action.creatTemporaryUninterruptibleAction(
                "/org/taibai/hellohei/img/sleeping.gif",
                Constant.UserInterface.ActionRunTime*5,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
        // 增加体力值
        TotalState.getInstance().getStaminaState().increase(30);
    }

    private void setStudyState() {
        InterfaceFunction.getInstance().say("啊~~~学不动了", Constant.UserInterface.SayingRunTime);
        Action action = Action.creatTemporaryUninterruptibleAction(
                "/org/taibai/hellohei/img/die.gif",
                Constant.UserInterface.ActionRunTime,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
    }

    public void hide() {
        Platform.setImplicitExit(false);
        Platform.runLater(stage::hide);
    }

    public void exit() {
        preStage.close();
        InterfaceFunction.getInstance().exit();
    }
}

package org.taibai.hellohei.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.items.Shop.Shopitem;
import org.taibai.hellohei.items.bath.BathItem;
import org.taibai.hellohei.items.drug.DrugItem;
import org.taibai.hellohei.items.food.FoodItem;
import org.taibai.hellohei.items.ItemWarehouse;
import org.taibai.hellohei.items.work.WorkItem;
import org.taibai.hellohei.state.TotalState;
import org.taibai.hellohei.ui.Action;
import org.taibai.hellohei.ui.ActionExecutor;
import org.taibai.hellohei.ui.InterfaceFunction;

import java.util.Map;

/**
 *
 * <p>Description: 货物列表控制器，该窗口用于展示食物、洗澡用品、打工列表</p>
 *
 * @author
 */
public class ItemsWindowController {

    public static final String FoodTitle = "食物仓库";
    public static final String BathTitle = "沐浴仓库";
    public static final String DrugTitle = "药品仓库";
    public static final String ShopTitle = "商店";
    public static final String WorkTitle = "打工";
    public static final String FindTitle = "钱包";

    @FXML
    public Label title;
    private String titleText;
    @FXML
    public Pane itemPane;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public VBox vbox;
    private Stage stage;

    public void Init(String title, Stage stage) {
        this.stage = stage;
        this.titleText = title;
        this.title.setText(title);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        // 禁用左右滚轴
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loadItems();
    }

    private void loadItems() {
        switch (titleText) {
            case FoodTitle:
                loadFoodItems();
                break;
            case BathTitle:
                loadBathItems();
                break;
            case DrugTitle:
                loadDrugItems();
                break;
            case ShopTitle:
                loadShopItems();
                break;
            case WorkTitle:
                loadWorkItems();
                break;
        }
    }

    private void loadFoodItems() {
        Map<String, FoodItem> foodItemList = ItemWarehouse.getInstance().getFoodItemMap();
        for (Map.Entry<String, FoodItem> entry : foodItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getStaminaState().canIncrease()) {
                stage.close();
            }
        }));
    }

    private void loadBathItems() {
        Map<String, BathItem> bathItemList = ItemWarehouse.getInstance().getBathItemMap();
        for (Map.Entry<String, BathItem> entry : bathItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getCleanlinessState().canIncrease()) {
                stage.close();
            }
        }));
    }

    private void loadShopItems() {
        Map<String, Shopitem> shopItemList = ItemWarehouse.getInstance().getShopitemMap();
        for (Map.Entry<String, Shopitem> entry : shopItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getCleanlinessState().canIncrease()) {
                stage.close();
            }
        }));
    }

    private void loadWorkItems() {
        Map<String, WorkItem> workItemList = ItemWarehouse.getInstance().getWorkItemMap();
        for (Map.Entry<String, WorkItem> entry : workItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getCleanlinessState().canIncrease()) {
                stage.close();
            }
        }));
   }


    private void loadDrugItems() {
        Map<String, DrugItem> drugItemList = ItemWarehouse.getInstance().getDrugItemMap();
        for (Map.Entry<String, DrugItem> entry : drugItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getHealthState().canIncrease()) {
                stage.close();
            }
        }));
    }

    /*private void setSleepState() {
        InterfaceFunction.getInstance().say("遇到困难睡大觉zzz", Constant.UserInterface.SayingRunTime);
        Action action = Action.creatTemporaryUninterruptibleAction(
                "/org/taibai/hellohei/img/before sleep.gif",
                Constant.UserInterface.ActionRunTime,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
        action = Action.creatTemporaryUninterruptibleAction(
                "/org/taibai/hellohei/img/sleeping.gif",
                Constant.UserInterface.ActionRunTime*20,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
        // 增加健康值
        TotalState.getInstance().getStaminaState().increase(30);
    }*/
}

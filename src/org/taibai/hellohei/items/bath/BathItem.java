package org.taibai.hellohei.items.bath;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.items.food.FoodEnum;
import org.taibai.hellohei.state.TotalState;
import org.taibai.hellohei.ui.Action;
import org.taibai.hellohei.ui.ActionExecutor;
import org.taibai.hellohei.ui.InterfaceFunction;

/**
 *
 * <p>Description: 洗澡的物品</p>
 *
 * @author
 */
public class BathItem {

    private BathEnum bathEnum;
    private int itemNum;
    private AnchorPane anchorPane;
    private Label label;

    public BathItem(BathEnum bathEnum, int itemNum) {
        this.bathEnum = bathEnum;
        this.itemNum = itemNum;
        init();
    }

    /**
     * 创建一个AnchorPane供以在ItemsWindow显示，同时要添加点击事件
     * 点击后将产生用该物品的动作、并且减去一个物品
     *
     * @return 创建出来的AnchorPane
     */
    public AnchorPane toItemAnchorPane() {
        label.setText(bathEnum.getName() + "*" + itemNum);
        return anchorPane;
    }

    /**
     * 每次只是个数的变化，所以不需要再次创建新的对象，否则会特别耗时
     */
    private void init() {
        anchorPane = new AnchorPane();
        ImageView imageView = new ImageView(ResourceGetter.getInstance().get(bathEnum.getPath()));
        imageView.setFitWidth(86);
        imageView.setFitHeight(86);
        label = new Label(bathEnum.getName() + "*" + itemNum);
        label.setLayoutX(0);
        label.setLayoutY(66);
        label.setMinWidth(86);
        label.setMinHeight(20);
        label.setAlignment(Pos.CENTER); //垂直水平居中
        label.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white");
        anchorPane.getChildren().addAll(imageView, label);
        anchorPane.setOnMousePressed(e -> useItem());
    }

    private void useItem() {
        if (itemNum <= 0) return;
        if (!TotalState.getInstance().getCleanlinessState().canIncrease()) {
            InterfaceFunction.getInstance().say("我已经很干净啦~", Constant.UserInterface.SayingRunTime);
            return;
        }
        decrease(1);
        Action action = Action.creatTemporaryInterruptableAction(
                bathEnum.getActionPath(),
                Constant.UserInterface.ActionRunTime,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
        InterfaceFunction.getInstance().say("我爱洗澡皮肤好好~", Constant.UserInterface.SayingRunTime);
        // 增加清洁度
        TotalState.getInstance().getCleanlinessState().increase(bathEnum.getBuff());
    }

    /**
     * 将该item数量增加num个
     *
     * @param num 增加的数量
     */
    public void increase(int num) {
        this.itemNum += num;
    }

    /**
     * 将该item数量减少num个
     *
     * @param num 减少的数量
     */
    public void decrease(int num) {
        this.itemNum -= num;
        itemNum = Math.max(0, itemNum);
    }

    /**
     * 得到该item还有多少个
     *
     * @return item的数量
     */
    public int getItemNum() {
        return itemNum;
    }

}

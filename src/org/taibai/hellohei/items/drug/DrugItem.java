package org.taibai.hellohei.items.drug;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.items.bath.BathEnum;
import org.taibai.hellohei.items.food.FoodEnum;
import org.taibai.hellohei.state.TotalState;
import org.taibai.hellohei.ui.Action;
import org.taibai.hellohei.ui.ActionExecutor;
import org.taibai.hellohei.ui.InterfaceFunction;

public class DrugItem {

    private DrugEnum drugEnum;
    private int itemNum;
    private AnchorPane anchorPane;
    private Label label;

    public DrugItem(DrugEnum drugEnum, int itemNum) {
        this.drugEnum = drugEnum;
        this.itemNum = itemNum;
        init();
    }
    public AnchorPane toItemAnchorPane() {
        label.setText(drugEnum.getName() + "*" + itemNum);
        return anchorPane;
    }
    private void init() {
        anchorPane = new AnchorPane();
        ImageView imageView = new ImageView(ResourceGetter.getInstance().get(drugEnum.getPath()));
        imageView.setFitWidth(86);
        imageView.setFitHeight(86);
        label = new Label(drugEnum.getName() + "*" + itemNum);
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
        if (!TotalState.getInstance().getHealthState().canIncrease()) {
            InterfaceFunction.getInstance().say("今天也是元气满满的一天！", Constant.UserInterface.SayingRunTime);
            return;
        }
        decrease(1);
        Action action = Action.creatTemporaryInterruptableAction(
                drugEnum.getActionPath(),
                Constant.UserInterface.ActionRunTime,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
        InterfaceFunction.getInstance().say("我要快快好起来！", Constant.UserInterface.SayingRunTime);
        // 增加健康值
        TotalState.getInstance().getHealthState().increase(drugEnum.getBuff());

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

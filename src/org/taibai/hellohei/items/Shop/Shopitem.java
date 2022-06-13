package org.taibai.hellohei.items.Shop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.items.ItemWarehouse;
import org.taibai.hellohei.items.food.FoodEnum;
import org.taibai.hellohei.state.TotalState;
import org.taibai.hellohei.ui.Action;
import org.taibai.hellohei.ui.ActionExecutor;
import org.taibai.hellohei.ui.InterfaceFunction;

public class Shopitem {

    private ShopEnum shopEnum;
    private int itemNum;
    private int price;
    private AnchorPane anchorPane;
    private Label label;

    public Shopitem(ShopEnum shopEnum, int itemNum,int price) {
        this.shopEnum = shopEnum;
        this.itemNum = itemNum;
        this.price=price;
        init();
    }

    /**
     * 创建一个AnchorPane供以在ItemsWindow显示，同时要添加点击事件
     * 点击后将产生用该物品的动作、并且减去一个物品
     *
     * @return 创建出来的AnchorPane
     */
    public AnchorPane toItemAnchorPane() {
        label.setText(shopEnum.getName() + "*" + itemNum);
        return anchorPane;
    }

    /**
     * 每次只是个数的变化，所以不需要再次创建新的对象，否则会特别耗时
     */
    private void init() {
        anchorPane = new AnchorPane();
        ImageView imageView = new ImageView(ResourceGetter.getInstance().get(shopEnum.getPath()));
        imageView.setFitWidth(86);
        imageView.setFitHeight(86);
        label = new Label(shopEnum.getName() + "*" + itemNum);
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
        if (itemNum <= 0) {
            InterfaceFunction.getInstance().say("该商品卖光啦~~", Constant.UserInterface.SayingRunTime);
            return;
        }
       if (TotalState.getInstance().getMoneyState().getTotalMoney()<price) {
            InterfaceFunction.getInstance().say("您的余额不足哦，快去打工赚钱吧~~", Constant.UserInterface.SayingRunTime);
            return;
        }
        decrease(1);
       if(shopEnum.getType()==1){
           ItemWarehouse.getInstance().getBathItemMap().get(shopEnum.getId()).increase(1);
       }
       else if(shopEnum.getType()==2){
           ItemWarehouse.getInstance().getFoodItemMap().get(shopEnum.getId()).increase(1);
       }
        TotalState.getInstance().getMoneyState().reduce(price);
        Action action = Action.creatTemporaryInterruptableAction(
                shopEnum.getActionPath(),
                Constant.UserInterface.ActionRunTime * 2,
                Constant.ImageShow.mainImage
        );
        ActionExecutor.getInstance().execute(action);
        InterfaceFunction.getInstance().say("又有好东西喽，钱包里还有"+TotalState.getInstance().getMoneyState().toString()+"元呢~ ",Constant.UserInterface.SayingRunTime);
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

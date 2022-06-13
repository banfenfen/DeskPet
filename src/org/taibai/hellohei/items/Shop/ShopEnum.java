package org.taibai.hellohei.items.Shop;

import org.taibai.hellohei.constant.Constant;

public enum ShopEnum {

    SOAP("肥皂", "BATH_001", "bath/soap.png", 10, "licking the claw.gif",1),
    EGG("鸡蛋", "FOOD_001", "foods/egg.png", 10, "eat drumstick.gif",2),
    MILK("牛奶", "FOOD_002", "foods/milk.png", 5, "eat drumstick.gif",2);

    /**
     * 商品名称
     */
    private final String name;
    /**
     * 商品的唯一性ID
     */
    private final String id;
    /**
     * 商品的图片资源路径
     */
    private final String path;
    /**
     * 买一个需要花费多少钱
     */
    private final int buff;
    /**
     * 图片资源路径
     */
    private final String actionPath;

    private final int type;

    public static final String pathPrefix = "/org/taibai/hellohei/img/";

    ShopEnum(String name, String id, String path, int buff, String actionPath,int type) {
        this.name = name;
        this.id = id;
        this.path = path;
        this.buff = buff;
        this.actionPath = actionPath;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return pathPrefix + path;
    }

    public int getBuff() {
        return buff;
    }

    public String getActionPath() {
        return pathPrefix + actionPath;
    }

    public int getType() {
        return type;
    }
}

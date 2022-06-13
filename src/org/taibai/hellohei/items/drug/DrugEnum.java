package org.taibai.hellohei.items.drug;
import org.taibai.hellohei.constant.Constant;

public enum DrugEnum {
    //CAPSULE("胶囊","DRUG_001",Constant.ImageShow.ItemImage.capsuleImage,10,Constant.ImageShow.havingCapsuleImage),
    //TRANSFUSION("输液","DRUG_002",Constant.ImageShow.ItemImage.transfusionImage,20,Constant.ImageShow.havingTransfusionImage);
    CAPSULE("胶囊","DRUG_001","drugs/capsule.png",10,"drinking water.gif"),
    TRANSFUSION("输液","DRUG_002","drugs/transfusion.png",20,"transfusion.gif");
    /**
     * 药品名称
     */
    private final String name;
    /**
     * 药品的唯一性ID
     */
    private final String id;
    /**
     * 药品的图片资源路径
     */
    private final String path;
    /**
     * 用一个这个可以增加多少健康值
     */
    private final int buff;
    /**
     * 吃药的图片资源路径
     */
    private final String actionPath;
    public static final String pathPrefix = "/org/taibai/hellohei/img/";

    DrugEnum(String name, String id, String path, int buff, String actionPath) {
        this.name = name;
        this.id = id;
        this.path = path;
        this.buff = buff;
        this.actionPath = actionPath;
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


}

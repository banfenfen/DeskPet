package org.taibai.hellohei.items.work;

public enum WorkEnum {

    Sweep("扫地","WORK_001","works/sweep.jpg",10,"sweep.gif"),
    Fortune("招财","WORK_002","works/fortune.jpg",10,"fortune_matting.gif");

    /**
     * 工作名称
     */
    private final String name;
    /**
     * 工作的唯一性ID
     */
    private final String id;
    /**
     * 工作的图片资源路径
     */
    private final String path;
    /**
     * 做一次赚多少钱
     */
    private final int buff;
    /**
     * 打工的图片资源路径
     */
    private final String actionPath;

    public static final String pathPrefix = "/org/taibai/hellohei/img/";

    WorkEnum(String name, String id, String path, int buff, String actionPath) {
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

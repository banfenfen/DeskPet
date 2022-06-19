package org.taibai.hellohei.state;

/**
 *
 * <p>Description: 清洁度状态</p>
 *
 * @author
 */
public class CleanlinessState {

    private int cleanliness = 60;

    public static final int Reduce_Step = 1;
    public static final int Max_Value = 100;
    public static final int Min_Value = 0;

    public int getCleanliness() {
        return cleanliness;
    }

    /**
     * 清洁度降低
     */
    public void reduce() {
        cleanliness = Math.max(Min_Value, cleanliness - Reduce_Step);
    }

    /**
     * 清洁度增加
     *
     * @param num 增加的量
     */
    public void increase(int num) {
        cleanliness = Math.min(Max_Value, cleanliness + num);
        System.out.printf("[CleanlinessState::increase(%d)]-当前清洁度=%d\n", num, cleanliness);
    }

    /**
     * 是否还能增加
     *
     * @return 能否增加
     */
    public boolean canIncrease() {
        return cleanliness < Max_Value;
    }

}

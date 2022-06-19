package org.taibai.hellohei.state;

public class HealthState {
    private int health = 60;

    public static final int Reduce_Step = 1;
    public static final int Max_Value = 100;
    public static final int Min_Value = 0;

    public int getHealth() {
        return health;
    }

    /**
     * 健康值降低
     */
    public void reduce() {
        health = Math.max(Min_Value, health - Reduce_Step);
    }

    /**
     * 健康值增加
     *
     * @param num 增加的量
     */
    public void increase(int num) {
        health = Math.min(Max_Value, health + num);
        System.out.printf("[StaminaState::increase(%d)]-当前健康值=%d\n", num, health);
    }

    /**
     * 是否还能增加
     *
     * @return 能否增加
     */
    public boolean canIncrease() {
        return health < Max_Value;
    }
}

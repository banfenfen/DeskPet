package org.taibai.hellohei.state;

/**
 *
 * <p>Description: 体力值</p>
 *
 * @author
 */
public class StaminaState {

    private int stamina = 60;

    public static final int Reduce_Step = 1;
    public static final int Max_Value = 100;
    public static final int Min_Value = 0;

    public int getStamina() {
        return stamina;
    }

    /**
     * 体力值降低
     */
    public void reduce() {
        stamina = Math.max(Min_Value, stamina - Reduce_Step);
    }

    /**
     * 体力值增加
     *
     * @param num 增加的量
     */
    public void increase(int num) {
        stamina = Math.min(Max_Value, stamina + num);
        System.out.printf("[StaminaState::increase(%d)]-当前体力值=%d\n", num, stamina);
    }

    /**
     * 体力值减少
     *
     * @param num 减少的量
     */
    public void decrease(int num) {
        stamina = Math.max(0, stamina - num);
        System.out.printf("[StaminaState::decrease(%d)]-当前体力值=%d\n", num, stamina);
    }

    /**
     * 是否还能增加
     *
     * @return 能否增加
     */
    public boolean canIncrease() {
        return stamina < Max_Value;
    }

    /**
     * 是否还能减少
     *
     * @return 能否减少
     */
    public boolean canDecrease() {
        return stamina == 0;
    }
}

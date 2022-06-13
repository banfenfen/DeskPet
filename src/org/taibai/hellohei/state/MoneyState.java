package org.taibai.hellohei.state;

public class MoneyState {

    private int TotalMoney = 60;


    public int getTotalMoney(){
        return TotalMoney;
    }

    public String toString(){
        return Integer.toString(TotalMoney);
    }
    /**
     * 余额降低
     */
    public void reduce(int cost) {
        TotalMoney = TotalMoney - cost;
    }

    /**
     * 余额增加
     *
     * @param num 增加的量
     */
    public void increase(int num) {
        TotalMoney = TotalMoney + num;
        System.out.printf("[MoneyState::increase(%d)]-当前余额=%d\n", num, TotalMoney);
    }

    /**
     * 余额增加
     *
     * @param num 增加的量
     */


}

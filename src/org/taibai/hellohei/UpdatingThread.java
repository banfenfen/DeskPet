package org.taibai.hellohei;

import org.taibai.hellohei.state.TotalState;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UpdatingThread extends Thread{
    private Lock lock = new ReentrantLock();
    private long time;
    private int type;

    public UpdatingThread(long time, int type){
        this.time = time;
        this.type = type;
    }

    @Override
    public void run() {
        try{
            while (true){
                Thread.sleep(time);
                lock.lock();
                if(type == 1){
                    //type=1对应心情更新线程
                    emotionChanging();
                }
                else if(type == 2){
                    //type=2对应清洁更新线程
                    cleanlinessChanging();
                }
                else if(type == 3){
                    //type=3对应健康更新线程
                    healthChanging();
                }
                else if(type == 4){
                    //type=4对应体力更新线程
                    staminaChanging();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void emotionChanging() {
        int value = TotalState.getInstance().getEmotionState().getEmotion();
        if(value > 0){
            TotalState.getInstance().getEmotionState().reduce();
        }
    }

    private void cleanlinessChanging() {
        int value = TotalState.getInstance().getCleanlinessState().getCleanliness();
        if(value > 0){
            TotalState.getInstance().getCleanlinessState().reduce();
        }
    }

    private void healthChanging() {
        int value = TotalState.getInstance().getHealthState().getHealth();
        if(value > 0){
            TotalState.getInstance().getHealthState().reduce();
        }
    }

    private void staminaChanging() {
        int value = TotalState.getInstance().getStaminaState().getStamina();
        if(value > 0){
            TotalState.getInstance().getStaminaState().reduce();
        }
    }
}

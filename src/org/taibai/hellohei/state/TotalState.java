package org.taibai.hellohei.state;

import javax.swing.text.html.ImageView;

/**
 *
 * <p>Description: 小黑的所有状态</p>
 *
 * @author
 */
public class TotalState {

    private static TotalState totalState;

    private final EmotionState emotionState;
    private final StaminaState staminaState;
    private final CleanlinessState cleanlinessState;
    private final MoneyState moneyState;
    private final HealthState healthState;

    private TotalState() {
        emotionState = new EmotionState();
        staminaState = new StaminaState();
        cleanlinessState = new CleanlinessState();
        moneyState =new MoneyState();
        healthState = new HealthState();
    }

    public static TotalState getInstance() {
        if (totalState == null) totalState = new TotalState();
        return totalState;
    }

    public EmotionState getEmotionState() {
        return emotionState;
    }

    public StaminaState getStaminaState() {
        return staminaState;
    }

    public CleanlinessState getCleanlinessState() {
        return cleanlinessState;
    }

    public MoneyState getMoneyState(){
        return moneyState;
    }

    public HealthState getHealthState() {return healthState; }

}

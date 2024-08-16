package ruleLogic;

import EssentialUtil.GameState;
import cardLogic.Card;

public class NumberChinRule implements Rule{
    @Override
    public boolean checkValidity(Card playedCard, Card topCard, GameState state) {
        return topCard.getValue().equalsIgnoreCase(playedCard.getValue());
    }
}

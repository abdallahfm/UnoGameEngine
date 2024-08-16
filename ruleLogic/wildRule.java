package ruleLogic;

import EssentialUtil.GameState;
import cardLogic.Card;

public class wildRule implements Rule{
    @Override
    public boolean checkValidity(Card playedCard, Card topCard, GameState state) {
        return playedCard.getColor().equalsIgnoreCase("mix");
    }
}

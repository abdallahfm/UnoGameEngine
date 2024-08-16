package ruleLogic;

import EssentialUtil.GameState;
import cardLogic.Card;

public interface Rule {
    boolean checkValidity(Card playedCard , Card topCard, GameState state);

}

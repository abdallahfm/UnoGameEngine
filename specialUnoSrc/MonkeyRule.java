package specialUnoSrc;

import EssentialUtil.GameState;
import cardLogic.Card;
import ruleLogic.Rule;

public class MonkeyRule implements Rule {
    @Override
    public boolean checkValidity(Card playedCard, Card topCard, GameState state) {
        return playedCard.getValue().equalsIgnoreCase("monkey");
    }
}

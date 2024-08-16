package ruleLogic;

import EssentialUtil.GameState;
import cardLogic.Card;

public class colorChainRule implements Rule{
    @Override
    public boolean checkValidity(Card playedcard, Card topCard, GameState state) {
        System.out.println("checking color");
        if(topCard.getColor().equalsIgnoreCase("mix"))
            return playedcard.getColor().equalsIgnoreCase(state.getColor()) || state.getColor().equalsIgnoreCase("mix");
        return ( playedcard.getColor().equalsIgnoreCase(topCard.getColor()) );

    }
}

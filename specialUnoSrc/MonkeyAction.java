package specialUnoSrc;

import EssentialUtil.GameState;
import actionLogic.Action;

public class MonkeyAction implements Action {

    @Override
    public GameState perform(GameState state) {
        state.getPlayers().get(state.getPlayerTurn()).resetHand();
        return state;
    }
}

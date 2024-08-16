package actionLogic;

import EssentialUtil.GameState;

import java.util.Collections;

public class reverse implements Action{
    @Override
    public GameState perform(GameState state) {

        Collections.reverse(state.getPlayers());
        state.setPlayerTurn((state.getPlayers().size()-state.getPlayerTurn())%state.getPlayers().size());
        return state;

    }
}

package actionLogic;

import EssentialUtil.GameState;

public class NoAction implements Action{
    @Override
    public GameState perform(GameState state) {
        state.setPlayerTurn((state.getPlayerTurn()+1)%state.getPlayers().size());
        return state;
    }
}

package actionLogic;

import EssentialUtil.GameState;

public class skip implements Action{
    @Override
    public GameState perform(GameState state) {
        state.setPlayerTurn((state.getPlayerTurn()+2)%state.getPlayers().size());
    return state;
    }
}

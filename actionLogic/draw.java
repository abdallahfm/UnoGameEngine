package actionLogic;

import EssentialUtil.GameState;

public class draw implements Action{
    private final int drawAmount;
    public draw(int drawAmount) {
        this.drawAmount = drawAmount;
    }
    @Override
    public GameState perform(GameState state) {
        //draw
        state.setPlayerTurn((state.getPlayerTurn()+1)%state.getPlayers().size());
        state.getPlayers().get(state.getPlayerTurn()).addMultipleCards(state.getDeck().drawCards(drawAmount));
        state.setPlayerTurn((state.getPlayerTurn()+1)%state.getPlayers().size());
        return state;
    }
}

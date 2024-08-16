package actionLogic;

import EssentialUtil.GameState;

public class DrawWithColorChange extends ColorChange  {
    private final int drawAmount;

    public DrawWithColorChange(int drawAmount) {
        this.drawAmount = drawAmount;
    }

    @Override
    public GameState perform(GameState state) {
        state.setPlayerTurn((state.getPlayerTurn()+1)%state.getPlayers().size());
        state.getPlayers().get(state.getPlayerTurn()).addMultipleCards(state.getDeck().drawCards(drawAmount));
        state.setPlayerTurn((state.getPlayerTurn()+1)%state.getPlayers().size());
        state.setColor(requestColor());
        return state;

    }
}

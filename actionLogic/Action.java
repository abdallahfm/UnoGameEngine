package actionLogic;

import EssentialUtil.GameState;

public interface Action {
    GameState perform(GameState state);
}

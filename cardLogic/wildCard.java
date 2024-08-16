package cardLogic;

import actionLogic.Action;

public class wildCard extends Card{
    private final String type;
    public wildCard(String color, String value, int score, Action action) {
        super(color, value, score, action);
        type=value;
    }

    @Override
    public String toString() {
        return "wildCard("+type+")";
    }
}

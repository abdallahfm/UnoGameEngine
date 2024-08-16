package cardLogic;

import actionLogic.Action;

public class actionCard extends Card{
    private final String type;
    public actionCard(String color, String value, int score, Action action) {
        super(color, value, score, action);
        type=color+" "+value;
    }
    public String toString() {
        return "Action card("+type+")";
    }
}

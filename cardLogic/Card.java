package cardLogic;

import actionLogic.Action;

public abstract class Card {
    private final String color;
    private final String value;
    private final int score;
    private final Action action;

    public Card(String color, String value, int score, Action action) {
        this.color = color;
        this.value = value;
        this.score = score;
        this.action = action;
    }

    @Override
    public String toString() {
        return "{" +
                "color='" + color + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }

    public Action getAction() {
        return action;
    }


}

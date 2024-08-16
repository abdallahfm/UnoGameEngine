package EssentialUtil;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    private Deck deck;
    private int playerTurn;
    private String color;



    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public GameState(ArrayList<Player> players, Deck deck, int playerTurn, String color) {
        this.players = players;
        this.deck = deck;
        this.playerTurn = playerTurn;
        this.color = color;

    }



    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public String getColor() {
        return color;
    }



    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setColor(String color) {
        this.color = color;
    }


}

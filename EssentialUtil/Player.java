package EssentialUtil;

import cardLogic.Card;
import scoringStrategy.ScoringStrategy;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int score;
    private final ArrayList<Card> hand;
    private ScoringStrategy scoringStrategy;

    public Player(String name, int score, ArrayList<Card> hand,ScoringStrategy scoringStrategy) {
        this.name = name;
        this.score = score;
        this.hand = hand;
        this.scoringStrategy=scoringStrategy;
    }
    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public void resetHand(){
        hand.clear();
    }
    public int calculateScore() {
        if (scoringStrategy == null) {
            throw new IllegalStateException("Scoring strategy not set");
        }
        return scoringStrategy.calculateScore(this.hand);
    }


    public void incrementScore(int score) {
        this.score = this.score +score;
    }
    public void showHand(){
        for (int i = 0; i < hand.size(); i++) {
            System.out.print("( "+ (1+i)+ " )" +hand.get(i)+",");
        }
        System.out.println();
    }
    public int numberOfCardsInHand(){
        return hand.size();
    }

    public Card getCardByIndex(int index ){
        return hand.get(index);
    }

    public void removeCard(int index){
        hand.remove(index);
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public void addMultipleCards(ArrayList<Card> cards){
        while(!cards.isEmpty()){
            hand.add(cards.get(0));
            cards.remove(0);
        }

    }


}

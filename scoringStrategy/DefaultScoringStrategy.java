package scoringStrategy;

import cardLogic.Card;

import java.util.ArrayList;

public class DefaultScoringStrategy implements ScoringStrategy{
    @Override
    public int calculateScore(ArrayList<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getScore();
        }
        return score;
    }
}

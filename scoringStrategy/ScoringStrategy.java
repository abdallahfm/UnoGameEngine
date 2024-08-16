package scoringStrategy;

import cardLogic.Card;

import java.util.ArrayList;

public interface ScoringStrategy {
    int calculateScore(ArrayList<Card> hand);
}

package scoringStrategy;

import cardLogic.Card;

import java.util.ArrayList;

public class specialScoringSystem implements ScoringStrategy{
    @Override
    public int calculateScore(ArrayList<Card> hand) {
      return hand.size();
    }
}

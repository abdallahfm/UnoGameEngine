import cardLogic.Card;
import cardLogic.cardFactory;
import ruleLogic.Rule;
import scoringStrategy.specialScoringSystem;
import specialUnoSrc.MonkeyAction;
import specialUnoSrc.MonkeyRule;

import java.util.ArrayList;

public class specialUNO extends Game{
    @Override
    public void play() {
        ArrayList<Card>custom=new ArrayList<>();
        ArrayList<Rule>rule=new ArrayList<>();
        custom.add(cardFactory.createCard("Custom","no-color","monkey",100,new MonkeyAction()));
        setCustomCards(custom);
        rule.add(new MonkeyRule());
        setCustomRules(rule);
        setScoringStrategy(new specialScoringSystem());
        super.play();
    }
}

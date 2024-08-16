package ruleLogic;

import java.util.ArrayList;

public class RuleManager {
    private final ArrayList<Rule> rules;

    public RuleManager() {
        this.rules = new ArrayList<>();
    }

    public void addDefaultRules() {
        // Add default rules
        rules.add(new wildRule());
        rules.add(new colorChainRule());
        rules.add(new NumberChinRule());
    }

    public void addCustomRules(ArrayList<Rule> customRules) {
        rules.addAll(customRules);
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    // Additional methods related to rule management can be added here
}

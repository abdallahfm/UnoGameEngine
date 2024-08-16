package EssentialUtil;

import cardLogic.Card;
import ruleLogic.Rule;
import ruleLogic.RuleManager;
import scoringStrategy.ScoringStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameInitializer {
    private  Deck deck;
    private final ScoringStrategy scoringStrategy;
    private final int dealingCount;
    private ArrayList<Rule>customRules=new ArrayList<>();
    private ArrayList<Card> customCards=new ArrayList<>();
    private final RuleManager ruleManager = new RuleManager();
    private final Scanner scanner = new Scanner(System.in);

    public GameInitializer(Deck deck, ScoringStrategy scoringStrategy, int dealingCount, ArrayList<Rule> customRules, ArrayList<Card> customCards) {
        this.deck = deck;
        this.scoringStrategy = scoringStrategy;
        this.dealingCount = dealingCount;
        this.customRules = customRules;
        this.customCards = customCards;
    }

    public RuleManager getRuleManager() {
        return ruleManager;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Player> initializePlayers(List<String> playerNames) {

        ArrayList<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name, 0, new ArrayList<>(), scoringStrategy));
        }
        return players;
    }

    public void dealCardsToPlayers(ArrayList<Player> players) {

        for (Player player : players) {
            player.addMultipleCards(this.deck.drawCards(dealingCount));
        }
    }
    public int EnterPlayerCount(){

        int numOfPlayers=-1;
        while(numOfPlayers==-1){
        System.out.println("please Enter the number of players ( 2-10 players ) : ");
        try{

            numOfPlayers=scanner.nextInt();
            while(numOfPlayers<2 || numOfPlayers>10){
                System.out.println("please enter a valid number of players between 2 and 10");
                numOfPlayers=scanner.nextInt();
            }
        }
        catch (Exception ex){
            System.out.println("enter a number between 2 and 10");
            return -1;
        }
        System.out.println("----the number of players playing: "+ numOfPlayers+" -------");
        }
        return numOfPlayers;
    }
    public ArrayList<String> enterPlayersNames(int num){
        ArrayList<String>names=new ArrayList<>();
        for(int i=1;i<=num;i++){
            System.out.println("Enter player "+i+" name");
            names.add(scanner.next());
        }
        return names;
    }

    private void addCustomCards(){
        for(Card card : customCards){
            deck.addCard(card);
        }
    }
    public void initRules(){

        ruleManager.addDefaultRules();
        if (!customRules.isEmpty()) {
            ruleManager.addCustomRules(customRules);
        }
    }
    public void deckInit(int numberCardCount,int actionCardCount,int wildCardCount){
        DeckBuilder deckBuilder = new DeckBuilder();
        // Specify the number of each card type
        this.deck = deckBuilder.buildDeck(numberCardCount,actionCardCount,wildCardCount);
        this.deck.shuffleDeck();

        // adding any custom cards if applicable
        if(!customCards.isEmpty())addCustomCards();
    }

}

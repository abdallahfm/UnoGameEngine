import EssentialUtil.*;
import cardLogic.Card;
import ruleLogic.Rule;
import ruleLogic.RuleManager;
import scoringStrategy.DefaultScoringStrategy;
import scoringStrategy.ScoringStrategy;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class  Game {
    private final Scanner scanner=new Scanner(System.in);
    private Deck deck=new Deck();
    private final Deck pile=new Deck();
    private ArrayList<Rule>customRules=new ArrayList<>();
    private ArrayList<Card> customCards=new ArrayList<>();
    private ArrayList<Player>players;
    private GameInitializer gameInitializer;
    RuleManager ruleManager = new RuleManager();
    private int maxScore=100;
    private int dealingCount=7;
    private int numberCardCount=76,wildCardCount=8,actionCardCount=24;
    private ScoringStrategy scoringStrategy=null;
    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy=scoringStrategy;
    }

    public void setCustomCards(ArrayList<Card> customCards) {
        this.customCards = customCards;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setCustomRules(ArrayList<Rule> customRules) {
        this.customRules = customRules;
    }

    public void setNumberCardCount(int numberCardCount) {
        this.numberCardCount = numberCardCount;
    }

    public void setWildCardCount(int wildCardCount) {
        this.wildCardCount = wildCardCount;
    }

    public void setActionCardCount(int actionCardCount) {
        this.actionCardCount = actionCardCount;
    }

    public void setDealingCount(int dealingCount) {
        this.dealingCount = dealingCount;
    }
    private void changeScoringStrategy(){
        for(Player player : players )
            player.setScoringStrategy(scoringStrategy);
    }
    public void play(){

        prepareGame();
        startGame();

    }
    private void prepareGame(){
        this.gameInitializer = new GameInitializer(deck, new DefaultScoringStrategy(), dealingCount,customRules,customCards);
        int numOfPlayers=gameInitializer.EnterPlayerCount();
        ArrayList<String> playerNames = gameInitializer.enterPlayersNames(numOfPlayers);
        //deck init
        this.gameInitializer.deckInit(numberCardCount,actionCardCount,wildCardCount);

        //init players
        this.players = gameInitializer.initializePlayers(playerNames);
        gameInitializer.dealCardsToPlayers(players);
        //init rules
        gameInitializer.initRules();
        deck=gameInitializer.getDeck();
        ruleManager=gameInitializer.getRuleManager();
        if(scoringStrategy!=null)changeScoringStrategy();
    }
    private void startGame() {
        pile.addCard(deck.drawCards(1).get(0));
        int roundNumber = 1;
        Player currentWinner = null;

        while (!isGameOver(currentWinner)) {
            System.out.println("Round number " + roundNumber + " will start now");
            System.out.println("--------------------------------------------------");
            Player roundWinner = playRound();
            currentWinner = updateOverallWinner(currentWinner, roundWinner);
            System.out.println("Round number " + roundNumber + " Ended with " + roundWinner.getName() + " winning the round.");
            System.out.println("--------------------------------------------------");
            playAgain();
            roundNumber++;
        }
        System.out.println("The game has ended! Congratulations to the winner: " + currentWinner.getName());
    }
    private Player playRound() {
        GameState state = new GameState(players, deck, 0, pile.getTop().getColor());
        while (players.stream().noneMatch(player -> player.numberOfCardsInHand() == 0)) {
            Player currentPlayer = players.get(state.getPlayerTurn());
            playTurn(currentPlayer, state);
        }
        Player roundWinner = determineRoundWinner();
        updateScore(roundWinner);
        System.out.println("----->" + roundWinner.getName() + " Won this Round! Their score is " + roundWinner.getScore() + "<-----");
        return roundWinner;
    }
    private void playTurn(Player player, GameState state) {
        showGameState(player, state);
        int cardChoice = getCardChoiceFromPlayer(player);
        if (cardChoice == -1) {
            handleCardDraw(player, state);
        } else {
            processCardPlay(player, cardChoice , state);
        }
    }
    private boolean validateCard(Card playedCArd,GameState state){

        for(Rule rule : ruleManager.getRules()){
            if ( rule.checkValidity(playedCArd,pile.getTop(), state) ) {
                return true;
            }
        }
        return  false;
    }
    private void showGameState(Player player, GameState state) {
        System.out.println("the card on top of the pile is :"+ pile.getTop() +" the color of the pile is "+ state.getColor());
        System.out.println(player.getName()+", please choose from your hand a card to play or -1 to draw :");
        player.showHand();
    }
    private int getCardChoiceFromPlayer(Player player) {
        int playedCardIdx;
        while(true) {
            while (!scanner.hasNextInt()) {
                System.out.println("enter a valid index");
                scanner.nextLine();
            }
            playedCardIdx = scanner.nextInt();
            if(playedCardIdx<-1 || playedCardIdx>player.numberOfCardsInHand() || playedCardIdx==0)System.out.println("enter a valid index");
            else break;
        }
        return  playedCardIdx;
    }
    private void handleCardDraw(Player player, GameState state) {
        player.addCard(deck.drawCards(1).get(0));
        player.showHand();
        System.out.println("do you want to skip this round ? y,n");
        if(scanner.next().equals("y")) state.setPlayerTurn((state.getPlayerTurn()+1)%players.size());

    }
    private void processCardPlay(Player player, int cardIndex, GameState state) {
        boolean isValidMove=validateCard(player.getCardByIndex(cardIndex-1),state);

        if(isValidMove){
            pile.addCard(player.getCardByIndex(cardIndex-1));
            state = player.getCardByIndex(cardIndex-1).getAction().perform(state);
            if(player.numberOfCardsInHand()>0)player.removeCard(cardIndex-1);
            players= state.getPlayers();
            state.setPlayerTurn(state.getPlayerTurn());
            if(!pile.getTop().getColor().equalsIgnoreCase("mix"))
                state.setColor(pile.getTop().getColor());
            System.out.println("----------------------------------------------------------");
        }
        else {
            System.out.println("wrong choice!!");
        }
    }
    private Player determineRoundWinner() {
        return players.stream().filter(player -> player.numberOfCardsInHand() == 0)
                .findFirst()
                .orElse(null);
    }
    private void updateScore(Player roundWinner){
        int totalScore = players.stream().mapToInt(Player::calculateScore).sum();
        roundWinner.incrementScore(totalScore);

    }
    private void playAgain(){
        deck.deleteAll();
        pile.deleteAll();
        gameInitializer.deckInit(numberCardCount,actionCardCount,wildCardCount);
        deck=gameInitializer.getDeck();
        ruleManager=gameInitializer.getRuleManager();
        for(Player player : players){
            player.resetHand();
            player.addMultipleCards(deck.drawCards(dealingCount));
        }
        pile.addCard(deck.drawCards(1).get(0));
}

   private boolean isGameOver(Player currentWinner) {
        return currentWinner != null && currentWinner.getScore() >= maxScore;
    }
    private Player updateOverallWinner(Player currentWinner, Player roundWinner) {
        if (currentWinner == null || roundWinner.getScore() > currentWinner.getScore()) {
            return roundWinner;
        }
        return currentWinner;
    }



}

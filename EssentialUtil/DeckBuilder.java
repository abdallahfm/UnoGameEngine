package EssentialUtil;

import actionLogic.*;
import cardLogic.cardFactory;

public class DeckBuilder {

    public Deck buildDeck(int countOfNumberCards, int countOfActionCards, int countOfWildCards) {
        Deck deck = new Deck();
        String[] colors =new String[]{"red", "blue", "yellow", "green"};
        String[] values =new String[]{"zero","one", "two", "three", "four","five", "six", "seven", "eight","nine"};

        Action action=new NoAction();
        for(int c=0;countOfNumberCards>0;c++){
            for(int v=0;v<10 && countOfNumberCards>0;v++){
                deck.addCard( cardFactory.createCard("Number",colors[c%4],values[v],v,action) ) ;
                c++;// to distribute the colors if number of card is not the default number
                countOfNumberCards--;

            }
        }
        action=new draw(2);
        countOfActionCards/=3;
        for(int i=0;i<countOfActionCards;i++){
            deck.addCard( cardFactory.createCard("action",colors[i%4],"draw two",20,action) ) ;
        }
        action=new reverse();
        for(int i=0;i<countOfActionCards;i++){
            deck.addCard( cardFactory.createCard("action",colors[i%4],"reverse",20,action) ) ;
        }
        action=new skip();
        for(int i=0;i<countOfActionCards;i++){
            deck.addCard( cardFactory.createCard("action",colors[i%4],"skip",20,action) ) ;
        }

        countOfWildCards/=2;
        action=new ColorChange();
        for(int i=0;i<countOfWildCards;i++){
            deck.addCard( cardFactory.createCard("wild","mix","wild",50,action) ) ;
        }
        action=new DrawWithColorChange(4);
        for(int i=0;i<countOfWildCards;i++){
            deck.addCard( cardFactory.createCard("wild","mix","Wild Draw 4",50,action) ) ;
        }

        return deck;
    }
}

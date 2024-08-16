package cardLogic;

import actionLogic.Action;

public interface cardFactory {

     static Card createCard(String name,String color, String value, int score, Action action){
         Card card = null;
         if(name.equalsIgnoreCase("Number")) {
             card = new numberCard(color, value, score, action);
             return card;
         }
         if(name.equalsIgnoreCase("Action")) {
             card = new actionCard(color, value, score, action);
             return card;
         }
         if(name.equalsIgnoreCase("wild")) {
             card = new wildCard(color, value, score, action);
             return card;
         }
         if(name.equalsIgnoreCase("Custom")) {
             card = new customCard(color, value, score, action);
             return card;
         }
         return card;


    }
}

package actionLogic;

import EssentialUtil.GameState;
import java.util.Scanner;

public class ColorChange implements Action{

    public String requestColor(){
        Scanner sc=new Scanner(System.in);
        System.out.println("to what color do you want to change ?");
        while(true){
            String color=sc.next();
            if(color.equalsIgnoreCase("red")) {
               return "red" ;
            }
            else if(color.equalsIgnoreCase("blue")) {
               return "blue";
            }
            else if(color.equalsIgnoreCase("yellow")) {
               return "yellow";
            }
            else if(color.equalsIgnoreCase("green")) {
               return "green";
            }
            System.out.println("Enter a valid color!");
        }
    }
    @Override
    public GameState perform(GameState state) {
       state.setColor(requestColor());
       state.setPlayerTurn((state.getPlayerTurn()+1)%state.getPlayers().size());
       return state;
    }
}

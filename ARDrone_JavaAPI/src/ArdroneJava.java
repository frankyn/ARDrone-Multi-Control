import java.util.Scanner;
public class ArdroneJava {  

    public static void main ( String[] args ) {    
        
        Scanner reader = new Scanner(System.in);
        ArdroneAPI drone1 = new ArdroneAPI("192.168.1.200");   
        ArdroneAPI drone2 = new ArdroneAPI("192.168.1.202");
        drone1.connect();
        drone2.connect();
        System.out.println("Type in a command:");
        String input = reader.nextLine();
        while(!input.equals("land")){
            System.out.println(input);
            if(input.equals("left")){
                drone1.left();
                drone2.left();
            }else
            if(input.equals("right")){
                drone1.right();
                drone2.right();
            }else
            if(input.equals("front")){
                drone1.front();
                drone2.front();
            }else
            if(input.equals("back")){
                drone1.back();
                drone2.back();
            }else
            if(input.equals("down")){
                drone1.down();
                drone2.down();
            }else
            if(input.equals("up")){
                drone1.up();
                drone2.up();
            }else
            if(input.equals("tright")){
                drone1.tright();
                drone2.tright();
            }else
            if(input.equals("tleft")){
                drone1.tleft();
                drone2.tleft();
            }

            input = reader.nextLine();    
        }
        
        drone1.close(); 
        drone2.close();
    }
}       
 

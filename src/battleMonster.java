import java.util.Scanner;

// CLASS VARIABLES
public class battleMonster {
    private  static Puppy puppy; 
    private static Monster[] monsters = new Monster[5];
    
    public static void main(String[] args){
         

        // SET UP 
        Scanner s = new Scanner(System.in);
        String input = "";

        // INTRO 
        System.out.println("your choice: fight or puppy");

        // GAME LOOP
        do{

            // CHECK FOR MONSTERS 
            if(noMonsters()) makeMonster();


            System.out.println("INPUT: ");
            input = s.nextLine().toLowerCase().trim();

            // OUR TURN
            if (input.equals("puppy") && puppy == null){
                
                puppy = new Puppy();
            }
            



            // MONSTERS TURN
            
            





        }while(!input.equals("quit"));

    }

    public static boolean noMonsters(){
        // loop and check for monsters 
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null) return false; 
        }
        return true;
    }

public static void makeMonster(){
    // looop and find the first free spot
    for(int i = 0; i < monsters.length; i++){
        if(monsters[i] == null){
            monsters[i] = new Monster(); 
            return;
        }
    }

}

}

import java.util.Scanner;

// CLASS VARIABLES
public class BattleMonster {
    // CLASS (NOT INSTANCE) VARIABLES
    private  static Puppy puppy; 
    private static Monster[] monsters = new Monster[5];
    private static int playerHealth = 100;
    private static int maxDmg = 100;
    
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
            //attack 
            else if(input.equals("attack")){
                // check if thers a puppy
                if(puppy != null){
                    // check if puppy attacks
                    // run a 1/100 chance the puppuy goes john wick
                   




                }
                // if no dog and no dog attack, roll for damage 

                // apply damage to first monster



            // MONSTERS' TURN
            
                

            }
            
            // heal 

            





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


    public static void johnWick(){
        // loop through all the monsters and the puppy destroys them 

        for(Monster m : monsters){
            if(m != null) m.takeDmg(m.health());
        }
    }

}

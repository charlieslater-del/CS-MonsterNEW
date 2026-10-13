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

                    double r = Math.random(); 
                    if(r < 0.01)johnWick();
                    // check if puppy attacks
                    // run a 1/100 chance the puppuy goes john wick
                   
                    int damage = (int)(Math.random() * maxDmg);
                    findWeakest().takeDmg(damage);



                }
                // if no dog and no dog attack, roll for damage 

                // apply damage to first monster



            // MONSTERS' TURN
            
                

            }
            
            // heal 

            else if(input.equals("heal")){
                // (diff + 1) + min
                int random = (int)(Math.random() * 91) + 10; 
                playerHealth += random;
                System.out.println("you heal for " + random " points. (Current health: " + playerHealth +")");   
            }

            





        }while(!input.equals("quit"));

    }

    public static boolean noMonsters(){
        // loop and check for monsters 
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null) return false; 
        }
        return true;
    }


    public static Monster findWeakest(){
        // grab the first non null monsters as our weakest 
        Monster result; 
        for(Monster m: monsters) if(m != null) result = m;

        // look for a weaker monster 
        for(Monster m: monsters){ 
            if(m!= null && m.health() < result.health()){ 
                result = m;
            }
        }
        
        // return the weakest 
        return result; 

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

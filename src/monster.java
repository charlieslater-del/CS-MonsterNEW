public class Monster {

    public static int specialMonsters = 1;


    public Monster(){
        if (Monster.specialMonsters > 0) 
            System.out.println("I'M ALIVE!!!!!!");
            Monster.specialMonsters--;
        else System.out.println("I'm just an average monster");
    }
   
}

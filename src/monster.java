public class Monster{

    private int health; 
    private int maxDmg;

    public Monster(){
       health = 100; 
       
       maxDmg = (int)(Math.random() * 15 + 1) + 10; 
    }

    // ACCESSORS 
    public int health(){return health;}
    public int maxDmg(){return maxDmg;}
    


    // MUTATORS 
    public void takeDmg(int change){
        health -= dmg; 
        System.out.println("monster takes " + dmg + " damage.");
        if(health <=0) System.out.println("aww monswer ded :(");
    }
}

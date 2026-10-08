public class Quiz {


    public String maybeReverse(String str){
        double math = Math.random(); 


        if(math<0.5){
            for(int i = str.length() - 1; i >= 0; i--){
                String reverse = ""; 
                reverse = reverse + str.substring(i, i-1);

                return reverse;
            }




        }
        return str; 


    }
    





}

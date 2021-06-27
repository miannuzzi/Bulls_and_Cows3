import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
                
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();


 
        double randomValue = 0;
        
        boolean looking = true;
        
        while (looking) {

            Random random = new Random(k);
            double maxRandom = - Double.MAX_VALUE;
            
//            System.out.println("Seed: " + i);

            for (int j = 0; j < n; j++) {
                randomValue = random.nextGaussian();
//                System.out.println("randomValue: " + randomValue);

                if (randomValue > maxRandom) {
                    maxRandom = randomValue;
                }
            }

//            System.out.println("Max random: " + maxRandom);

            if (maxRandom <= m) {
                looking = false;                
            } else {
                k++;
            }
        }

        System.out.println(k);
        
    }
}

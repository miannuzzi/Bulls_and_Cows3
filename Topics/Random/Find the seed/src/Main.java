import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();


        int minSeed = 0;

        int minMax = Integer.MAX_VALUE;
        int randomValue;

        for (int i = a; i < (b + 1); i++) {
            int maxRandom   = Integer.MIN_VALUE;
            Random random = new Random(i);
//            System.out.println("Seed: " + i);

            for (int j = 0; j < n; j++) {
                randomValue = random.nextInt(k);
//                System.out.println("randomValue: " + randomValue);

                if (randomValue > maxRandom) {
                    maxRandom = randomValue;
                }
            }

//            System.out.println("Max random: " + maxRandom);

            if (maxRandom < minMax) {
                minMax = maxRandom;
                minSeed = i;
            }
        }

        System.out.println(minSeed);
        System.out.println(minMax);
    }
}
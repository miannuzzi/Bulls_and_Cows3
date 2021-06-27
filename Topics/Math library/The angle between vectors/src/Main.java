import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        int ad = x1 - x2;
        int op = y1 - y2;

        double div = (double)op/ad;
        double atan = Math.atan(div);



        System.out.println(Math.toDegrees(atan));

    }
}
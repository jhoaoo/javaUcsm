import java.util.Random;

public class dado {
    public static void main(String[] args) {

        Random random = new Random();

        int[] contador = new int[7];

        for (int i = 0; i < 20000; i++) {

            int dado = random.nextInt(0, 7);

            contador[dado]++;
        }

        for (int i = 0; i <= 6; i++) {
            System.out.println("Cara " + i + ": " + contador[i]);
        }
    }
}
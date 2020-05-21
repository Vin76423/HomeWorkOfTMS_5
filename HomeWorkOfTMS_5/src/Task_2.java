import java.util.Arrays;
import java.util.Random;

public class Task_2 {
    public static void main(String[] args) {

        int size = Task_1.helper("Введите размер матрицы, вектора:");
        Random random = new Random();

        int [][] arrayA = new int[size][size];
        int [] arrayB = new int[size];
        int [] arrayC = new int[size];

        for ( int i = 0; i < arrayA.length; i++ ) {
            for ( int j = 0; j < arrayA[i].length; j++ ) {
                arrayA[i][j] = random.nextInt(20);
                System.out.print(arrayA[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for ( int i = 0; i < arrayB.length; i++ ) {
            arrayB[i] = random.nextInt(20);
            System.out.print(arrayB[i] + " ");
        }

        System.out.println("\n");

        for ( int i = 0; i < arrayA.length; i++ ) {
            for ( int j = 0; j < arrayA[i].length; j++ ) {
                arrayC [i] += arrayA [i][j] * arrayB [j];
            }
            System.out.print(arrayC[i] + " ");
        }
    }
}

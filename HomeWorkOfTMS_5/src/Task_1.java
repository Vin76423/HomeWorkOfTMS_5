import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        int size = helper("Введите длину матриыц:");

        int [][] matrix = new int[size][size];
        int index = 0;

        int counter = matrix.length;
        int lengthIteration = 4;
        int lengthArray = matrix.length;
        int nulValueArray = 0;

        while ( counter > 0 ) {
            if ( counter == 2) {
                lengthIteration = 3;
            } else if ( counter == 1 ) {
                lengthIteration = counter;
            }

            for ( int i = 0; i < lengthIteration; i++ ) {
                for ( int j = nulValueArray; j < lengthArray; j++ ) {

                    if ( i == 0 ) {
                        index++;
                        matrix [nulValueArray][j] = index;
                    } else if ( i == 1 && j > nulValueArray ) {
                        index++;
                        matrix [j][lengthArray - 1] = index;
                    } else if ( i == 2 && j != 0 && j < lengthArray - nulValueArray ) {
                        index++;
                        matrix [lengthArray - 1][lengthArray - 1 - j] = index;
                    } else if ( i == 3 && j != 0 && j < lengthArray - 1 - nulValueArray ) {
                        index++;
                        matrix [lengthArray - 1 -j][nulValueArray] = index;
                    }
                }
            }
            counter -= 2;
            lengthArray--;
            nulValueArray++;
        }

        for ( int [] array : matrix ) {
            for ( int x : array ) {
                System.out.print(x + "  ");
            }
            System.out.println();
        }
    }


    public static int helper ( String massage ) {

        int number;
        System.out.println(massage);

        while ( true ) {
            Scanner scanner = new Scanner(System.in);
            if ( scanner.hasNextInt() ) {
                number = scanner.nextInt();
                if ( number > 0 ) {
                    break;
                }
            }
            System.out.println("Значение не корректно, попробуйте еще раз:");
        }

        return number;
    }
}

import java.util.Scanner;

// Class #1 ............................................

class Player {
    private int counterAnswers = 0;
    private int numberOfPlayer;

    public int [] askCords ( int [][] cache ) {
        int [] values = new int[2]; // row/column
        boolean key = true;

        String massage = "Введите координату(номер) строки:";
        System.out.println(numberOfPlayer + " игрок делает ход.");

        while ( key ) {
            for ( int i = 0; i < 2; i++) {
                System.out.println(massage);
                int value;

                while ( true ) {
                    Scanner scanner = new Scanner(System.in);
                    if ( scanner.hasNextInt() ) {
                        value = scanner.nextInt();
                        if ( value > 0 && value < 4 ) {
                            break;
                        }
                    }
                    System.out.println("Значение не корректно, попробуйте еще раз:");
                }

                if ( i == 0 ) {
                    values [0] = value;
                    massage = "Введите координату(номер) столбца:";
                } else {
                    values [1] = value;
                }
            }

            key = false;

            for ( int i = 0; i < cache.length; i++ ) {
                if ( values [0] == cache [i][0] && values [1] == cache [i][1] ) {
                    key = true;
                    System.out.println("Эта ячейка уже заполнена! Введите другие координаты.");
                    break;
                }
            }
        }

        counterAnswers++;
        return values;
    }

    public void setNumberOfPlayer ( int numberOfPlayer ) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public int getNumberOfPlayer () {
        return numberOfPlayer;
    }

    public int getCounterAnswers () {
        return counterAnswers;
    }
}

// Class #2 .........................................

class WorkPlace {
    private int[][] place = new int[3][3];

    public void setPlace () {
        for ( int i = 0; i < place.length; i++ ) {
            for ( int j = 0; j < place [i].length; j++ ) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public boolean checkMySelf ( int [] values, int index ) {
        // Записывает значение(Х/0) и отрисовывает рабочее поле:
        boolean answer = false;

        for ( int i = 0; i < place.length; i++ ) {
            for ( int j = 0; j < place[i].length; j++ ) {

                if ( i == values [0] - 1 && j == values [1] - 1 ) {
                    place [i][j] = index;
                }

                if ( place [i][j] == 1 ) {
                    System.out.print("X ");
                } else  if ( place [i][j] == 2 ) {
                    System.out.print("0 ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

        // Проверяет рабочее поле:

        int counterGeneralLine = 0;
        int counterUnGeneralLine = 0;
        int[] counterColumn = {0,0,0};

        body: {
            for ( int i = 0; i < place.length; i++ ) {
                int counterRow = 0;

                for ( int j = 0; j < place [i].length; j++ ) {
                    if ( i == j && place [i][j] == index ) {
                        counterGeneralLine++;
                    }
                    if ( place [i].length - 1 - i == j && place [i][j] == index ) {
                        counterUnGeneralLine++;
                    }
                    if ( place [i][j] == index ) {
                        counterRow++;
                        counterColumn [j]++;
                    }
                }

                if ( counterRow == place [i].length ) {
                    answer = true;
                    break body;
                }
            }

            if ( counterGeneralLine == place.length || counterUnGeneralLine == place.length ) {
                answer = true;
                break body;
            }

            for ( int i : counterColumn ) {
                if ( i == 3 ) {
                    answer = true;
                    break body;
                }
            }
        }

        return answer;
    }
}

// Class #3 .........................................

public class GameByOOP {
    private int [][] cache = new int[9][2]; // row/column
    private int gameCounter = 0;
    private boolean somebodyWin = false;

    private Player player1 = new Player();
    private Player player2 = new Player();
    private WorkPlace place = new WorkPlace();

    private void setGame () {
        System.out.println("Перед вами игра крестики-нолики. Ваша задача заполнять" +
                " рабочее поле 3х3 поочереди. Первый игрок играет за \"Х\" , второй за \"0\". " +
                "\nчтобы вписать свое значение в игравое поле, нужно указать номер строки а затем номер колонки для своего значения во время хода." +
                "\nНумерация начинаятся c левого верхнего угла поля");
        place.setPlace();
        player1.setNumberOfPlayer(1);
        player2.setNumberOfPlayer(2);
    }

    private void playGame () {
        do {
            int [] values;

            if ( gameCounter % 2 == 0 ) {
                values = player1.askCords(cache);
                somebodyWin = place.checkMySelf(values, player1.getNumberOfPlayer());
            } else {
                values = player2.askCords(cache);
                somebodyWin = place.checkMySelf(values, player2.getNumberOfPlayer());
            }

            cache [gameCounter] = values;
        } while ( !(somebodyWin) && ++gameCounter < 9 );
    }

    private void finishGame () {
        System.out.println("И так, игра закончена!!!");
        if ( somebodyWin ) {
            System.out.print("У нас есть победитель им стал.....: ");
            if ( gameCounter % 2 == 0 ) {
                System.out.print("игрок № " + player1.getNumberOfPlayer() + " за " + player1.getCounterAnswers() + " хода");
            } else {
                System.out.print("игрок № " + player2.getNumberOfPlayer() + " за " + player2.getCounterAnswers() + " хода");
            }
        } else {
            System.out.println("К сожалению у нас ничья...Не хотите ли сыграть еще раз?");
        }
    }



    public static void main(String[] args) {
        GameByOOP game = new GameByOOP();
        game.setGame();
        game.playGame();
        game.finishGame();
    }
}


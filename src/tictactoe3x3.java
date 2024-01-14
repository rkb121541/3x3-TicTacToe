import java.util.Scanner;
import java.util.Random;

public class tictactoe3x3{

    public static void main(String args[]) {

        while (true) {
            System.out.println("Welcome to a game of tic tac toe!");
            char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
            printBoard(board);
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            while (true) {
                System.out.println("Where would you like to place your marker 'X'? (1-9)");
                String userInput = scanner.nextLine().trim();

                while (makeMove(board, 'X', userInput) == false) {
                    System.out.println("Invalid input.");
                    System.out.println("Please choose an available slot to place your marker 'X' (1-9)");
                    printBoard(board);
                    System.out.println("Where would you like to place your marker 'X'? (1-9)");
                    userInput = scanner.nextLine().trim();
                }
                printBoard(board);

                if (checkWinner(board) == true) {
                    System.out.println("Congratulations, you won!");
                    break;
                } else if (checkDone(board)) {
                    System.out.println("It's a tie!");
                    break;
                }

                int computerInput = random.nextInt(1,10);
                while (makeMove(board, 'O', String.valueOf(computerInput)) == false) {
                    computerInput = random.nextInt(1,10);
                }
                System.out.println("The computer has placed its marker 'O'");
                printBoard(board);
                if (checkWinner(board) == true) {
                    System.out.println("You lost!");
                    break;
                }

            }

            System.out.println("Would you like to play again? (y/n)");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            while (!playAgain.equals("y") && !playAgain.equals("n")) {
                System.out.println("Would you like to play again? (y/n)");
                playAgain = scanner.nextLine().trim().toLowerCase();
            }

            if (playAgain.equals("n")) {
                System.out.println("Thanks for playing!");
                // Scanner scanner = new Scanner(System.in);
                // https://stackoverflow.com/questions/58244954/why-cant-i-just-create-another-scanner-object-after-using-scanner-close
                // You can't recreate the new scanner object in the next iteration of the while loop because it closes System.in
                scanner.close();
                break;
            }
        }
    }

    public static void printBoard(char[] board) {
        System.out.println(board[0] + "|" + board[1] + "|" + board[2]);
        System.out.println(board[3] + "|" + board[4] + "|" + board[5]);
        System.out.println(board[6] + "|" + board[7] + "|" + board[8]);
    }

    public static boolean checkWinner(char[] board) {
        if ((board[0] == board[1] && board[1] == board[2]) ||
                (board[3] == board[4] && board[4] == board[5]) ||
                (board[6] == board[7] && board[7] == board[8]) ||
                (board[0] == board[3] && board[3] == board[6]) ||
                (board[1] == board[4] && board[4] == board[7]) ||
                (board[2] == board[5] && board[5] == board[8]) ||
                (board[0] == board[4] && board[4] == board[8]) ||
                (board[2] == board[4] && board[4] == board[6])) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDone(char[] board) {
        // iterates through "board" to see if any of the elements in it are 'X' or 'O'
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    public static boolean makeMove(char[] board, char turn, String position) {

        if (position.length() != 1 || Integer.parseInt(position) > 9 || Integer.parseInt(position) < 1 ||
                board[Integer.parseInt(position) - 1] == 'X' || board[Integer.parseInt(position) - 1] == 'O') {
            return false;
        }

        // https://www.youtube.com/watch?v=fjUG_y5ZaL4 -> switch is faster than if
        // when it gets translated to assembly, all the if statements will be CMP
        // so the computer will need to run through all the CMP instructions for each if statement
        // in assembly, the switch statement is O(1) so it can access the desired one immediately by doing some smart arithmetic
        // hence, switch > if for runtime

        switch(position) {
            case "1":
                board[0] = turn;
                return true;
            case "2":
                board[1] = turn;
                return true;
            case "3":
                board[2] = turn;
                return true;
            case "4":
                board[3] = turn;
                return true;
            case "5":
                board[4] = turn;
                return true;
            case "6":
                board[5] = turn;
                return true;
            case "7":
                board[6] = turn;
                return true;
            case "8":
                board[7] = turn;
                return true;
            case "9":
                board[8] = turn;
                return true;
            default:
                return false;
        }
    }


}
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    static char[][] board = {
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        while (true) {

            printBoard();

            System.out.print("Enter slot (1-9): ");
            int slot = sc.nextInt();

            if (!isValidMove(slot)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            placeMove(slot, 'X');

            if (checkWin('X')) {
                printBoard();
                System.out.println("You win!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }

            int compSlot;
            do {
                compSlot = rand.nextInt(9) + 1;
            } while (!isValidMove(compSlot));

            System.out.println("Computer placed at slot: " + compSlot);
            placeMove(compSlot, 'O');

            if (checkWin('O')) {
                printBoard();
                System.out.println("Computer wins!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }
        }
    }

    static void printBoard() {
        for (char[] row : board) {
            System.out.println(row[0] + " | " + row[1] + " | " + row[2]);
        }
        System.out.println();
    }

    static boolean isValidMove(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return slot >= 1 && slot <= 9 && board[row][col] == '-';
    }

    static void placeMove(int slot, char symbol) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        board[row][col] = symbol;
    }

    static boolean isDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkWin(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;

            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
                return true;
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;

        return false;
    }
}
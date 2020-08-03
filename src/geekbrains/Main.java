package geekbrains;

public class Main {



import java.util.Random;
import java.util.Scanner;

    public class TicTacToe {

        private static final char DOT_HUMAN = 'X';
        private static final char DOT_AI = 'O';
        private static final char DOT_EMPTY = '.';
        private static final Scanner SCANNER = new Scanner(System.in);
        private static final Random RANDOM = new Random();
        private static int fieldSizeY;
        private static int fieldSizeX;
        private static char[][] field;
//Задание 2
        
        private static void initField() {
            fieldSizeX = 5;
            fieldSizeY = 5;
            field = new char[fieldSizeY][fieldSizeX];
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    field[y][x] = DOT_EMPTY;
                }
            }
        }

        private static void printField() {
            System.out.print("+");
            for (int x = 0; x < fieldSizeX * 2 + 1; x++)
                System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
            System.out.println();

            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(y + 1 + "|");
                for (int x = 0; x < fieldSizeX; x++)
                    System.out.print(field[y][x] + "|");
                System.out.println();
            }

            for (int x = 0; x <= fieldSizeX * 2 + 1; x++)
                System.out.print("-");
            System.out.println();
        }

        private static void humanTurn() {
            int x;
            int y;
            do {
                System.out.println("Р’РІРµРґРёС‚Рµ РєРѕРѕСЂРґРёРЅР°С‚С‹ С…РѕРґР° X Рё Y (РѕС‚ 1 РґРѕ 3) С‡РµСЂРµР· РїСЂРѕР±РµР» >>> ");
                x = SCANNER.nextInt() - 1;
                y = SCANNER.nextInt() - 1;
            } while (!isValidCell(x, y) || !isEmptyCell(x, y));
            field[y][x] = DOT_HUMAN;
        }

        private static boolean isEmptyCell(int x, int y) {
            return field[y][x] == DOT_EMPTY;
        }

        private static boolean isValidCell(int x, int y) {
            return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
        }

        private static void aiTurn() {
            int x;
            int y;
            do {
                x = RANDOM.nextInt(fieldSizeX);
                y = RANDOM.nextInt(fieldSizeY);
            } while (!isEmptyCell(x, y));
            field[y][x] = DOT_AI;
        }

        private static boolean checkDraw() {
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (isEmptyCell(x, y)) return false;
                }
            }
            return true;
        }

        private static boolean checkWin(char c) {
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
            // hor
            if (_left ==  right) return true;

            // ver
            if ( top == bottom ) return true;

            // dia
            if (left == right + bottom) return true;
            if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;
            return false;

        }

        public static void main(String[] args) {
            initField();
            printField();
            String answer;
            do {
                while (true) {
                    humanTurn();
                    if (checkEndGame(DOT_HUMAN, "Human win!")) break;
                    aiTurn();
                    if (checkEndGame(DOT_AI, "Computer win!")) break;
                }
                System.out.println("Wanna play again? (y/n) >>> ");
                answer = SCANNER.next();
            } while (answer.equals("y"));
            SCANNER.close();
        }

        private static boolean checkEndGame(char dot, String winMessage) {
            printField();
            if (checkWin(dot)) {
                System.out.println(winMessage);
                return true;
            }
            if (checkDraw()) {
                System.out.println("Draw!");
                return true;
            }
            return false;
        }

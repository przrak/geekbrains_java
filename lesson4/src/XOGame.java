import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class XOGame {
    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;

    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '.';

    private static char[][] map;

    private final static Scanner sc = new Scanner(System.in);
    private final static Random random = new Random();


    public static void main(final String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы выиграли!!!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
//            advancedAiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Комьютер победил");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                map[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int y = 0; y < SIZE; y++) {
            System.out.print(y + 1 + " ");
            for (int x = 0; x < SIZE; x++) {
                System.out.printf("%c ", map[y][x]);
            }
            System.out.println();
        }
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("input coord X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValidAndEmpty(y, x));
        map[y][x] = DOT_X;
    }

    private static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValidAndEmpty(y, x));
        map[y][x] = DOT_O;
    }

    private static void advancedAiTurn() {
        int[] a;
        do {
            a = findAIMove();
        } while (!isCellValidAndEmpty(a[1], a[0]));
        map[a[1]][a[0]] = DOT_O;
    }

    private static int[] findAIMove() {
        int x = random.nextInt(SIZE);
        int y = random.nextInt(SIZE);
        int[] a = null;
//        int[] a = detectHorizontalPotencial(DOT_X);
//        if (a != null) {
//            return a;
//        }
//        a = detectHorizontalPotencial(DOT_O);
//        if (a != null) {
//            return a;
//        }
        a = detectVerticalPotencial(DOT_X);
        if (a != null) {
            return a;
        }
        a = detectVerticalPotencial(DOT_O);
        if (a != null) {
            return a;
        }
        a = new int[]{x, y};
        return a;
    }

    private static boolean isCellValidAndEmpty(final int y, final int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    private static boolean isCellValid(final int y, final int x, final char c) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == c || map[y][x] == DOT_EMPTY;
    }

    private static boolean isFull() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (map[y][x] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(final char c) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (checkVertical(c, x, y)) {
                    return true;
                }
                if (checkHorizontal(c, x, y)) {
                    return true;
                }
                if (checkDiagonal(c, x, y)) {
                    return true;
                }
                if (checkReverseDiagonal(c, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVertical(final char c, final int x, final int y) {
        if (y + DOTS_TO_WIN - 1 < SIZE) {
            for (int yy = y; yy < DOTS_TO_WIN + y; yy++) {
                if (map[yy][x] != c) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkHorizontal(final char c, final int x, final int y) {
        if (x + DOTS_TO_WIN - 1 < SIZE) {
            for (int xx = x; xx < DOTS_TO_WIN + x; xx++) {
                if (map[y][xx] != c) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkDiagonal(final char c, final int x, final int y) {
        if (x + DOTS_TO_WIN - 1 < SIZE && y + DOTS_TO_WIN - 1 < SIZE) {
            for (int z = 0; z < DOTS_TO_WIN; z++) {
                if (map[y + z][x + z] != c) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkReverseDiagonal(final char c, final int x, final int y) {
        if (x + DOTS_TO_WIN - 1 < SIZE && y - DOTS_TO_WIN + 1 >= 0) {
            for (int z = 0; z < DOTS_TO_WIN; z++) {
                if (map[y - z][x + z] != c) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static int[] detectHorizontalPotencial(char c) {
        TreeMap<Integer, Integer[]> map = new TreeMap<>();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int counter = 0;
                if (tryFillLineToLeft(x, y, c, counter) + tryFillLineToRight(x + 1, y, c, counter) >= DOTS_TO_WIN) {
                    if (isCellValidAndEmpty(y, x)) {
                        map.put(getFilledSizeByHorizontal(y, c), new Integer[]{x,y});
                        break;
                    }
                }
            }
        }
        return toPrimitive(map.get(map.lastKey()));
    }

    private static int[] toPrimitive(Integer[] IntegerArray) {

        int[] result = new int[IntegerArray.length];
        for (int i = 0; i < IntegerArray.length; i++) {
            result[i] = IntegerArray[i].intValue();
        }
        return result;
    }

    private static int getFilledSizeByHorizontal(int y, char c) {
        int maxFilled = 0;
        int counter = 0;
        for (int xx = 0; xx < SIZE; xx++) {
            if (map[y][xx] == c) {
                counter++;
                if (counter > maxFilled) {
                    maxFilled = counter;
                }
            } else {
                counter = 0;
            }
        }
        return maxFilled;
    }

    private static int getFilledSizeByVertical(int x, char c) {
        int maxFilled = 0;
        int counter = 0;
        for (int yy = 0; yy < SIZE; yy++) {
            if (map[yy][x] == c) {
                counter++;
                if (counter > maxFilled) {
                    maxFilled = counter;
                }
            } else {
                counter = 0;
            }
        }
        return maxFilled;
    }

    private static int tryFillLineToLeft(int x, int y, char c, int counter) {
        if (isCellValid(y, x, c)) {
            counter++;
            counter = tryFillLineToLeft(x - 1, y, c, counter);
        }
        return counter;
    }

    private static int tryFillLineToRight(int x, int y, char c, int counter) {
        if (isCellValid(y, x, c)) {
            counter++;
            counter = tryFillLineToRight(x + 1, y, c, counter);
        }
        return counter;
    }

    private static int tryFillLineToUp(int x, int y, char c, int counter) {
        if (isCellValid(y, x, c)) {
            counter++;
            counter = tryFillLineToLeft(x, y - 1, c, counter);
        }
        return counter;
    }

    private static int tryFillLineToDown(int x, int y, char c, int counter) {
        if (isCellValid(y, x, c)) {
            counter++;
            counter = tryFillLineToRight(x, y + 1, c, counter);
        }
        return counter;
    }

    private static int[] detectVerticalPotencial(char c) {
        TreeMap<Integer, Integer[]> map = new TreeMap<>();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                int counter = 0;
                if (tryFillLineToUp(x, y, c, counter) + tryFillLineToDown(x, y + 1, c, counter) >= DOTS_TO_WIN) {
                    if (isCellValidAndEmpty(y, x)) {
                        map.put(getFilledSizeByVertical(x, c), new Integer[]{x,y});
                        break;
                    }
                }
            }
        }
        return toPrimitive(map.get(map.lastKey()));
    }

}

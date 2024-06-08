import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Board {
// игровое поле 15x15
private static final int SIZE = 15;
    String [][] grid = new String[SIZE][SIZE];
    private ArrayList<Point> winCombination = new ArrayList<>();
    private String empty = ".";
    private final int winLength;

    public Board() {
        winLength = 5;
        // Вызываем метод инициализации
        initializeBoard();
    }
    public Board(int winLength) {
        this.winLength = winLength;
        // Вызываем метод инициализации
        initializeBoard();
    }

    public String getEmpty() {
        return empty;
    }

    public String[][] getBoard() {
        return grid;
    }

    public void setEmpty(String empty){
        this.empty = empty;
        initializeBoard();
    }
    public static int getSIZE() {
        return SIZE;
    }
    //инициализируем игровое поле, устанавливаем все значения двумерного массива в начальный символ
 public void initializeBoard() {
     for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
            grid[row][col] = empty;
         }
     }
 }
 //проверяем, свободна ли клетка на поле по указанным координатам.
    boolean isCellEmpty(int row, int col) {
        return grid[row][col].equals("-");
    }

    //проверяем, привел ли последний ход к победе.
    public boolean isWinningMove(String symbol, int row, int col) {
        // Проверяем выигрышные комбинации, начиная с последнего хода.

        // Проверка для горизонтали
        if (countConsecutiveStones(symbol, row, col, 0, 1) >= 5) {
            return true;
        }
        // Проверка для вертикали
        if (countConsecutiveStones(symbol, row, col, 1, 0) >= 5) {
            return true;
        }
        // Проверка для главной диагонали
        if (countConsecutiveStones(symbol, row, col, 1, 1) >= 5) {
            return true;
        }
        // Проверка для побочной диагонали
        return countConsecutiveStones(symbol, row, col, 1, -1) >= 5;
    }

    private int countConsecutiveStones(String playerSymbol, int row, int col, int rowDelta, int colDelta) {
        int count = 1; // Начинаем с 1, так как считаем последний поставленный камень
        int r, c;

        // Подсчет камней в одном направлении
        r = row + rowDelta;
        c = col + colDelta;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && Objects.equals(grid[r][c], playerSymbol)) {
            count++;
            r += rowDelta;
            c += colDelta;
        }

        // Подсчет камней в противоположном направлении
        r = row - rowDelta;
        c = col - colDelta;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && Objects.equals(grid[r][c], playerSymbol)) {
            count++;
            r -= rowDelta;
            c -= colDelta;
        }

        return count;
    }

    //проверяем, заполнены ли все клетки на поле.
      boolean isBoardFull(){
          for (int row = 0; row < SIZE; row++) {
              for (int col = 0; col < SIZE; col++) {
                  if (grid[row][col].equals("-")) { // Если нашли пустую ячейку, значит доска не заполнена
                      return false;
                  }
              }
          }
          return true; // Все ячейки заполнены
    }
}

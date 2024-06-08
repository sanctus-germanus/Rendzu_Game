import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Player {

    //строка с именем игрока.
    private String name;
    private Board board;
    //символ, которым игрок будет играть.
    private String symbol;
public Player (String name){
    this.name = name;
}

    //конструктор, устанавливающий имя и символ для игрока.
    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    // геттер имени игрока.
    public String getName() {
        return name;
    }

    //  геттер символа игрока.
    public String getSymbol() {
        return symbol;
    }

    //просим игрока ввести координаты для хода и осуществляем ход на поле.
    public Point makeMove(Board board) {
        int row = -1, col = -1;
        boolean isInputValid;
        while (true){
        if (row < 0 || row >= Board.getSIZE() || col < 0 || col >= Board.getSIZE()) {
            break;
        }
        if (board.grid[row][col] != board.getEmpty()) {
            break;
        }
        board.grid[row][col] = symbol;} // Выполнение хода на доске
        return new Point(row, col); // Возвращаем координаты хода
    }
        private boolean isValidMove (Board board, int row, int col){
            return ((row >= 0 && row < Board.getSIZE()) && (col >= 0 && col < Board.getSIZE()) && board.isCellEmpty(row, col));
        }

}


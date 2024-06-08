import java.awt.*;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public Game(Player player1, Player player2) {
        // инициализация игровой доски
        board = new Board();

        // присваиваем ссылки на игроков
        this.player1 = player1;
        this.player2 = player2;

        // установка первого игрока, который будет ходить
        currentPlayer = this.player1;
    }
    public Player getCurrentPlayer () {
        return currentPlayer;
    }
    public Board getBoard () {
        return board;
    }

    //основной цикл игры, в котором происходит ввод игроков и обновление состояния игры до её завершения.
    void play () {

        board.initializeBoard();

        while (true) {
            // Игровой цикл
            Point move = currentPlayer.makeMove(board);
            // Проверка условия выхода из игры
            if (move.equals(new Point(-1, -1))) {
                // текущий игрок решил выйти, объявить другого игрока победителем
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                System.out.println("Игрок " + currentPlayer.getName() + " выиграл, так как другой игрок вышел!");
                break;
            }

            // Проверка на победу
            if (board.isWinningMove(currentPlayer.getSymbol(), move.x, move.y)) {
                System.out.println("Поздравляем! " + currentPlayer.getName() + " выиграл!");
                break; // Завершаем игру, если один из игроков выиграл
            }

            // Проверка на ничью
            if (board.isBoardFull()) {
                System.out.println("Ничья! Нет свободных клеток на доске.");
                break;
            }

            switchPlayer(); // Переключение игрока после каждого хода
        }

        // По окончанию игры
        System.out.println("Конец игры!");
    }
    void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

    }
}

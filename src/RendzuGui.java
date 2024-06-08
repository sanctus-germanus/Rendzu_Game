import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RendzuGui {
    private Game game;
    private JFrame gameFrame;
    // Стартовое окно приложения
    private JPanel btPanel;
    // Игровое поле в виде двумерного массива кнопок
    private JButton[][] board;
    // Панель управления
    private JPanel ctrlPanel;
    // Метка первого игрока
    private JLabel player1NameLabel;
    // Метка второго игрока
    private JLabel player2NameLabel;
    // Кнопка "Сдаться"
    private JButton giveUpBt;
    // Кнопка перезапуска игры
    private JButton restartBt;
    private Clip ambientClip;
    // Звук окончания игры
    private Clip endGameClip;
    // Звук совершения хода
    private Clip hitBtClip;
    // Стилистика приложения
    private Color greenColor = new Color(50,205,50);
    private Color redColor = new Color(200,34,34);
    private Border blackBorder = BorderFactory.createLineBorder(Color.black,5,true);
    private final Font mainFont = new Font("Comic Sans MS", Font.BOLD, 22);

    public RendzuGui() {
        // Настраиваем главное окно приложения
        gameFrame = new JFrame();
        gameFrame.setTitle("Рэндзю");
        gameFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        gameFrame.setSize(1250, 950);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Первоначально деактивируем окноп
        gameFrame.setEnabled(false);
        // Настраиваем панель с игровым поле
        btPanel = new JPanel();
        btPanel.setLayout(new GridLayout(15,15));
        btPanel.setPreferredSize(new Dimension(900,900));
        game.getBoard().initializeBoard(); // Настраиваем игровое поле
        initClips(); // Настраиваем звук
        // Настраиваем панель управления
        ctrlPanel = new JPanel();
        ctrlPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        ctrlPanel.setPreferredSize(new Dimension(300,900));
        // Настраиваем метку первого игрока
        player1NameLabel = new JLabel("X: ");
        player1NameLabel.setPreferredSize(new Dimension(250,100));
        player1NameLabel.setFont(mainFont);
        player1NameLabel.setBorder(blackBorder);
        player1NameLabel.setOpaque(true);
        ctrlPanel.add(player1NameLabel);
        // Настраиваем метку второго игрока
        player2NameLabel = new JLabel("O: ");
        player2NameLabel.setPreferredSize(new Dimension(250,100));
        player2NameLabel.setFont(mainFont);
        player2NameLabel.setBorder(blackBorder);
        player2NameLabel.setOpaque(true);
        ctrlPanel.add(player2NameLabel);
        // Настраиваем кнопку "Сдаться"
        giveUpBt = new JButton("Сдаться");
        giveUpBt.setFont(mainFont);
        giveUpBt.setFocusable(false);
        giveUpBt.setBackground(Color.white);
        giveUpBt.setPreferredSize(new Dimension(250,100));
        giveUpBt.addActionListener(e -> {
            // Сдавшийся игрок будет выделен красным цветом
            if (game.getCurrentPlayer().getSymbol().equals("X")) {
                player1NameLabel.setBackground(redColor);
            } else {
                player2NameLabel.setBackground(redColor);
            }
            // Запускаем логику конца игры
            endGame(game.showResult());
        });
        ctrlPanel.add(giveUpBt);

    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    private void endGame(String result) {
        // Воспроизводим звук конца игры
        if (endGameClip != null) {
            endGameClip.stop();
            endGameClip.setFramePosition(0);
            endGameClip.start();
        }
        // Выделяем цветом победную комбинацию
        for (Point p : game.getBoard().isWinningMove(game.getCurrentPlayer().getSymbol(), int row, int col)) {
            row = (int) p.getX();
            col = (int) p.getY();
            board[row][col].setBackground(greenColor);
        }
        // Деактивируем игровое поле
        forEachBoardBt(bt -> {
            bt.setEnabled(false);
        });
        // Деактивируем кнопку "Сдаться"
        giveUpBt.setEnabled(false);
        // Выводим диалоговое окно с результатом игры
        JOptionPane.showMessageDialog(gameFrame, result,
                "Конец игры!", JOptionPane.INFORMATION_MESSAGE);
    }
    // Метод отображения игрового поля согласно модели
    public void printBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j].setText(String.valueOf(game.getBoard().getBoard()[i][j]));
            }
        }
    }

}

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private RendzuGui rendzuGui;
    private  Player player1;
     private  Player player2;
    private Game game;
    private JLabel welcomeLabel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JTextField player1Field;
    private JTextField player2Field;
    private JButton startButton;
    private Font mainFont = new Font("Comic Sans MS", Font.BOLD, 22);
    private Color greenColor = new Color(50,205,50);


    public StartFrame() throws HeadlessException {
        rendzuGui = new RendzuGui();
        this.setTitle("Добро пожаловать!");
        this.setSize(800, 600);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        // Делаем окно поверх других окон
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        // Настраиваем метку с правилами
        welcomeLabel = new JLabel();
        // Для форматирования текста можно использовать язык разметки HTML
        welcomeLabel.setText("<html><div style='text-align: center;'>Добро пожаловать в игру Рэндзю!" +
                "<br>Цель игры - собрать пять своих символов в ряд" +
                "<br> по горизонтали, вертикали или диагонали." +
                "<br>Игроки ходят по очереди, ставя крестики и " +
                "<br>нолики на свободные клетки поля." +
                "<br>Игра заканчивается ничьей, когда поле полностью заполнено," +
                "<br>а выигрышная комбинация так и не была получена." +
                "<br>" +
                "<br>Желаем вам удачи и приятной игры!<br><br></div></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(mainFont);
        // Настраиваем метку текстового поля
        player1Label = new JLabel("Имя первого игрока: ");
        player1Label.setPreferredSize(new Dimension(250,50));
        player1Label.setFont(mainFont);
        // Настраиваем текстовое поле
        player1Field = new JTextField();
        player1Field.setPreferredSize(new Dimension(350,50));
        player1Field.setFont(mainFont);
        // Настраиваем метку текстового поля
        player2Label = new JLabel("Имя второго игрока: ");
        player2Label.setPreferredSize(new Dimension(250,50));
        player2Label.setFont(mainFont);
        // Настраиваем текстовое поле
        player2Field = new JTextField();
        player2Field.setPreferredSize(new Dimension(350,50));
        player2Field.setFont(mainFont);
        // Настраиваем кнопку начала игры
        startButton = new JButton("Начать игру");
        startButton.setFont(mainFont);
        startButton.setPreferredSize(new Dimension(250,50));
        startButton.setFocusable(false);
        startButton.setBackground(greenColor);
        startButton.addActionListener(e -> {
                             // Если введено пустое имя, то выводим сообщение
        if (player1Field.getText().equals("") || player2Field.getText().equals("")) {
                              JOptionPane.showMessageDialog(this,
                                         "Имя игрока не может быть пустым!",
                                         "Введите имена игроков", JOptionPane.WARNING_MESSAGE);
                             } else {   
                    // Сохраняем введённые имена игроков
                    String name1 = player1Field.getText();
                    String name2 = player2Field.getText();
                    // Инициализируем метки игроков
                    player1Label.setText("X: " + name1);
                    player1Label.setBackground(greenColor);
                    player2Label.setText("O: " + name2);
                    player2Label.setBackground(null);
                    player1 = new Player(name1);
                    player2 = new Player(name2);
                    game = new Game(player1, player2);
                     // Задаём символ для пустых клеток
                     game.getBoard().setEmpty(" ");
                      this.dispose();
                      rendzuGui.printBoard();
                      rendzuGui.getGameFrame().setEnabled(true);
                      rendzuGui.getGameFrame().setVisible(true); }});
            this.add(welcomeLabel);
            this.add(player1Label);
            this.add(player1Field);
            this.add(player2Label);
            this.add(player2Field);
            this.add(startButton);
            this.setLocationRelativeTo(rendzuGui.getGameFrame());
            this.setVisible(true);  }}
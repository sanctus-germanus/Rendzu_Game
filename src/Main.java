import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        System.out.printf("Два игрока (крестики и нолики), поочередно размещают свой символ, по одному за ход, \n\n" +
                "в пустую ячейку игровой доски, которая состоит из квадрата площадью 15 горизонтальных и 15\n\nвертикальных ячеек." +
                "\n\nВыигрышной считается комбинация из 5 символов одного игрока подряд - по горизонтали, диагонали, " +
                "\n\nили вертикали.\n\n\n%n");


        Scanner scanner = new Scanner(System.in);

        System.out.println("Первый игрок, введите ваше имя");
        String name1 = scanner.nextLine();

        System.out.println("Второй игрок, введите ваше имя");
        String name2 = scanner.nextLine();

        String symbol1 = "";
        String symbol2 = "";

        while (!"X".equals(symbol1) && !"O".equals(symbol1)) {
            System.out.println(name1 + ", введите, чем будете играть (X или O?)");
            symbol1 = scanner.nextLine().toUpperCase();

            if ("X".equals(symbol1)) {
                symbol2 = "O";
            } else if ("O".equals(symbol1)) {
                symbol2 = "X";
            } else {
                System.out.println("Некорректный выбор, попробуйте ещё раз.");
            }
        }

        Player player1 = new Player(name1,symbol1);
        Player player2 = new Player(name2,symbol2);
        Game game = new Game(player1, player2);
        game.play();
    }
}
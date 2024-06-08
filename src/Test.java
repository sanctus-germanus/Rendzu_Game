public class Test {
    public static void main(String[] args) {
        System.out.println("Два игрока (крестики и нолики), поочередно размещают свой символ, по одному за ход, \n" +
                "\n" +
                "в пустую ячейку игровой доски, которая состоит из квадрата площадью 15 горизонтальных и 15\n" +
                "\n" +
                "вертикальных ячеек.\n" +
                "\n" +
                "Выигрышной считается комбинация из 5 символов одного игрока подряд - по горизонтали, диагонали, \n" +

                "\n" +
                "или вертикали." +
                "\n" + "\n" + "\n"
        );

        int grid [][] = new int[15][15];
        for (int row = 0; row < 15; row++) {
            if (row > 0) {
                for (int col = 0; col < 15; col++) {
                    System.out.print("---");
                    if (col < 14) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
            for (int col = 0; col < 15; col++) {
                System.out.print(" " + grid[row][col] + " ");
                if (col < 15 - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();
        }
    }
}

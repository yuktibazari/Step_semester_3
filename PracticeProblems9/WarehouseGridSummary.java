public class Main {
    static int[] warehouseSummary(int[][] grid) {
        int total = 0;
        int max = -1;
        int row = 0, col = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];

                if (grid[i][j] > max) {
                    max = grid[i][j];
                    row = i;
                    col = j;
                }
            }
        }

        return new int[]{total, row, col};
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        int[] result = warehouseSummary(grid);
        System.out.println("(" + result[0] + ", (" +
                result[1] + ", " + result[2] + "))");
    }
}
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // 1 == block side
    // 2 == open side
    // 3 ==  full open side
    private final int num;
    private int[][] grid;
    private  WeightedQuickUnionUF uf;


    public static int random_num(int blockNums, int openNums) {
        int ran = StdRandom.uniform(0,1);
        double ratio = openNums / blockNums;
        if (ran < ratio ) {return 1;}
        else {return 2;}
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int blockNums, int openNums) {
        if (blockNums <= 0) throw new IllegalArgumentException("Invalid input: n must > 0 !");
        this.num = blockNums;
        grid = new int[this.num][this.num];

        //Put the open in block randomly
        for (int i = 0; i < this.num; i++) {
            for (int j = 0; j < this.num; j++) {
                grid[i][j] = random_num(blockNums, openNums);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (grid[row][col] == 1) {
            grid[row][col] = 2;
        }
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (grid[row][col] == 2) {
            return true;
        } else {
            return false;
        }
    }



    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (grid[row][col] == 3) {
            return true;
        } else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i <= this.num; i++ ) {
            for (int j = 0; j <= this.num; j++) {
                if (grid[i][j] == 2) {
                    count ++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean isPercolates = false;
        int currentRow = 0;

        for (int k = 0; k < this.num; k++) {
            if (k == 0) {
                for (int l = 0; l < this.num; l++) {
                    if (grid[0][l] == 2 ) {
                        grid[0][l] = 3;
                        currentRow = l;
                    }
                }
            } else {
                if (currentRow == 0) return isPercolates;
                boolean foundThisrow = false;
                if (currentRow != this.num) {
                    for (int m = currentRow - 1; m <= currentRow + 1; m++) {
                        System.out.println(m);
                        System.out.println(k);
                        System.out.println("+++");
                        if (grid[k][m] == 2) {
                            grid[k][m] = 3;
                            foundThisrow = true;
                        }
                    }
                    if (foundThisrow == false) {
                        return isPercolates;
                    }
                    if (k == this.num && foundThisrow == true) {
                        isPercolates = true;
                    }
                }
            }
        }
        return isPercolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        int blockNums = Integer.parseInt(args[0]);
        int openNums = Integer.parseInt(args[1]);

        Percolation per = new Percolation(blockNums, openNums);
        System.out.print(per.percolates());
    }
}

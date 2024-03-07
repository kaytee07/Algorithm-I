import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF wqf;

    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        bottom = size * size + 1;
        wqf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        openSites = 0;
    }

    public void open(int row, int col){
        checkException(row, col);
        opened[row -1][col - 1] = true;
        ++openSites;

        if(row == 1){
            wqf.union(getQuickFindIndex(row, col), TOP);
        }

        if (row == size){
            wqf.union(getQuickFindIndex(row, col), bottom);
        }

        if(row > 1 && isOpen(row-1, col)){
            wqf.union(getQuickFindIndex(row, col), getQuickFindIndex(row-1, col));
        }

        if (row > 1 && isOpen(row + 1, col)) {
            wqf.union(getQuickFindIndex(row, col), getQuickFindIndex(row+1, col));
        }

        if (row > 1 && isOpen(row, col - 1)) {
            wqf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
        }

        if (row > 1 && isOpen(row, col + 1)) {
            wqf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col){
        checkException(row, col);
        return opened[row - 1][col -1];
    }

    private void checkException(int row, int col){
        if (row <= 0 || row > size || col <= 0 || col > size){
            throw new IllegalArgumentException();
        }
    }

    public int numOfOpenSites(){
        return openSites;
    }

    public boolean isFull(int row, int col){
        if ((row > 0 && row <= size) && (col > 0 && col <= size)){
            return wqf.find(TOP) == wqf.find(getQuickFindIndex(row, col));
        } else throw new IllegalArgumentException();
    }

    public int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    public boolean percolates(){
        return wqf.find(TOP) == wqf.find(bottom);
    }
}
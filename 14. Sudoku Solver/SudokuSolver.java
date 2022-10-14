// Write a program to solve a Sudoku puzzle by filling the empty cells.

// A sudoku solution must satisfy all of the following rules:

// Each of the digits 1-9 must occur exactly once in each row.
// Each of the digits 1-9 must occur exactly once in each column.
// Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
// The '.' character indicates empty cells.

// ==================================================================================================================

public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };
        if(solve(board)){
            display(board);
        }
        else {
            System.out.println("Cannot solve");
        }
    }


    static boolean solve(int[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;

        boolean emptyLeft = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0){
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            //if you found some empty element in row, then break
            if(emptyLeft == false) {
                break;
            }
        }
        if(emptyLeft == true){
            return true;
        }

        //backtrack
        for (int number = 1; number <= 9; number++) {
            if(isSafe(board, row, col, number)){
                board[row][col] = number;
                if(solve(board)) {
                    return true;
                }else{
                    //backtrack
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private static void display(int[][] board) {
        for (int[] row:board) {
            for (int num:row) {
                System.out.print(num+ " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int[][] board, int row, int col, int num) {
        //check row
        for (int i = 0; i < board.length; i++) {
            if(board[row][i] == num) {
                return false;
            }
        }
        //check col
        for (int[] nums : board) {
            if(nums[col] == num) {
                return false;
            }
        }
        //check sub square matrix
        int sqrt = (int)(Math.sqrt(board.length));
        int rowStart = row - row%sqrt;
        int colStart = col - col%sqrt;
        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if(board[r][c] == num){
                    return false;
                }
            }
        }
        return true;
    }

}

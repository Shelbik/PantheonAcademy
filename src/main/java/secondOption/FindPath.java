package secondOption;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class FindPath {

    private static final char FREE = '.';
    private static final char BLOCKED = '#';
    private static final char START = 'S';
    private static final char TARGET = 'X';
    private static final char[] DIRECTIONS = {'u', 'd', 'l', 'r'};
    private static final int[][] MOVES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        AbstactFilePathInputReader inputReader;

        if (args.length > 0) {
            // ak mame v konfiguracii link na file
            inputReader = new FindPathInputReaderFile(args[0]);
        } else {
            System.out.println("If your input is ready press double time ENTER");
            //ak mame prazdnu konfiguraciu

            inputReader = new FindPathInputReaderStdIn();
        }

        try {
            char[][] maze = inputReader.readMaze();
            String path = findPath(maze);
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findPath(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        int startRow = -1, startCol = -1;
        int targetRow = -1, targetCol = -1;

       //inicializacia start a target bod
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == START) {
                    startRow = r;
                    startCol = c;
                } else if (maze[r][c] == TARGET) {
                    targetRow = r;
                    targetCol = c;
                }
            }
        }

        if (startRow == -1 || targetRow == -1) {
            return "Error: Start or target not found";
        }

       //cesta, visited
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        queue.offer(new int[]{startRow, startCol});
        pathQueue.offer("");


        //start bod is visited
        visited[startRow][startCol] = true;


        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            String path = pathQueue.poll();

            int r = position[0];
            int c = position[1];

            if (r == targetRow && c == targetCol) {
                if (path != null) {
                    return formatPath(path);
                }
            }

            //Breadth-first search algorithm
            for (int i = 0; i < MOVES.length; i++) {
                int newRow = r + MOVES[i][0];
                int newCol = c + MOVES[i][1];
                char direction = DIRECTIONS[i];

                if (isValidMove(newRow, newCol, rows, cols, maze, visited)) {
                    queue.offer(new int[]{newRow, newCol});
                    pathQueue.offer(path + direction);
                    visited[newRow][newCol] = true;
                }
            }
        }

        return "Error: No path found";
    }

    //overenie ci bod je mimo rozsahu alebo blocked alebo visited
    private static boolean isValidMove(int row, int col, int rows, int cols, char[][] maze, boolean[][] visited) {
        return row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col] && maze[row][col] != BLOCKED;
    }

    //vypis cesty
    private static String formatPath(String path) {
        StringBuilder formattedPath = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            if (i > 0) {
                formattedPath.append(',');
            }
            formattedPath.append(path.charAt(i));
        }
        return formattedPath.toString();
    }
}

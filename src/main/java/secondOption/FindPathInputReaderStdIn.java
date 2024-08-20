package secondOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindPathInputReaderStdIn extends AbstactFilePathInputReader {
    @Override
    public char[][] readMaze() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            sb.append(line).append("\n");
        }

        String[] linesChars = sb.toString().split("\n");
        char[][] maze = new char[linesChars.length][0];
        for (int i = 0; i < linesChars.length; i++) {
            maze[i] = linesChars[i].toCharArray();
        }
        return maze;
    }
}

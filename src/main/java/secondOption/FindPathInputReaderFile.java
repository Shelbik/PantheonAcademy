package secondOption;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindPathInputReaderFile extends AbstactFilePathInputReader {
    private final String filePath;

    public FindPathInputReaderFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public char[][] readMaze() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
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

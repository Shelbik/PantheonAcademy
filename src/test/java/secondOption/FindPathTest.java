package secondOption;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindPathTest {

    @Test
    public void testFindPath() {
        char[][] maze = {
                "....................................".toCharArray(),
                "..S...#......................#......".toCharArray(),
                "......#......................#......".toCharArray(),
                ".............................#......".toCharArray(),
                "....................................".toCharArray(),
                "....................................".toCharArray(),
                "..............#.....................".toCharArray(),
                "............#.......................".toCharArray(),
                "..........#.........................".toCharArray(),
                "....................................".toCharArray(),
                ".....................#..........#...".toCharArray(),
                ".....................#....X.....#...".toCharArray(),
                ".....................#..........#...".toCharArray(),
                "....................................".toCharArray()
        };

        String path = FindPath.findPath(maze);
        System.out.println("Calculated path: " + path); // Debug output
        assertEquals("d,d,d,d,d,d,d,d,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,d,d,r,r,r,r", path);
    }
}

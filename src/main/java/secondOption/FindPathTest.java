package secondOption;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

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
        Assertions.assertEquals("d,d,d,d,d,d,d,d,d,d,d,d,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,u,u,u", path);
    }

}

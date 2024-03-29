import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRobotTest {

    @Test
    /**
     *
     * It checks if we can create a valid position class by sending some parameters to it.
     *
     */
    public void testPosition(){
        Position pos = null;
        pos = new Position(3, 4);
        assertNotEquals(pos,null);
    }
    @Test
    /*
            testmem.txt
            *S*********************
            *                     *
            * *   *               *
            * *   *           *   *
            * *   *           *   *
            * *G  *           *   *
            ***********************

   We will check the start position index in the maze.

     */
    public void testMaze() throws IOException {
        Maze maze = new Maze(new Scanner(new File("testmem.txt")));
        Position pos = null;
        pos = maze.getStart();
        assertEquals(pos.getX(),1);
        assertEquals(pos.getY(),0);
    }

    @Test
    /**
     *         testmem.txt
     *             *S*********************
     *             *                     *
     *             * *   *               *
     *             * *   *           *   *
     *             * *   *           *   *
     *             * *G  *           *   *
     *             ***********************
     *
     *      It checks whether the robot can do the backtacking successfully or not
     *      when the robot found a deadend in the maze. The memory robot
     *      constructed in such a manner that first it checks if it can move to north, south, east and west
     *      respectively.
     */
    public void checkBackTracking() throws Exception {
        Maze maze = new Maze(new Scanner(new File("testmem.txt")));
        MemoryRobot memRobot = new MemoryRobot(maze);
        memRobot.move();
        int currentXaxis = memRobot.getPosition().getX();
        int currentYaxis = memRobot.getPosition().getY();
        for(int i = 0; i < 4; i++){
           assertEquals(memRobot.getPosition().getY(),currentYaxis);
           assertEquals(memRobot.getPosition().getX(),currentXaxis);
           memRobot.move();
           currentYaxis++;
        }
       // System.out.println("position x: == "+memRobot.getPosition().getX()+"position y: == "+memRobot.getPosition().getY());
        for (int i = 0; i < 4; i++){
            assertEquals(memRobot.getPosition().getY(),currentYaxis);
            assertEquals(memRobot.getPosition().getX(),currentXaxis);
            memRobot.move();
            currentYaxis--;
        }
       // System.out.println("position x: == "+memRobot.getPosition().getX()+"position y: == "+memRobot.getPosition().getY());
    }

    /**
     *
     * The purpose of that is to check whether the memory robot visits those positions which
     * have been previously visited or not.
     * A memory robot always prioritizes those positions which have not visited yet.
     *
     *
     * positionChecker:

     *  ***********************
     *  *                     *
     *  *         S           *
     *  *                     *
     *  * *   *           *   *
     *  * *G  *           *   *
     *  ***********************
     *
     */
    @Test
    public void checkNextMovablePosition() throws Exception {
        Maze maze = new Maze(new Scanner(new File("positionChecker")));
        MemoryRobot memoryRobot = new MemoryRobot(maze);
        Position pos = memoryRobot.getPosition();
        memoryRobot.markingApositionAsVisited(pos);
        memoryRobot.markingApositionAsVisited(pos.getPosToEast());
        memoryRobot.markingApositionAsVisited(pos.getPosToNorth());
        memoryRobot.markingApositionAsVisited(pos.getPosToWest());
        memoryRobot.move();
        assertEquals(memoryRobot.getPosition().getX(),pos.getPosToSouth().getX());
        assertEquals(memoryRobot.getPosition().getY(),pos.getPosToSouth().getY());
    }

    /**
     *
     * The robot will explore the movable positions by checking north, south, east and west
     * in orderly.
     * If the robot follows this manner, then it will take 36 steps from the start position
     * to goal position.
     *
     * MemoryRobot.txt
     *
     *      ********* ******************
     *      **   G*
     *      ** **** ****
     *      ** **   ****
     *      ** **** **** *
     *      ** ****S****
     *      ** ********* *
     *      **           ****************
     *      ********************
     *      ***************************
     *
     **/
    @Test
    public void countTheRobotsStepsInTheMaze () throws Exception {
        Maze maze = new Maze(new Scanner(new File("MemoryRobot.txt")));
        MemoryRobot memRobot = new MemoryRobot(maze);
        int countSteps = 0;
        while(!memRobot.hasReachedGoal()){
            memRobot.move();
            countSteps++;
        }
        assertTrue(memRobot.hasReachedGoal());
        assertEquals(36,countSteps);
    }

}


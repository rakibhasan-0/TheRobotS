import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class RightHandRuleRobotTest {

    /**
     *
     * rightHand.txt
     *      **************************
     *      *          G             *
     *      ****************         *
     *      *              *        **
     *      *    *   *     **** *   *
     *      *    *   *        * *   *
     *      *    *   *        *S*   *
     *      **************************
     */
    @Test
    public void checkPositionAndMaze() throws Exception {
        Maze maze = new Maze(new Scanner(new File("rightHand.txt")));
        RightHandRuleRobot robot = new RightHandRuleRobot(maze);
        Position pos = robot.getPosition();
        assertEquals(pos,maze.getStart());
        assertEquals(pos.getX(),19);
        assertEquals(pos.getY(),6);
    }

    /**
     * rightHand.txt
     *      **************************
     *      *          G             *
     *      ****************         *
     *      *              *        **
     *      *    *   *     **** *   *
     *      *    *   *        * *   *
     *      *    *   *        *S*   *
     *      **************************
     */
    @Test
    public void verifyRightHandRule() throws Exception {
        Maze maze = new Maze(new Scanner(new File("rightHand.txt")));
        RightHandRuleRobot robot = new RightHandRuleRobot(maze);
        // As we can see from the maze, that robot only can go north for 3 steps.
        for(int i = 0; i < 3; i++){
            robot.move();
        }
        // after the first three steps, the robot supposed to move east for 2 steps.
        Position pos = null;
        pos = robot.getPosition();
        robot.move();
        assertEquals(pos.getPosToEast(),robot.getPosition());
        robot.move();
        //the roobot supposed to move south for 3 steps.
        for(int i = 0; i < 3; i++){
            pos = robot.getPosition();
            robot.move();
            assertEquals(robot.getPosition(),pos.getPosToSouth());
         }
        // the robot supposed to move east for 2 steps.
        for (int i = 0; i < 2; i++){
            pos = robot.getPosition();
            robot.move();
            assertEquals(robot.getPosition(),pos.getPosToEast());
        }
        // the robot supposed to move north for 4 steps.
        for(int i = 0; i <4; i++){
            pos = robot.getPosition();
            robot.move();
            assertEquals(robot.getPosition(),pos.getPosToNorth());
        }
        // The robot should move to one step to the east, then one step to the north.
        pos = robot.getPosition();
        robot.move();
        assertEquals(pos.getPosToEast(),robot.getPosition());
        pos = robot.getPosition();
        robot.move();
        assertEquals(pos.getPosToNorth(),robot.getPosition());
        // As we can see that the robot will move to the west positions until it reaches the goal.
        while(robot.hasReachedGoal()){
            pos = robot.getPosition();
            robot.move();
            assertEquals(robot.getPosition(),pos.getPosToWest());
        }
    }


    /**
     * positionChecker:
     *
     *      ***********************
     *      *                     *
     *      *         S           *
     *      *                     *
     *      * *   *           *   *
     *      * *G  *           *   *
     *      ***********************
     */
    @Test
    public void checkAPositionWhereExistsNoWall() throws Exception {
        Maze maze = new Maze(new Scanner(new File("positionChecker")));
        RightHandRuleRobot robot = new RightHandRuleRobot(maze);
        Exception exception = assertThrows(Exception.class, robot::move);
        assertEquals("the robot can not move",exception.getMessage());
    }


    /**
     * deadEnds.txt:

     *      ********** *****************
     *      **   G*
     *      ** **** **** *
     *      *  **** **** *
     *      ** **** ***
     *      *  ****S**** *
     *      ** *** ***** *
     *      *            ****************
     *      ****** *** *********
     *      ***************************
     *
     */
    @Test
    public void stepCounts() throws Exception {
        Maze maze = new Maze(new Scanner(new File("deadEnds.txt")));
        RightHandRuleRobot robot = new RightHandRuleRobot(maze);
        int countSteps = 0;
        while(!robot.hasReachedGoal()){
            robot.move();
            countSteps++;
        }
        assertEquals(38,countSteps);
    }
}

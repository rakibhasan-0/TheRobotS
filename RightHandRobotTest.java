import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A test program to check whether the robot reaches the goal by traversing in the maze.
 * @author Gazi Md Rakibul Hasan
 */
public class RightHandRobotTest {
    private RightHandRuleRobot robot;
    private Maze maze;
    /*
     * In that function, the robot will traverse the maze iteratively,
     * and the robot's current position will be printed.
     */
    public void traverseInMaze () {
        int count = 0;
        Position pos = new Position(4, 10);
        if(maze.isMovable(pos)){
            System.out.println("okay Nice");
            System.out.println("pos:: x == " +pos.getY() +"  y == "+pos.getX());
        }
        Position pos1 = new Position(2, 11);
        if(maze.isMovable(pos1)){
            System.out.println("okay not Nice");
        }
        while (!robot.hasReachedGoal()){
            System.out.print("Current ");
            System.out.println(toString());
            // System.out.println("dir" + robot.getDirection());
            robot.move();
            count++;
        }
        System.out.print("Goal ");
        System.out.println(toString());
        System.out.println(count);
    }

    public static void main (String[] args) throws Exception {
        RightHandRobotTest test = new RightHandRobotTest();
        test.maze = new Maze(new Scanner(new File(args[0])));
        System.out.println("NUMBER OF ROW == "+test.maze.getNumRows() + " NUMBER OF COLS =="+test.maze.getNumColumns());
        test.robot = new RightHandRuleRobot(test.maze);
        System.out.println("Start Position::"+ test.toString());
        test.traverseInMaze();
        for(int i = 0; i < test.maze.getNumRows(); i++){
            for (int j = 0; j< test.maze.getNumColumns(); j++){
                System.out.print(test.robot.labyrint[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Position to string.
     * @return It converts the robot's current position to a string.
     */
    @Override
    public String toString() {
        return "Position{" +
                "x = " + robot.getPosition().getX() + " ," + "y = " + robot.getPosition().getY()+
                '}';
    }
}



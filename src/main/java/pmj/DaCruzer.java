package pmj;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import java.awt.Color;

/**
 * Implements the DaCruzer robot, which completes a circuit around the arena, 
 * then spins to scan for other robots. 
 * 
 * @author Philip Johnson
 */
public class DaCruzer extends Robot {

  /**
   * Moves to successive corners and rotates the robot to maximize the chances
   * of scanning another robot. 
   * Code courtesy of Kimberly Heu.
   */
  @Override
  public void run() {

    // Get the field's dimensions.
    // Specifies battle field width
    double fieldWidth = getBattleFieldWidth();
    // Specifies battle field Height
    double fieldHeight = getBattleFieldHeight();
    
    // Orient the robot so that it is facing upward.
    turnLeft(getHeading());

    // change bullet color to red.
    setBulletColor(Color.red);
    // change radar color to black.
    setRadarColor(Color.black);

    // Get the robot's current location.
    double robotPositionX = getX();
    double robotPositionY = getY();

    // Move to top left corner
    ahead(fieldHeight - robotPositionY);
    turnLeft(90);
    ahead(robotPositionX);
    turnRight(360);

    // Move to bottom right corner
    turnLeft(180);
    ahead(fieldWidth);
    turnRight(90);
    ahead(fieldHeight);
    turnRight(360);

    // Move to top right corner
    turnLeft(180);
    ahead(fieldHeight);
    turnRight(360);

    // Move to bottom left corner
    turnLeft(180);
    ahead(fieldHeight);
    turnRight(90);
    ahead(fieldWidth);
    turnRight(360);

    // if finished traversing, scan for robots and fire.
    // spins the body of the robot 90 degrees to conduct scans.
    while (true) {
      this.turnLeft(90);
    }
  }

  /**
   * Fires at any robots it finds with a power that is based on distance from target.
   * 
   * @param e contains information about the enemy robot, e.g. its location
   */
  @Override
  public void onScannedRobot(ScannedRobotEvent e) {
    // OVER 9000!!
    double firePower = Math.min(400 / e.getDistance(), 3);
    this.fire(firePower);
  }
}

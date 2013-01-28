package pmj;

import static org.junit.Assert.assertTrue;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.testing.RobotTestBed;

/**
 * Tests that DaCruzer moves to all four corners per its specified strategy.
 *
 * @author Professor Philip Johnson
 */
public class TestDaCruzerMovement extends RobotTestBed {
  /** True if the robot visited this corner during the test case. */
  boolean visitedUpperLeft = false;
  /** True if the robot visited this corner during the test case. */
  boolean visitedUpperRight = false;
  /** True if the robot visited this corner during the test case. */
  boolean visitedLowerLeft = false;
  /** True if the robot visited this corner during the test case. */
  boolean visitedLowerRight = false;

  /**
   * Specifies that SittingDuck and DaCruzer are to be matched up in this test case.
   *
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.SittingDuck,pmj.DaCruzer";
  }

  /**
   * This test runs for 10 round.
   *
   * @return The number of rounds.
   */
  @Override
  public int getNumRounds() {
    return 10;
  }

  /**
   * After each turn, check to see if we're at a corner. If so, set the corresponding flag.
   *
   * @param event Info about the current state of the battle.
   */
  @Override
  public void onTurnEnded(TurnEndedEvent event) {
    // Get the snapshot of the robot daCruzer 
    IRobotSnapshot robot = event.getTurnSnapshot().getRobots()[1];

    // Get robot's current position
    double xPos = robot.getX();
    double yPos = robot.getY();
    // The set the width of all robots
    final double robotWidth = 40.0;

    // Checks to see if the robot visited the top left corner
    if ((xPos < robotWidth) && (yPos < robotWidth)) {
      visitedUpperLeft = true;
    }
    if ((xPos < robotWidth && (yPos > (height - robotWidth)))) {
      visitedLowerLeft = true;
    }
    if ((xPos > (width - robotWidth)) && (yPos < robotWidth)) {
      visitedUpperRight = true;
    }
    if ((xPos > (width - robotWidth) && (yPos > (height - robotWidth)))) {
      visitedLowerRight = true;
    }

  }

  /**
   * After the battle, check to see that we've visited the corners.
   *
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    assertTrue("Check UpperLeft", visitedUpperLeft);
    assertTrue("Check LowerLeft", visitedLowerLeft);
    assertTrue("Check UpperRight", visitedUpperRight);
    assertTrue("Check LowerRight", visitedLowerRight);

  }

}

package pmj;

import static org.junit.Assert.assertEquals;
import robocode.control.testing.RobotTestBed;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;

/**
 * Illustrates JUnit testing of Robocode robots.
 * This test simply verifies that DaCruzer always beats SittingDuck.
 * 
 * Also illustrates the overriding of a set of methods from RobotTestBed to show how the testing
 * behavior can be customized and controlled. 
 * 
 * @author Philip Johnson
 */
public class TestDaCruzerVersusSittingDuck extends RobotTestBed {

  /**
   * Specifies that SittingDuck and DaCruzer are to be matched up in this test case.
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.SittingDuck,pmj.DaCruzer";
  }
  
  /**
   * This test runs for 10 rounds.
   * @return The number of rounds. 
   */
  @Override
  public int getNumRounds() {
    return 10;
  }
  
  /**
   * The actual test, which asserts that DaCruzer has won every round against SittingDuck.
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    // Sanity check that results[1] is DaCruzer (not strictly necessary, but illustrative).
    BattleResults daCruzerResults = battleResults[1];
    String robotName = daCruzerResults.getTeamLeaderName();
    assertEquals("Check that results[1] is DaCruzer", robotName, "pmj.DaCruzer*");
    
    // Check to make sure DaCruzer won every round.
    assertEquals("Check DaCruzer winner", daCruzerResults.getFirsts(), getNumRounds());
  }
  
  /**
   * Called after each turn. 
   * Provided here to show that you could use this method as part of your testing.  
   * @param event The TurnEndedEvent.
   */
  @Override
  public void onTurnEnded(TurnEndedEvent event) {
    // You could add code here to check a condition after every turn or collect data. 
  }
  
  /**
   * Returns a comma or space separated list like: x1,y1,heading1, x2,y2,heading2, which are the 
   * coordinates and heading of robot #1 and #2. So "0,0,180, 50,80,270" means that robot #1 
   * has position (0,0) and heading 180, and robot #2 has position (50,80) and heading 270.
   * 
   * Override this method to explicitly specify the initial positions for your test cases. 
   * 
   * Defaults to null, which means that the initial positions are determined randomly.  Since
   * battles are deterministic by default, the initial positions are randomly chosen but will
   * always be the same each time you run the test case. 
   * 
   * @return The list of initial positions.
   */
  @Override
  public String getInitialPositions() {
    return null;
  }
  
  /**
   * Returns true if the battle should be deterministic and thus robots will always start
   * in the same position each time. 
   * 
   * Override to return false to support random initialization. 
   * @return True if the battle will be deterministic.
   */
  @Override
  public boolean isDeterministic() {
    return true;
  }

  /**
   * Specifies how many errors you expect this battle to generate.
   * Defaults to 0. Override this method to change the number of expected errors. 
   * @return The expected number of errors.
   */
  @Override
  protected int getExpectedErrors() {
    return 0;
  }
  
  /**
   * Invoked before the test battle begins.
   * Default behavior is to do nothing.
   * Override this method in your test case to add behavior before the battle starts. 
   */
  @Override
  protected void runSetup() {
    // Default does nothing.
  }

  /**
   * Invoked after the test battle ends.
   * Default behavior is to do nothing.
   * Override this method in your test case to add behavior after the battle ends.
   */
  @Override
  protected void runTeardown() {
    // Default does nothing.
  }
  
}

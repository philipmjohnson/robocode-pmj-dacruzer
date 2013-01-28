package pmj;

import static org.junit.Assert.assertTrue;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IBulletSnapshot;
import robocode.control.testing.RobotTestBed;

/**
 * This tests for variability in bullet power administered by the robot DaCruzer.
 *
 * @author Philip Johnson
 */
public class TestDaCruzerFiring extends RobotTestBed {

  // True if DaCruzer has fired a bullet with power less than 1.4;
  boolean firePowerReachLevelOne = false;
  // True if DaCruzer has fired a bullet with power between 1.5 to 1.9;
  boolean firePowerReachLevelTwo = false;
  // True if DaCruzer has fired a bullet with power between 2.0 to 2.4;
  boolean firePowerReachLevelThree = false;
  // True if DaCruzer has fired a bullet with power greater than 2.4;
  boolean firePowerReachLevelFour = false;
  
  /**
   * Specifies that SittingDuck and DaCruzer are to be matched up in this test case.
   * 
   * @return The comma-delimited list of robots in this match.
   */
  @Override 
  public String getRobotNames() {
    return "sample.Tracker,pmj.DaCruzer";
  }
  
  /**
   * This test runs for 20 rounds.
   * 
   * @return The number of rounds. 
   */
  @Override 
  public int getNumRounds() {
    return 20;
  }
  
  /**
   * At the end of each turn, checks the power of all bullets moving across the 
   * battlefield. Checks to see if there is any variability in bullet power, proving
   * that DaCruzer is using proportional firing based on distance of detected enemy robot. 
   * 
   * @param event Info about the current state of the battle.
   */
  @Override 
  public void onTurnEnded (TurnEndedEvent event) {
    
    // All active bullets belong to DaCruzer since SittingDuck does not fire.
    IBulletSnapshot bullets[] = event.getTurnSnapshot().getBullets();
    double bulletPower;
    
    for (int i = 0; i < bullets.length; i++) {
    
      bulletPower = bullets[i].getPower();
      
      if (bulletPower <= 1.4) {
        firePowerReachLevelOne = true;
      }
      else if (bulletPower > 1.5 && bulletPower <= 1.9) {
        firePowerReachLevelTwo = true;
      }
      else if (bulletPower > 1.9 && bulletPower <= 2.4) {
        firePowerReachLevelThree = true;
      }
      else if (bulletPower > 2.4) {
        firePowerReachLevelFour = true;
      }
    }
  }
  
  /**
   * After running all matches, determine if DaCruzer has had variability in its bullet power.
   * 
   * @param event Details about the completed battle.
   */
  @Override 
  public void onBattleCompleted(BattleCompletedEvent event) {
    assertTrue("Bullet Power less than 1.4", firePowerReachLevelOne);
    assertTrue("Bullet Power between 1.5 and 1.9", firePowerReachLevelTwo);
    assertTrue("Bullet Power between 2.0 and 2.4", firePowerReachLevelThree);
    assertTrue("Bullet Power greater than 2.5", firePowerReachLevelFour);
  }
}
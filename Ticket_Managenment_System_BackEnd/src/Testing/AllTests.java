package Testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class is used as a jUnit test Suite to test all the jUnit class at once in the following order:
 * BackEndTest and then BackEnd_ioTest.
 * @author Devan Shah 100428864, Ravikumar Patel 100423830, Parth Patel 1003922782, Parth Desai 100467968
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ BackEndTest.class, BackEnd_ioTest.class, MainLoopAndDecisionCoverageTest.class})
public class AllTests {

}

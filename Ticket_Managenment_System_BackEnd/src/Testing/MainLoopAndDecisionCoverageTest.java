package Testing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BackEnd.BackEnd;
/**
 * This class is used to test the loop coverage and the condition coverage in the main method from the BackEnd class
 * @author Devan Shah 100428864, Ravikumar Patel 100423830, Parth Patel 1003922782, Parth Desai 100467968
 *
 */
public class MainLoopAndDecisionCoverageTest {
	// Declaring ArrayList
	ArrayList<String> dailyTransactionInformation;

	/**
	 * The ByteArrayOutputStream class stream creates a buffer in memory and all the data sent to the stream is stored in the buffer.
	 * So, this is used to store an outputs that are sent to the console.
	 */
	private final ByteArrayOutputStream outputHolder = new ByteArrayOutputStream();

	/**
	 * This method is used as a start up so, when ever a new test is run the start()
	 * method is activated. This is possible with the @Before which means that this
	 * method is run before any test cases is run.
	 */
	@Before
	public void setup() {

		System.setOut(new PrintStream(outputHolder));
		BackEnd.usersInformation.clear();
		BackEnd.ticketInformation.clear();
		BackEnd.dailyTransaction.clear();
		dailyTransactionInformation = new ArrayList<String>();
	}

	/**
	 * This method tests that the outer loop of the main function does not run.
	 */
	@Test
	public void mainOuterLoopDoesNotRunTest() {
		BackEnd.main(null);

		Assert.assertTrue(true);
	}

	/**
	 * This method is used to test the main() method with no transaction being passed to it.
	 * Also covers Loop coverage, of testing a single run for the first for loop and the second
	 * for loop also. This will run the outer loop once and the inner loop will not run.
	 */
	@Test
	public void mainOuterLoopRunsOnceInnerLoopDoesNotRunTest() {
		BackEnd.dailyTransaction.add("00");

		BackEnd.main(null);

		Assert.assertTrue(true);
	}

	/**
	 * This method is used to test the main() method with a single run of the inner loop, which will also invoke
	 * the outer loop to run twice.
	 */
	@Test
	public void mainOuterLoopRunsTwiceInnerLoopRunsOnceTest() {
		BackEnd.dailyTransaction.add("00_Buyer123________BS_000000.00");
		BackEnd.dailyTransaction.add("00");

		BackEnd.main(null);

		Assert.assertTrue(true);
	}

	/**
	 * This method is testing that the outer loop runs many times and that inner loop runs twice.
	 */
	@Test
	public void mainOuterLoopRunsManyTimesInnerLoopRunsTwiceTest() {
		BackEnd.dailyTransaction.add("03_Off-Mirvish_Series__Ravi123_________080_299.99");
		BackEnd.dailyTransaction.add("00_Buyer123________BS_000000.00");
		BackEnd.dailyTransaction.add("00");

		BackEnd.main(null);

		Assert.assertTrue(true);
	}

	/**
	 * This method is used to test the main() method in the BackEnd class. The main method is
	 * tested with sample daily transaction entries. There are 6 transaction that are considered
	 * in the daily transaction file which include create, delete, buy, sell, refund and addcredit.
	 * To test the main function each sample transaction needs to be passed including the logout and
	 * the ending line for a daily transaction file. The test is successful when the entire main
	 * method is executed and the Current_User_Accounts File and the Available_Tickets_File  are
	 * printed with the updated files after the transactions have been performed.
	 * Also covers Loop coverage, of testing multiple run for the first for loop and the second
	 * for loop also.
	 */
	@Test
	public void mainInnerLoopRunsManyTimesAndInnerLoopConditionalTest() {
		// Adding the following sample dtf entries into the ArrayList for the main function to be run
		BackEnd.dailyTransaction.add("04_Spider-Man__________Seller123_______002_055.00");
		BackEnd.dailyTransaction.add("03_Off-Mirvish_Series__Ravi123_________080_299.99");
		BackEnd.dailyTransaction.add("01_Jordan1_________AA_000000.00");
		BackEnd.dailyTransaction.add("02_Jordan123_______AA_000000.00");
		BackEnd.dailyTransaction.add("05_FunMan23________Foundation21____000045.99");
		BackEnd.dailyTransaction.add("06_Wonderwomen123__BS_000854.35");
		BackEnd.dailyTransaction.add("00_Buyer123________BS_000000.00");
		BackEnd.dailyTransaction.add("00");

		BackEnd.main(null); // Activating the main function for the test

		Assert.assertTrue(true);
	}

	/**
	 * This method is used to test the main() method with an incorrect transaction entry
	 * such as in the example below "07" should not be accepted as a transaction.
	 * This method is also used for decision coverage if structure.
	 */
	@Test
	public void mainInnerLoopConditionBranchErrorTest() {
		BackEnd.dailyTransaction.add("07_____________________________");
		BackEnd.dailyTransaction.add("00_Buyer123________BS_000000.00");
		BackEnd.dailyTransaction.add("00");

		BackEnd.main(null);

		Assert.assertNotSame(0,outputHolder.size());
	}

	/**
	 * This method is testing the positive aspect of the conditional statement in the outer loop.
	 */
	@Test
	public void mainOuterLoopConditionPositiveTest() {
		BackEnd.dailyTransaction.add("00_Buyer123________BS_000000.00");

		BackEnd.main(null); // Activating the main function for the test

		Assert.assertTrue(true);
	}

	/**
	 * This method is testing the negative aspect of the conditional statement in the outer loop.
	 */
	@Test
	public void mainOuterLoopConditionNegitivetest() {
		BackEnd.dailyTransaction.add("00");

		BackEnd.main(null); // Activating the main function for the test

		Assert.assertTrue(true);
	}

	/**
	 * This is used to make sure that at the end of every test the output stream is always set to null so that
	 * there are no error later on when multiple error are found in the different test cases. This method
	 * runs after each test cases has been run.
	 */
	@After
	public void tearDown() {
		System.setOut(null);
	}

}

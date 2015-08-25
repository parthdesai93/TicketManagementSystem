package Testing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BackEnd.BackEnd;

/**
 * This class is used to test all the method in the BackEnd class. With this class to test all the methods
 * it is possible to see that all the instructions in the BackEnd class are achieved.
 * @author Devan Shah 100428864, Ravikumar Patel 100423830, Parth Patel 1003922782, Parth Desai 100467968
 *
 */
public class BackEndTest {
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
	 * This method is used to test the print() method in the BackEnd class. This test consists of
	 * adding designated entries into the designated ArrayList. Also checking to make sure that
	 * the created files exist in the designated directory.
	 */
	@Test
	public void printTest(){
		// Adding the following entries in to the userInformation ArrayList
		BackEnd.usersInformation.add("Seller123_______SS_047774.74");
		BackEnd.usersInformation.add("admin___________AA_000000.00");
		BackEnd.usersInformation.add("Buyer123________FS_999999.09");
		BackEnd.usersInformation.add("Devan123________AA_000000.00");
		BackEnd.usersInformation.add("Tom123__________BS_999999.00");
		// Adding the following entries into the ticketInformation ArrayList
		BackEnd.ticketInformation.add("Spider-Man__________Seller123_______919_055.00");
		BackEnd.ticketInformation.add("Batman______________Seller123_______719_035.00");
		BackEnd.ticketInformation.add("The_Green_Hornet____Seller123_______799_100.00");
		BackEnd.ticketInformation.add("The_Wonder_Women____Seller123_______919_088.00");
		BackEnd.ticketInformation.add("Die_Hard____________Seller123_______919_044.00");

		BackEnd.print();

		File currentAccountsFileExist = new File("TestCasesOrganization/Current_User_Accounts.cua");
		File availableTicketsFileExist = new File("TestCasesOrganization/Available_Tickets_File.atf");

		Assert.assertTrue(currentAccountsFileExist.exists());
		Assert.assertTrue(availableTicketsFileExist.exists());
	}

	/**
	 * This method is testing the create() method in the BackEnd class. This method is testing
	 * that a user is created properly. This is completed by passing in the parsed dtf entry to
	 * the create() method and the method creates the user accordingly. The test is successful
	 * only when the expected and the actual string are the same, to do this check the first
	 * index of the userInformation ArrayList to see if user account was created.
	 * This method also covers loop coverage of no iterations since there are no user in the system
	 * currently.
	 */
	@Test
	public void createTest(){
		dailyTransactionInformation.add("01_Jordan2_________AA_000000.00");

		BackEnd.create(dailyTransactionInformation.get(0).substring(3, 31));
		// Checks to see that the two strings are equal
		Assert.assertEquals("Jordan2_________AA_000000.00", BackEnd.usersInformation.get(0));
	}

	/**
	 * This method is used to check loop coverage of many loop iterations for the user check.
	 */
	@Test
	public void createManyUsersTest() {
		dailyTransactionInformation.add("01_Devan12_________AA_099999.00");

		BackEnd.usersInformation.add("Devan123________AA_40000.00");
		BackEnd.usersInformation.add("Monkery123______AA_000400.00");
		BackEnd.usersInformation.add("upperWestSide___BS_004000.00");

		BackEnd.create(dailyTransactionInformation.get(0).substring(3, 31));

		Assert.assertEquals("Devan12_________AA_099999.00", BackEnd.usersInformation.get(3));

	}

	/**
	 * This method is testing the Error branch of the create() method in the BackEnd class. This test
	 * is testing the portion that if the user already exists in the system then split out an error.
	 * In this transaction the dtf transaction already matches an account that exist so the BackEnd
	 * will through an error that the user has already been created. So to make sure that the test
	 * passes the outputHolder store the error that is thrown to the console, that array is compared
	 * to "Error: User has already been created\r\n" if that is true then the test cases passes.
	 */
	@Test
	public void createTestErrorBranch(){
		dailyTransactionInformation.add("01_Devan123________AA_000000.00");
		BackEnd.usersInformation.add("Devan123________AA_000000.00");

		BackEnd.create(dailyTransactionInformation.get(0).substring(3, 31));
		// Check that the correct errors is thrown
		Assert.assertNotSame(0,outputHolder.size());
	}

	/**
	 * This method is testing the delete() method in the BackEnd class. This method also
	 * covers loop coverage by running the loop many times with the different user accounts
	 * in the system.
	 */
	@Test
	public void deleteTest(){
		dailyTransactionInformation.add("02_Devan123________AA_000000.00");
		BackEnd.usersInformation.add("Devan123________AA_000000.00");
		BackEnd.usersInformation.add("Monkey123_______BS_000000.00");

		BackEnd.delete(dailyTransactionInformation.get(0).substring(3, 31));

		Assert.assertSame(1, BackEnd.usersInformation.size());
	}

	/**
	 * This method is testing the ErrorBranch of the delete() method in the BackEnd class. Also
	 * this method is used for loop coverage, in particularly it covers the aspect that the loop
	 * never runs. This test cases is satisfied when the Error matches the error that was displayed
	 * in the console.
	 */
	@Test
	public void deleteTestErrorBranch() {
		dailyTransactionInformation.add("02_Devan123________AA_000000.00");

		BackEnd.delete(dailyTransactionInformation.get(0).substring(3, 31));
		// Check to see that the excepted error is the same as the error displayed in the outputHolder
		Assert.assertNotSame(0,outputHolder.size());
	}

	/**
	 * This method is testing the sell() method in BackEnd class. Furthermore, testing that the for loop is not
	 * executed at all since the ticketInformation ArrayList does not have any entries in it.
	 */
	@Test
	public void sellTest() {
		dailyTransactionInformation.add("03_The_Wizard_of_OZ____Ravi123_________004_199.00");
		dailyTransactionInformation.add("03_Hello_World_________Devan123________003_099.00");

		BackEnd.ticketInformation.add("Hello_World_________Devan123________300_099.00");
		BackEnd.sell(dailyTransactionInformation.get(0).substring(3, 49));

		Assert.assertEquals("The_Wizard_of_OZ____Ravi123_________004_199.00", BackEnd.ticketInformation.get(1));
	}

	/**
	 * This method also tests the sell() method in the BackEnd class. Primarily this test is testing the to make sure
	 * that the error branch in the sell() method is covered. This also manages to cover loop coverage as the for loop
	 * will not run.
	 */
	@Test
	public void sellTestErrorBranch() {
		dailyTransactionInformation.add("03_The_Wizard_of_OZ____Ravi123_________044_199.00");


		BackEnd.ticketInformation.add("The_Wizard_of_OZ____Ravi123_________044_199.00");
		BackEnd.sell(dailyTransactionInformation.get(0).substring(3, 49));

		Assert.assertNotSame(0,outputHolder.size());
	}

	/**
	 * This method is testing the buy() method in the BackEnd class. Furthermore, testing to make sure that all
	 * the statements in the method are covered. With test all the statements of the method are covered including
	 * loop coverage that cycles through the loop many times. This test is successful when the tickets are deducted
	 * correctly, the buyer's balance is credited correctly and the seller's balance is deducted correctly.
	 */
	@Test
	public void buyTest() {
		dailyTransactionInformation.add("04_The_Green_Hornet____Seller123_______003_100.00");
		dailyTransactionInformation.add("00_Buyer123________AA_000000.00");

		BackEnd.usersInformation.add("Seller123_______SS_047774.74");
		BackEnd.usersInformation.add("Buyer123________FS_999999.09");

		BackEnd.ticketInformation.add("Batman______________Seller123_______719_035.00");
		BackEnd.ticketInformation.add("The_Green_Hornet____Seller123_______799_100.00");

		BackEnd.buy(dailyTransactionInformation.get(0).substring(3, 49), dailyTransactionInformation.get(1).substring(3, 18));
		// Checking to see that the tickets were deducted correctly and that the balances were calculated as expected
		Assert.assertEquals("796", BackEnd.ticketInformation.get(1).substring(36, 39));
		Assert.assertEquals("048074.74", BackEnd.usersInformation.get(0).substring(19,28));
		Assert.assertEquals("999699.09",BackEnd.usersInformation.get(1).subSequence(19, 28));
	}

	/**
	 * This method is testing the refund() method in the BackEnd class. This test is made to conduct full
	 * statement coverage and also some loop coverage and decision coverage. This test is successful when
	 * the correct balance for the buyer and the seller is calculated and check to make sure that it is so.
	 */
	@Test
	public void refundTest() {
		dailyTransactionInformation.add("05_FunMan23________Foundation21____000045.99");

		BackEnd.usersInformation.add("FunMan23________BS_000100.00");
		BackEnd.usersInformation.add("Foundation21____SS_990000.00");

		BackEnd.refund(dailyTransactionInformation.get(0).substring(3, 44));
		// Checking to see if the calculated balance is the excepted balance
		Assert.assertEquals("000145.99", BackEnd.usersInformation.get(0).substring(19,28));
		Assert.assertEquals("989954.01",BackEnd.usersInformation.get(1).subSequence(19, 28));
	}

	/**
	 * This method is testing the addCredit() method in the BackEnd class.This test is made so that full coverage
	 * is achieved through the method. This test is successful when the excepted result and the actual result match
	 * so in this cases if the balace that the credit is being added to is credited the test passes.
	 */
	@Test
	public void addCreditTest() {
		dailyTransactionInformation.add("06_Wonderwomen123__BS_000854.35");

		BackEnd.usersInformation.add("Devan123________AA_999999.98");
		BackEnd.usersInformation.add("Wonderwomen123__BS_001000.35");

		BackEnd.addCredit(dailyTransactionInformation.get(0).substring(3, 31));

		Assert.assertEquals("001854.70", BackEnd.usersInformation.get(1).subSequence(19, 28));

	}

	/**
	 * This method is testing the constructor in the BackEnd class. The only reason for this here is to make sure that
	 * the entire source code is 100% covereged and that required that the constructor is tested as well even if it is
	 * not being used.
	 */
	//=Constructor Test To get 100% Coverage=
		@Test							  //=
		public void constructorTest() {	  //=
			new BackEnd(null);        	  //=
		}							      //=
	//=======================================

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

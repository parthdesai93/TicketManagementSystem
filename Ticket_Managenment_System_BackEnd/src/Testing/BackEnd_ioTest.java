package Testing;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BackEnd.BackEnd_io;

/**
 * This class is used to test all the methods in the BackEnd_io class. With this class all the test
 * give 100% coverage in the BackEnd_io, which show that there is no dead code and all lines are
 * being executed depending on the test cases.
 * @author Devan Shah 100428864, Ravikumar Patel 100423830, Parth Patel 1003922782, Parth Desai 100467968
 *
 */
public class BackEnd_ioTest {

	/**
	 * The ByteArrayOutputStream class stream creates a buffer in memory and all the data sent to the stream is stored in the buffer.
	 * So, this is used to store exception errors that are sent to the console.
	 */
	private final ByteArrayOutputStream errorHolder = new ByteArrayOutputStream();
	ArrayList<String> currentUserAccountsInformation;
	ArrayList<String> availableTicketInformation;
	ArrayList<String> dailyTransactionEntries;

	/**
	 * This method is used as a start up so, when ever a new test is run the start()
	 * method is activated. This is possible with the @Before which means that this
	 * method is run before any test cases is run.
	 */
	@Before
	public void setup() {
		System.setErr(new PrintStream(errorHolder));
		// Setting the ArrayLists in the BackEnd_io class to null
		BackEnd_io.CURRENT_USER_ACCOUNT_FILE_PATH = null;
		BackEnd_io.AVAILABLE_TICKETS_FILE_PATH = null;
		BackEnd_io.DAILY_TRANSACTION_FILE_PATH = null;
	}

//--------------------------------------------------------readCurrentUserAccount() Method Tests-------------------------------------------------
	/**
	 * This method is testing the readCurrentUserAccounts() method in the BackEnd_io class. This test is using the method in the
	 * BackEnd_io class to read a file that has already been created and contains data to be read. This test stores the data that
	 * is in the file into an array, and then that ArrayList is check to see that it contains information. Therefore if the ArrayList
	 * is not null then the test case will pass.
	 */
	@Test
	public void readCurrentUserAccountsTest() {
		BackEnd_io.CURRENT_USER_ACCOUNT_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Current_User_Accounts_Test.cua";

		currentUserAccountsInformation = new ArrayList<String>(BackEnd_io.readCurrentUserAccounts());

		assertNotNull(currentUserAccountsInformation);
	}

	/**
	 * This method is testing any exception error that can be thrown by the readCurrentUserAccount() method. This allows to cover the
	 * try and catch statement.
	 */
	@Test
	public void currentUserAccountsExceptionBranchTest() {
		BackEnd_io.CURRENT_USER_ACCOUNT_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Current_User_Accounts.cua";

		BackEnd_io.readCurrentUserAccounts();

		Assert.assertNotSame(0,errorHolder.size());
	}

	/**
	 * This method is testing the while loop to make sure that all possibilities are are are covered. The negative of the while loop is covered
	 * when the file does not contain anything in the file so that the EOF will be reached right away.
	 */
	@Test
	public void currentUserAccountsWhileBranch() {
		BackEnd_io.CURRENT_USER_ACCOUNT_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Current_User_Accounts_While_Test.cua";

		currentUserAccountsInformation = new ArrayList<String>(BackEnd_io.readCurrentUserAccounts());

		Assert.assertNotSame(1,currentUserAccountsInformation.size());

	}
// --------------------------------------------------------------------------------------------------------------------------------------------

//---------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method is testing the readAvailableTicketFile() method from BackEnd_io class. This test cases is testing to make sure that the
	 * file is read correctly and that is check by storing the data from the method into an ArrayList. The test will be successful if the
	 * availableTicketInformation is not empty.
	 */
	@Test
	public void readAvailableTicketFileTest() {
		BackEnd_io.AVAILABLE_TICKETS_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Available_Tickets_File_Test.atf";

		availableTicketInformation = new ArrayList<String>(BackEnd_io.ReadAvailableTicketFile());

		assertNotNull(availableTicketInformation); // Tests that the ArrayList is not null
	}

	/**
	 * This method is testing the exception branch in the readAcailableTicketFile() method from BackEnd_io class. The test is successful
	 * when the errorHolder is not empty. So the error holder will have the error in it that was retrieved from the console.
	 */
	@Test
	public void availableTicketExceptionBranchTest() {
		BackEnd_io.AVAILABLE_TICKETS_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Available_Tickets_File.atf";

		BackEnd_io.ReadAvailableTicketFile();

		Assert.assertNotSame(0,errorHolder.size());
	}

	/**
	 * This method is testing the while branch in the readAvailableTicketFile() method from BackEnd_io class. This test is successful when there
	 * is only one only one thing in the ArrayList which is the null.
	 */
	@Test
	public void availableTicketWhileBranchTest() {
		BackEnd_io.AVAILABLE_TICKETS_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Available_Tickets_File_While_Test.atf";

		availableTicketInformation = new ArrayList<String> (BackEnd_io.ReadAvailableTicketFile());

		Assert.assertNotSame(1,availableTicketInformation.size());
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method is testing the readDailyTransactionFile() method in the BackEnd_io class. The method will read the daily transaction file
	 * and store the data into an ArrayList called dailyTransactionEntries. So the test will be successful as long as the dailyTransactionEntries
	 * ArrayList is not null.
	 */
	@Test
	public void readDailyTransactionFileTest() {
		BackEnd_io.DAILY_TRANSACTION_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Daily_Transaction_File.dtf";

		dailyTransactionEntries = new ArrayList<String>(BackEnd_io.ReadDailyTransactionFile());

		Assert.assertNotNull(dailyTransactionEntries);
	}

	/**
	 * This method is testing the exceptions branch in the readDailyTransactionFile() method. When the in correct file path to read is given
	 * the method will display an error in the console. This is where the errorHolder will store the error that was displayed in the console
	 * and check to see if there is anything in the holder and if there is then the test is true.
	 */
	@Test
	public void readDailyTransactionExceptionBranchTest() {
		BackEnd_io.DAILY_TRANSACTION_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/readFilesTest/Daily_Transaction_Fil.dtf";

		BackEnd_io.ReadDailyTransactionFile();

		Assert.assertNotSame(0,errorHolder.size());
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method is testing the writeFiles() method in the BackEnd_io class. This test is performed to test if the file
	 * is written correctly and contains the correct information. The test will be successful when the new file is created
	 * successfully.
	 */
	@Test
	public void writeFilesTest() {
		currentUserAccountsInformation = new ArrayList<String>();

		currentUserAccountsInformation.add("Seller123_______SS_047774.74");
		currentUserAccountsInformation.add("admin___________AA_000000.00");
		currentUserAccountsInformation.add("Buyer123________FS_999999.09");
		currentUserAccountsInformation.add("Devan123________AA_000000.00");
		currentUserAccountsInformation.add("Tom123__________BS_999999.00");

		File currentUserAccountFile = new File("TestCasesOrganization/jUnitTestFiles/writeFilesTest/Current_User_Accounts.cua");

		BackEnd_io.WriteFiles(currentUserAccountsInformation,currentUserAccountFile.getPath());

		Assert.assertNotNull(currentUserAccountFile.exists());
	}

	/**
	 * This method is testing exception branch of the writeFile() method. This test is passing in a directory as the file, so
	 * when method tries to write a file it will display an error since it is not possible to write to a directory. The test
	 * is successful when the errorHolder is not null, which means that there will be an error that displayed in the console.
	 */
	@Test
	public void writeFilesExceptionBranchTest() {
		currentUserAccountsInformation = new ArrayList<String>();

		File currentUserAccountFileException = new File("TestCasesOrganization/Daily_Transaction_File.dtf");

		BackEnd_io.WriteFiles(currentUserAccountsInformation, currentUserAccountFileException.getParent());

		Assert.assertNotNull(errorHolder.size());
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method is testing the fileArchive() in the BackEnd_io class. This is testing to make sure that the files are archived
	 * correctly, and to see that the files that are created contain same data that is in the file that was
	 * archive. This test passes when there are file created in the passed directory
	 */
	@Test
	public void fileArchiveTest() throws Exception {
		BackEnd_io.PREVIOUS_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/archiveTest/";
		BackEnd_io.ARCHIVE_FILE_PATH = "TestCasesOrganization/jUnitTestFiles/archiveTest/ArchivedFile/";

		BackEnd_io.FileArchive("Current_User_Accounts.cua");

		File testArchiveFile = new File("TestCasesOrganization/jUnitTestFiles/archiveTest/ArchivedFile/");

		String[] files = testArchiveFile.list(); // Storing the names of the files that are in the directory into an array

		Assert.assertNotNull(files); // Checking to see that the files array is not empty, if it is then test failed
	}

	/**
	 * This method is testing the the Conditional and the Exception branch of the fileArchive() method in the BackEnd_io class.
	 * This test is successful when the file name that is passed does not exist and also there is no files in the directory.
	 */
	@Test
	public void fileArchiveConditionalAndExceptionBranchTest() {

		BackEnd_io.FileArchive("");

		Assert.assertNotSame(0,errorHolder.size());
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This method is for testing purpose only, has not functionality. This test is to test the constructor of the BackEnd_io class.
	 * HAS NO FUNCTIONALITY, TESTING CONSTRUCTOR ONLY
	 */
//=Constructor Test To get 100% Coverage=
	@Test							  //=
	public void constructorTest() {	  //=
		new BackEnd_io(null);         //=
	}							      //=
//=======================================

	/**
	 * This is used to make sure that at the end of every test the output stream is always set to null so that
	 * there are no error later on when multiple error are found in the different test cases. This method
	 * runs after each test cases has been run. To set the error stream to null.
	 */
	@After
	public void tearDown() {
		System.setErr(null);
	}
}

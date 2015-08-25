package BackEnd;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * This class is used to read the Current_User_Accounts.cua file, the Available_Tickets_File.atf file and the
 * Daily_Transaction_file.dtf. Also contains the the functionality to write to the Current_User_Acounts.cua file
 * and the Available_Tickets_File.atf. Finally the most optimal functionality that this class has is the capibility
 * to archive previous files before a change is applied.
 * @author Devan Shah 100428864, Ravikumar Patel 100423830, Parth Patel 1003922782, Parth Desai 100467968
 *
 */
public class BackEnd_io {

	public static String test = "";
	//=========================================
	//	HAS NO FUNCTION, TESTING PURPOSE ONLY
	public BackEnd_io(String tests) {
		test = tests;
	}
	//==========================================

	// Constant Definition
	public static String CURRENT_USER_ACCOUNT_FILE_PATH = "TestCasesOrganization/Current_User_Accounts.cua";
	public static String AVAILABLE_TICKETS_FILE_PATH = "TestCasesOrganization/Available_Tickets_File.atf";
	public static String DAILY_TRANSACTION_FILE_PATH = "TestCasesOrganization/Daily_Transaction_File.dtf";
	public static String PREVIOUS_FILE_PATH = "TestCasesOrganization/";
	public static String ARCHIVE_FILE_PATH = "TestCasesOrganization/ArchivedFile/";

	/**
	 * This method is used to read and parse the current user accounts file. The current user accounts file
	 * consists of the users that are currently able to access the system. This method also is used to extract the
	 * information from the Current_User_Accounts file and parse each line and store each aspect of the users
	 * information in a arraylist, which can be used later on for account validation.
	 * What Each Variable Represents
	 * 		username_information - stores a single line that was read from the file
	 * 		system_user_accounts - stores the parsed username, accountType and balance
	 * @return a array list of type string that stores a single line from the Current User Account File into an index
	 * @throws IOException
	 */
	public static ArrayList<String> readCurrentUserAccounts(){

		// Defining Variables
		String username_information = "";

		// Constructing a arrayList of type string that will store the information of all users in the system
		ArrayList<String> system_user_accounts = new  ArrayList<String>();

		// Opening the "Current_User_Accounts.cua" file
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(CURRENT_USER_ACCOUNT_FILE_PATH));

			// This while loop will continue to run until the end of the file
			while ((username_information = in.readLine()) != null) {
				// When the END word has been read break, and return the arraylist
				if (!username_information.equals("END")) {

					// storing the user accounts information into an arraylist
					system_user_accounts.add(username_information);
				}
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		FileArchive("Current_User_Accounts.cua");
		return system_user_accounts;
	}

	/**
	 * This method is used to read and parse the Available Tickets file. The available Tickets file
	 * consists of the event name, the seller's username that is selling that event, the amount of available tickets for that
	 * event and also the price to purchase that event. So the purpose of this method is to parse the file and retrieve all
	 * the correct information and store it in a arrayList for later comparison.
	 * What Each Variable Represents
	 * 		ticketInformation - stores a single line that was read from the file
	 * 		available_tickets_information - stores the parsed eventTitle, sellerUsername, numberOfTickesAvailable and ticketPrice
	 * @return a arrayList of type string that store all a single line from the Available Tickets File into an index
	 * @throws IOException
	 */
	public static ArrayList<String> ReadAvailableTicketFile() {

		// Variable Deleceration
		String ticketInformation = "";

		// Constructing a arrayList of type string that will store the information of all the available tickets in the system
		ArrayList<String> available_tickets_information = new  ArrayList<String>();

		// Opening the "Available_Tickets_File.atf" file
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(AVAILABLE_TICKETS_FILE_PATH));
			// This while loop will continue to run until the end of the file
			while ((ticketInformation = in.readLine()) != null) {
				// When the END word has been read break, and return the arraylist
				if (!ticketInformation.equals("END")){

					// storing the ticket information into the available tickets information arraylist
					available_tickets_information.add(ticketInformation);
				}
			}

			in.close();
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}

		FileArchive("Available_Tickets_File.atf");
		return available_tickets_information;
	}
	/**
	 * This method is used to read and parse the Daily Transaction File. And store the results in an arraylist of type sting.
	 * What Each Variable Represents
	 * 		dailyTransctionLine - stores a single line that was read from the file
	 * 		dailyTransactionEntry - stores all the lines of the file in a different index
	 * @return a arraylist of type string that stores all the daily transaction entries
	 * @throws IOException
	 */
	public static ArrayList<String> ReadDailyTransactionFile() {

		// Defining Variables
		String dailyTransactionLine = "";

		// Constructing a arrayList of type string that will store the daily transaction entry
		ArrayList<String> dailyTransactionEntry = new  ArrayList<String>();

		// Opening the "Daily_Transaction_File.dtf" file
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(DAILY_TRANSACTION_FILE_PATH));
			// This while loop will continue to run until the end of the file
			while ((dailyTransactionLine = in.readLine()) != null)
			{
				// storing the daily transaction entries in an arraylist
				dailyTransactionEntry.add(dailyTransactionLine);
			}

			in.close();
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}

		return dailyTransactionEntry;
	}

	/**
	 * This method is used to write the updated current user accounts files after transactions have been applied or
	 * write the updated available tickets file after transactions have been applied.
	 * What Each Variable Represents
	 * 		out - is the bufferwriter for the Current User Accounts File
	 * @param writingArray is the updated user accounts information array list
	 * @param filePath is the file path that the file will be write into
	 * @throws IOException
	 */
	public static void WriteFiles(ArrayList<String> writingArray, String filePath) {

		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			// Used to loop through the userAccountInformation arrayList and outputting that to the corresponding file
			for (int i = 0; i < writingArray.size(); i++){
				out.write(writingArray.get(i));
				out.newLine(); // Used to output each of the entries in the arraylist onto a new line
			}
			// Adding in the "END" to show that it is the end of the file
			out.write("END");
			out.close();
		} catch (IOException e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	/**
	 * This method is used to archive the Available_Tickets_File and the Current_User_Accounts file before
	 * the changes are to be made to the file the files depending on the daily transaction files. The way that
	 * this method achieve its objective is by storing the current destination of the file, and then changing the
	 * destination of the file along with changing the name of the file depending on the number of files that are create
	 * the file name will add in date and the time to the new file name
	 * What Each Variable Represents
	 * 		previousFile - stores an instance of the previous file
	 * 		outputFile - is an output stream that is used to copy the data in the previous file to the created new file
	 * 		name - stores the name of the previous file
	 * 		period - stores the index where the period occurs (after the period is the file extension)
	 * 		newFile - stores the instance of the new file with the file name renamed with the addition of the date and time that the
	 * 				  file was archived
	 * @param filename is the file name that is going
	 */
	public static void FileArchive(String filename){
		// Stores an instance of the current file destination
		File previousFile = new File(PREVIOUS_FILE_PATH + filename);
		// Constructing the Date object to retrieve the current Date
		Date now = new Date();
		// Used to format the date to the following format "yyyy.MM.dd-hh.mm.ss"
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss" );
		// Declaring the output stream to copy the data from the current file to the new file
		FileOutputStream outputFile = null;
		// This makes sure that the file is of type file
		if (previousFile.isFile()) {
			File newFile = previousFile; // making the newFile equal to the previous file for latter use
			do {
				String name = newFile.getName(); // Retrieve the name
				int period = name.indexOf('.'); // index number where the "." is
				// creating a new instance of the file that consists of the new file name and the new destination
				newFile = new File(ARCHIVE_FILE_PATH, name.substring(0, period) + "_" + dateFormatter.format(now) + name.substring(period));
			} while (newFile.exists());
			// Rename the current file with the newfile instance
			previousFile.renameTo(newFile);
		}

		try {
			// Store the data in the current file to the new file that is stored in the new destination
			outputFile = new FileOutputStream(previousFile);
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
	}
}

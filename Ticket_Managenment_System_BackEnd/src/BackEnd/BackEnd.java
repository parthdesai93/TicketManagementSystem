package BackEnd;
import java.text.DecimalFormat;
import java.util.*;
/**
 * This class is used to handle the BackEnd evaluation of the dtf file and perform the appropriate
 * transactions and updating the correct accounts and updating the ticket information. The way to run
 * this program is to read in the available ticket file, the current user account file and the daily
 * transaction file. From there all the files are stored in into a designated ArrayList that is global
 * so that all method have access to the same data. Once the ArrayLists are filled the main function is
 * comes into play and starts to run through the daily transaction ArrayList and searches for a logout
 * transaction, once that is found all the transactions above the logout transaction are performed and
 * then the program goes back to searching through the ArrayList again for another logout transaction.
 * This process runs until all logout transactions are found and executed or when the string "00" is reached.
 *
 * @author Devan Shah 100428864, Ravikumar Patel 100423830, Parth Patel 1003922782, Parth Desai 100467968
 *
 */
public class BackEnd {

	public static String test = ""; // Testing purpose only
	//=========================================
	//	HAS NO FUNCTION, TESTING PURPOSE ONLY
	public BackEnd(String tests) {
		test = tests;
	}
	//==========================================

	// Declaring Static arraylist that will be used throughout the class
	public static ArrayList<String> usersInformation = new ArrayList<String>(BackEnd_io.readCurrentUserAccounts());
	public static ArrayList<String> ticketInformation = new ArrayList<String>(BackEnd_io.ReadAvailableTicketFile());
	public static ArrayList<String> dailyTransaction = new ArrayList<String>(BackEnd_io.ReadDailyTransactionFile());
	// Declaring static objects that are used throughout the class
	static DecimalFormat formatbalance = new DecimalFormat("000000.00");
	static DecimalFormat formatPrice = new DecimalFormat("000");

	/**
	 * This main method is used to determine which transaction needs to be applied, depending on the
	 * daily transaction file entries. The transactions in the dtf are first looked through until
	 * a transaction code of "00" is found and once a "00" is found in the dtf, the transaction before
	 * the "00" line are executed and the new current user accounts file and the available ticket files
	 * are printed taking in to consideration the transactions.
	 * What the Variables Represent
	 * 		newSession - stores the index value where the logout transaction line is found which is
	 * 					 used to identify the start of the second for loop.
	 * 		code - stores the current transaction code that is being tested
	 * @param args
	 */
	public static void main(String[] args) {
		// Variable Declaration
		int newSession = 0;

		/**
		 *  This first loop is used to run through the entire Daily Transaction arraylist.
		 *  The program exists only if the ending entry in the Daily Transaction file
		 *  in "00".
		 */
		for (int i=0; i < dailyTransaction.size(); i++)
		{
			if(!dailyTransaction.get(i).equals("00")){

				/**
				 * This inner loop runs through the entire Daily Transaction File until the
				 * transaction code "00" is found. This transaction code is the transaction code
				 * for a logout transaction. When the logout transaction line is found all the
				 * above transactions are executed and the arraylist for the User Information and the
				 * Available Tickets File are updated according to the dtf file entry.
				 */
				for (int j = newSession; j < i; j++){
					String code = dailyTransaction.get(i).substring(0, 2);
					// Once the code is "00" execute the transactions before the "00"
					if (code.equals("00")){

						code = dailyTransaction.get(j).substring(0, 2);
						if (code.equals("01")){
							create(dailyTransaction.get(j).substring(3,31));
						}
						else if (code.equals("02")){
							delete(dailyTransaction.get(j).substring(3, 31));
						}
						else if (code.equals("03")){
							sell(dailyTransaction.get(j).substring(3,49));
						}
						else if (code.equals("04")){
								buy(dailyTransaction.get(j).substring(3, 49),dailyTransaction.get(i).substring(3,18));
						}
						else if (code.equals("05")){
							refund(dailyTransaction.get(j).substring(3, 44));
						}
						else if (code.equals("06")){
							addCredit(dailyTransaction.get(j).substring(3, 31));
						}
						else {
							System.out.println("Error: Incorrect Transaction Entry");
						}
						newSession = i;
						print(); // Print the current arraylist
					}
				}
			}
		}
	}

	/**
	 * This method is used to print the user Information and the Ticket Information arraylist
	 */
	public static void print() {
		BackEnd_io.WriteFiles(usersInformation, "TestCasesOrganization/Current_User_Accounts.cua");
		BackEnd_io.WriteFiles(ticketInformation, "TestCasesOrganization/Available_Tickets_File.atf");

	}

	/**
	 * This method is used to create a new user accounts from the information that is gathered from
	 * the dtf. Also checks to make sure that the accounts does not already exist in the current user accounts file.
	 * What Each Variables Represent
	 * 		newUser - stores the new users information that needs to be created
	 * 		username - contains all the information of the current user account file
	 * 		breakingFlag - is used as a flag to specify when an errors is thrown and when the user Information needs
	 * 					   to be updated.
	 * @param createEntry represents the entire create transaction line from the dtf
	 */
	public static void create(String createEntry) {
		boolean breakingFlag = false; // Used for checking user verification
		// Used to loop through User information arraylist to check if the user exits already or not
		for (int j=0; j < usersInformation.size(); j++){
			String username = usersInformation.get(j).substring(0,15);
			// Shows that the user already exits and an error will be displayed
			if (username.equals(createEntry.substring(0,15))){
				System.out.println("Error: User has already been created");
				breakingFlag = true; // Change the flag to true if this branch is executed
			}
		}
		// This branch is executed if the flag is not true (so if the user does not exist)
		if (breakingFlag == false){
			usersInformation.add(createEntry);
		}
	}

	/**
	 * This method is used to delete a user from the current user accounts file depending on the daily transaction file.
	 * Checks to make sure that the user exists in the user information array list and if it does delete it if not
	 * display an error.
	 * What Each Variables Represent
	 * 		userEntry - stores the dtf entry after the transaction code is removed
	 * 		usersInformation - is the arraylist of all the cua entries
	 * 		breakingFlag - is used as a flag to specify when an errors is thrown and when the user Information needs
	 * 					   to be updated.
	 * @param deleteEntry represents the entire delete transaction line from the dtf
	 */
	public static void delete(String deleteEntry) {
		boolean breaking = false; // Used for checking user verification
		// Used to loop through User information arraylist to check if the user exists already
		for (int j = 0; j < usersInformation.size(); j++){
			String username = usersInformation.get(j).substring(0,15);
			// Shows the user does exist in the user information arraylist, so remove it
			if (username.equals(deleteEntry.substring(0, 15))){
				usersInformation.remove(deleteEntry);
				breaking = true;
			}
		}
		// This branch is executed if the user does not exist in the user Information arraylist
		if (breaking == false){
			System.out.println("Error: User Does not Exist");
		}
	}

	/**
	 * This method is used update the available tickets file when the dtf consists of a sell transaction
	 * What Each Variables Represent
	 * 		newTicket - stores the new ticket information that is from the dtf file
	 * 		ticketInformation - is the arraylist of the available tickets file
	 * 		breakingFlag - is used as a flag to specify when an errors is thrown and when the ticket information needs
	 * 					   to be updated.
	 * @param sellEntry represents the entire sell transaction line
	 */
	public static void sell(String sellEntry){
		boolean breakingFlag = false; // Used for checking event name verification
		// Used to loop through ticket information arraylist to check if the event name exist already or not
		for (int j=0; j < ticketInformation.size(); j++){
			String eventTitle = ticketInformation.get(j).substring(0,19);
			// Displays error when the event does exist in the ticket information arraylist
			if (eventTitle.equals(sellEntry.substring(0, 19))){
				System.out.println("Error: Event already exists");
				breakingFlag = true;
			}
		}
		// This branch is executed only if the event title does not already exits
		if (breakingFlag == false){
			ticketInformation.add(sellEntry);
		}
	}

	/**
	 * This method is used to update the atf and the cua depending on the transaction information in the dtf.
	 * This method updates the seller's account balance along with the buyer's account balance furthermore
	 * the tickets line is also updated with the new amount of tickets left.
	 * What the Variables Represent
	 * 		buyEntry - buy transaction line from the dtf
	 * 		eventTitleFromDTF - the event title from the dtf file
	 * 		sellerUsernameFromDTF - the seller's username from the dtf
	 * 		numberOfTicketsBought - the amount of tickets bought by the buyer
	 * 		priceOfTicket - the price of each ticket the seller is selling for
	 * 		sellerUsername - the seller's username in the userInformation arraylist
	 * 		sellerBalance - the seller's balance in the userInfomation arraylist
	 * 		newSellerBalance - calculating the new sellers balance after transactions takes place
	 * 		newSellerBalanceFormatter - stores the formatted string of the new seller's balance
	 * 		newSellerEntry - update the entry with the updated balance
	 * 		buyerUsername - stores the buyers username from the user information arraylist
	 * 		buyerBalance - stores the buyers balance from the user information arraylist
	 * 		newBuyerBalance - calculated buyers balance after transaction excuted
	 * 		newBuyerBalanceFormatter - stores the formatted string of the new buyer's balance
	 * 		newBuyerEntry - store the new buyer's account entry
	 * 		eventTitle - stores the event title from the ticket information arraylist
	 * 		numberOfTicketsAvailable - stores the number of tickets available from the ticket information arraylist
	 * 		newEntry - stores the updated ticket entry with the change in the number of tickets
	 * @param buyEntry represents the entire transaction line for a buy transaction
	 * @param buyersUsernameLogout the buyer's username from the dtf file when a logout transaction is performed
	 */
	public static void buy(String buyEntry, String buyersUsernameLogout){
		// Extract the information of the tickets information per line to update the seller's and buyer's balance
		String eventTitleFromDTF = buyEntry.substring(0, 19);
		String sellerUsernameFromDTF = (buyEntry.substring(20, 35));
		int numberOfTicketsBought = Integer.parseInt(buyEntry.substring(36, 39));
		double priceOfTicket = Double.parseDouble(buyEntry.substring(40));

		// Check to see if the Seller's Username exists in the user information arraylist and update the balance
		for (int j = 0; j < usersInformation.size(); j++){
			// Stores the parsed seller's username from the user information arraylist
			String sellerUsername = (usersInformation.get(j).substring(0,15));
			// Stores the seller's balance
			double sellerBalance = Double.parseDouble(usersInformation.get(j).substring(19, 28));

			// Stores the parsed buyer's username from the user information arraylist
			String buyerUsername = (usersInformation.get(j).substring(0,15)).replaceAll("_", "");
			// Stores the buyer's balance
			double buyerBalance = Double.parseDouble(usersInformation.get(j).substring(19, 28));

			// Check if the user exists and only execute if user exists
			if (sellerUsername.equals(sellerUsernameFromDTF)){

				double newSellerBalance = (priceOfTicket*numberOfTicketsBought) + sellerBalance;
				// Format the Seller's new balance
				String newSellerBalanceFormatter = formatbalance.format(newSellerBalance);
				// replaces the the old seller's balance with the new balance and stores it in newsellerEntry
				String newSellerEntry = usersInformation.get(j).replace(usersInformation.get(j).substring(19, 28), newSellerBalanceFormatter);
				// Change the entry in the arraylist with the new one
				usersInformation.set(j, newSellerEntry);
			}

			// Check if the user exists and only execute if user exists
			if (buyerUsername.equals(buyersUsernameLogout.replaceAll("_", ""))){
				double newBuyerBalance = buyerBalance - (priceOfTicket*numberOfTicketsBought);
				// Format the Buyer's new balance
				String newBuyerBalanceFormatter = formatbalance.format(newBuyerBalance);
				// replaces the the old buyer's balance with the new balance and stores it in newbuyerEntry
				String newBuyerEntry = usersInformation.get(j).replace(usersInformation.get(j).substring(19, 28), newBuyerBalanceFormatter);
				// Change the entry in the arraylist with the new one
				usersInformation.set(j, newBuyerEntry);
			}
		}
		// Update the number of tickets after tickets have been bought by the buyer
		for (int l = 0; l < ticketInformation.size(); l++){

			String eventTitle = ticketInformation.get(l).substring(0,19);
			int numberOfTicketsAvailable = Integer.parseInt(ticketInformation.get(l).substring(36,39));

			// Update the ticket information with the decrease of the the tickets only if the eventTitle exists
			if (eventTitle.equals(eventTitleFromDTF)){

				int newPriceNumber = numberOfTicketsAvailable - numberOfTicketsBought;
				String newFormatedAvailableTicket = formatPrice.format(newPriceNumber);

				String newEntry = ticketInformation.get(l).replace(ticketInformation.get(l).substring(36, 39), newFormatedAvailableTicket);

				ticketInformation.set(l, newEntry);
			}
		}
	}

	/**
	 * This method is used to perform a refund transaction to the buyer's account by subtracting from the seller's account.
	 * Also checks to see if the user exists in the user information arraylist.
	 * What the Variables Represent
	 * 		buyersUsernameFromDTf - stores the buyers name from the refund transaction line
	 * 		sellersUsernameFromDTf - stores the sellers name from the refund transaction line
	 * 		sellerUsername - the seller's username in the userInformation arraylist
	 * 		sellerBalance - the seller's balance in the userInfomation arraylist
	 * 		newSellerBalance - calculating the new sellers balance after transactions takes place
	 * 		newSellerBalanceFormatter - stores the formatted string of the new seller's balance
	 * 		newSellerEntry - update the entry with the updated balance
	 * 		buyerUsername - stores the buyers username from the user information arraylist
	 * 		buyerBalance - stores the buyers balance from the user information arraylist
	 * 		newBuyerBalance - calculated buyers balance after transaction excuted
	 * 		newBuyerBalanceFormatter - stores the formatted string of the new buyer's balance
	 * @param refundEntry the refund transaction line from the dtf file
	 */
	public static void refund(String refundEntry) {
		String buyersUsernameFromDTF = refundEntry.substring(0, 15);
		String sellersUsernameFromDTF = refundEntry.substring(16, 31);
		double refundAmount = Double.parseDouble(refundEntry.substring(32));

		// Check to see if the Buyer's Username exists in the user information arraylist and updates the balance
		for (int k = 0; k < usersInformation.size(); k++){
			// Stores the parsed buyer's username from the user information arraylist
			String buyerUsername = (usersInformation.get(k).substring(0,15));
			// Stores the buyer's balance
			double buyerBalance = Double.parseDouble(usersInformation.get(k).substring(19, 28));

			// Check if the user exists and only execute if user exists
			if (buyerUsername.equals(buyersUsernameFromDTF)){
				double newBuyerBalance = buyerBalance + refundAmount;
				// Format the Buyer's new balance
				String newBuyerBalanceFormatter = formatbalance.format(newBuyerBalance);
				// replaces the the old buyer's balance with the new balance and stores it in newbuyerEntry
				String newBuyerEntry = usersInformation.get(k).replace(usersInformation.get(k).substring(19, 28), newBuyerBalanceFormatter);
				// Change the entry in the arraylist with the new one
				usersInformation.set(k, newBuyerEntry);
			}
		}

		// Check to see if the Seller's Username exists in the user information arraylist and update the balance
		for (int j = 0; j < usersInformation.size(); j++){
			// Stores the parsed seller's username from the user information arraylist
			String sellerUsername = (usersInformation.get(j).substring(0,15));
			// Stores the seller's balance
			double sellerBalance = Double.parseDouble(usersInformation.get(j).substring(19, 28));

			// Check if the user exists and only execute if user exists
			if (sellerUsername.equals(sellersUsernameFromDTF)){

				double newSellerBalance = sellerBalance - refundAmount;
				// Format the Seller's new balance
				String newSellerBalanceFormatter = formatbalance.format(newSellerBalance);
				// replaces the the old seller's balance with the new balance and stores it in newsellerEntry
				String newSellerEntry = usersInformation.get(j).replace(usersInformation.get(j).substring(19, 28), newSellerBalanceFormatter);
				// Change the entry in the arraylist with the new one
				usersInformation.set(j, newSellerEntry);
			}
		}
	}

	/**
	 * This method is used to addcredit to the designated user in and update the cua with the changes.
	 * What Each Variables Represent
	 * 		addCreditEntry - stores the entire dtf line for that transaction
	 * 		usernameFromDTF - stores the username from the DTF file
	 * 		creditToBeAdded - stores that amount of credit that needs to be added to the user
	 * 		username - stores the usernamne from the CUA for comparing
	 * 		newBalance - stores the new balance to be credited
	 * @param addCreditEntry represents the entire addcredit transaction line from the dtf
	 */
	public static void addCredit(String addCreditEntry) {
		String usernameFromDTF = addCreditEntry.substring(0, 15);

		addCreditEntry = addCreditEntry.substring(22,28);

		double creditToBeAdded = Double.parseDouble(addCreditEntry);
		// Check to see if the user exists in the user information arraylist and update the balance
		for (int j = 0; j < usersInformation.size(); j++){
			String username = usersInformation.get(j).substring(0,15);
			double balance = Double.parseDouble(usersInformation.get(j).substring(19, 28));

			// Check to see if the user exists and only execute if user exists
			if (username.equals(usernameFromDTF)){

				double newBalance = creditToBeAdded + balance;

				String newFormatterBalance = formatbalance.format(newBalance);

				String newEntry = usersInformation.get(j).replace(usersInformation.get(j).substring(19, 28), newFormatterBalance);

				usersInformation.set(j, newEntry);
			}
		}
	}
}

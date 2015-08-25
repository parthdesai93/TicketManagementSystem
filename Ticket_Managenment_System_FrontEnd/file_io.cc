#include "ticket_managenment_system.h"
#include "daily_transaction.h"

/*
 * This class is used to convet the desired string in to a double
 */
class StringConverterHelper {
public:
	// template represents if it is either a conversion from or to a string
	template<typename T>
	static string toString(const T &subject) {
		stringstream ss;
		ss << fixed << setprecision(2) << subject ;
		return ss.str();
	}

	template<typename T>
	static T fromString(const string &subject) {
		stringstream ss(subject);
		T target;
		ss >> target;
		return target;
	}
};



/*
 * This methods is used to parse the trailing underscores of a given string.
 * @param string_length represents the length of the string
 * @param parsing_string
 * @return the new length of the string after the trailing underscores have been removed
 */
int UnderscoreParsing(int string_length, string parsing_string) {
	int i = string_length;
	while (parsing_string[i] == '_') {
		i--;
	}
	i++;
	return i;
}

/*
 * This methods is used replace a single character with a desired character.
 * This methods is mainly used to replace underscores that are present inbetween
 * string, representing spaces. This methods replaces those underscores to be spaces
 * @param str the string on which characters will be replaced
 * @param ch1 the first character which is the character that is going to be replaced
 * @param ch2 the second character which is the character that is going to be replacing character 1
 * @return a string after the replace of character has been applied
 */
string ReplaceCharacter(string str, char ch1, char ch2) {
	for (int i = 0; i < str.length(); i++) {
		if (str[i] == ch1)
			str[i] = ch2;
	}
	return str;
}

/*
 * This method is uesd to read and parse the current user accounts file. The current user accounts file
 * consists of the users that are currently able to access the system. This method also is used to extract the
 * information from the Current_User_Accounts file and parse each line and store each aspect of the users
 * information in a vector, which can be used later on for account validation.
 * @return a vector of type string that store all the information of a user, such as username, account type and balance
 */
vector<string> ReadCurrentUserAccounts() {
	// Defining Variables
	string username = "";
	string account_type = "";
	string balance = "";

	// Constructuing a vector of type string that will store the information of all user in the system
	vector<string> system_user_accounts = vector<string>();

	// This string will be used to store the string before parsing
	string single_user_entry;

	// Opening the "Current_User_Accounts.cua" file
	ifstream current_user_accounts_file(
			"TestCasesOrganization/Current_User_Accounts.cua");

	// This while loop will continue to run until the end of the file
	while (!current_user_accounts_file.eof()) {
		// retrive a single line from the current open file and store it in to the single_user_entry variable
		getline(current_user_accounts_file, single_user_entry);

		// extracting the username from the file
		username = single_user_entry.substr(0, 14);
		// parsing the usernamne to remove the trailing underscores
		int username_parsed = UnderscoreParsing(13, username);
		// assigning the parsed username back to the username variable
		username = username.substr(0, username_parsed);

		/*
		 * after the username has been parsed and the value of username is equal to "END"
		 * end the while loop as the entire file has been parsed.
		 */
		if (username == "END") {
			break;

		}
		// or continue parseing the rest of the string
		else {
			// extracting the account_type and the balance of the user from the file
			account_type = single_user_entry.substr(16, 2);
			balance = single_user_entry.substr(19, 9);
		}
		// storing the username, account_type and the balance into the vector
		system_user_accounts.push_back(username);
		system_user_accounts.push_back(account_type);
		system_user_accounts.push_back(balance);
	}
	// close the file
	current_user_accounts_file.close();

	return system_user_accounts;
}
;

/*
 * This method is uesd to read and parse the Available Tickets file. The available Tickets file
 * consists of the event name the seller's username that is selling that even, the amount of available tickets for that
 * event and also the price to purchase that event. So the purpose of this method is to parse the file and retrive all
 * the apporiate information and store it in a vector for later comparision.
 * @return a vector of type string that store all the information of a user, such as username, account type and balance
 */
vector<string> ReadAvailableTicketFile() {
	// Variable Deleceration
	string event_title = "";
	string seller_username = "";
	string number_of_tickets_available;
	string ticket_price;

	// Constructuing a vector of type string that will store the information of the available tickets
	vector<string> available_tickets_information = vector<string>();

	// This string will be used to store the string before parsing
	string single_available_ticket_entry;

	// Openinf the "Available_Tickets_File.atf"
	ifstream current_available_ticket_file(
			"TestCasesOrganization/Available_Tickets_File.atf");

	// This while loop will continue tun until the end of the file
	while (!current_available_ticket_file.eof()) {
		// retrive a single line from the current open file and store it into the single_available_ticket_entry variable
		getline(current_available_ticket_file, single_available_ticket_entry);

		// extracting the event title from the file
		event_title = single_available_ticket_entry.substr(0, 19);
		// parsing the event title to remove the trailing underscores
		int event_title_parsing = UnderscoreParsing(18, event_title);
		// assigning the parsed event title back to the event_title variable
		event_title = event_title.substr(0, event_title_parsing);
		// replacing the _ character that may be inbetween the sting with an space
		//***event_title = ReplaceCharacter(event_title, '_', ' ');

		/*
		 * after the event_title has been parsed and the value of username is equal to "END"
		 * end the while loop as the entire file has been parsed.
		 */
		if (event_title == "END") {
			break;
		}
		// or continue parseing the rest of the string
		else {

			// extracting the seller's username from the file
			seller_username = single_available_ticket_entry.substr(20, 13);
			// parsing the the seller's username to remove trailing underscores
			int seller_username_parsing = UnderscoreParsing(12,
					seller_username);
			// assigning the parsed seller's username back to the seller_username variable
			seller_username = seller_username.substr(0, seller_username_parsing);

			// extracting the number of tickets available and ticket price from the file
			number_of_tickets_available = single_available_ticket_entry.substr(26, 3);

			ticket_price = single_available_ticket_entry.substr(38, 6);
		}
		// storing the event_title, seller_username, number_of_tickets_avilable and ticket_price into the vector
		available_tickets_information.push_back(event_title);
		available_tickets_information.push_back(seller_username);
		available_tickets_information.push_back(number_of_tickets_available);
		available_tickets_information.push_back(ticket_price);
	}
	// close the file
	current_available_ticket_file.close();

	return available_tickets_information;
}

void WriteDailyTransactionFile(string code, string e_title, string b_username, string s_username, string a_type, int n_tickets, double credit) {
  string transaction_code = code;
  string event_title = e_title;
  string buyers_username = b_username;
  string sellers_username = s_username;
  string account_type = a_type;
  string number_tickets = StringConverterHelper::toString<int>(n_tickets);
  string credit_value = StringConverterHelper::toString<double>(credit);


  if (transaction_code == "01" || transaction_code == "02" || transaction_code ==  "06"){
    for(int a = transaction_code.length(); a < 3; a++){
      transaction_code = transaction_code + '_';
    }
    for (int b = buyers_username.length(); b < 16; b++) {
      buyers_username = buyers_username + '_';
    }
    for (int c = account_type.length(); c < 3; c++) {
      account_type = account_type + '_';
    }

    int length = credit_value.length();
    int difference = 9 - length;

    if (length < 9){
      for(int e = 0; e < difference; e++){
        credit_value = "0" + credit_value;
      }
    }

    daily_transaction_holder.push_back(transaction_code + buyers_username + account_type + credit_value);
  }
  else if (transaction_code == "05"){
        for(int a = transaction_code.length(); a < 3; a++){
          transaction_code = transaction_code + '_';
        }

        for (int b = buyers_username.length(); b < 16; b++) {
          buyers_username = buyers_username + '_';
        }

        for (int c = sellers_username.length(); c < 16; c++) {
          sellers_username = sellers_username + '_';
        }

        int length = credit_value.length();
        int difference = 9 - length;

        if (length < 9){
          for(int e = 0; e < difference; e++){
            credit_value = "0" + credit_value;
          }
        }
      daily_transaction_holder.push_back(transaction_code +sellers_username + buyers_username + credit_value);
  }
  else if (transaction_code == "03" || transaction_code == "04"){
    for(int a = transaction_code.length(); a < 3; a++){
      transaction_code = transaction_code + '_';
    }

    for(int a = event_title.length(); a < 20; a++){
      event_title = event_title + '_';
    }

    for (int c = sellers_username.length(); c < 14; c++) {
      sellers_username = sellers_username + '_';
    }

    int length_ticket_number = number_tickets.length();
    int difference_ticket_number = 3 - length_ticket_number;

    if (length_ticket_number < 3){
      for(int e = 0; e < difference_ticket_number; e++){
        number_tickets = "0" + number_tickets;
      }
    }

    int length_ticket_price = number_tickets.length();
    int difference_ticket_price = 3 - length_ticket_price;

    if (length_ticket_price < 3){
      for(int e = 0; e < difference_ticket_price; e++){
        credit_value = "0" + credit_value;
      }
    }
    daily_transaction_holder.push_back(transaction_code + event_title + sellers_username + number_tickets + "_" + credit_value);
  }
  else if (transaction_code == "00") {
    ofstream outputFile ("Daily_Transaction_File.dtf");

    for (int k = 0; k < daily_transaction_holder.size(); k ++) {
      outputFile << daily_transaction_holder[k] << "\n";
    }

    outputFile << "00";

    outputFile.close();
  }
}





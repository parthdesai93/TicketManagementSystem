#include "ticket_managenment_system.h"

/*transationEvalutor class
 * contains the cases of transaction messages.
 * @param int number
 */
int transationEvalutor(int number) {
  switch (number) {
  case 1:
    cout << "Login Accepted" << endl;
    break;
  case 2:
    cout << "User Successfully deleted" << endl;
    break;
  case 3:
    cout << "Addcredit successful" << endl;
    break;
  case 4:
    cout << "Logout successful" << endl;
    break;
  case 5:
    cout << "Session Terminated" << endl;
    break;
  case 6:
    cout << "Create Successful" << endl;
    break;
  case 7:
    cout << "Delete Successful" << endl;
    break;
  case 8:
    cout << "Sell Successful" << endl;
    break;
  case 9:
    cout << "Buy Successful" << endl;
    break;
  case 10:
    cout << "refund Successful" << endl;
    break;
  case 11:
    cout << "Addcredit Successful" << endl;
    break;
  case 12:
    cout << "Have a Nice Day/Night" << endl;
    break;
  case 13:
    cout << "Successfully Saved Transaction in Daily Transaction File" << endl;
    break;
  }
}


/*errorEvaluator class
 * contains the cases of error messages.
 * @param int code
 * this class would be called upon to provide appropriate methods
 */
void errorEvaluator(int code) {
  switch (code) {
  case -1:
    cout << "Error: must Login first" << endl;
    break;
  case -2:
    cout << "Error: Current user logged in" << endl;
    break;
  case -3:
    cout << "Error: admin Login required" << endl;
    break;
  case -4:
    cout << "Error: Username does not exist" << endl;
    break;
  case -5:
    cout << "Error: Invalid characters" << endl;
    break;
  case -6:
    cout << "Error: Must login before logging out" << endl;
    break;
  case -7:
    cout << "Error: Username already in use, please enter a unique username"
        << endl;
    break;
  case -8:
    cout
        << "Error: Username is greater than the maximum allowed number of characters: 15. Please try again."
        << endl;

    break;
  case -9:
    cout << "Error: Only admin users are able to create new users." << endl;
    break;
  case -10:
    cout
        << "Error: The maximum credit allowed to be applied to an account is $1,000,000.00"
        << endl;
    cout<<""<<endl;
    break;
  case -11:
    cout << "Error: The Amount of Credit can only be of numeric value."
        << endl;

  case -12:
    cout << "Error: Only admin users are able to delete new users." << endl;
    break;
  case -13:
    cout << "Error: User does not exist, please try again." << endl;
    break;
  case -14:
    cout << "Error: Sorry your account has been deleted." << endl;
    break;
  case -15:
    cout
        << "Error: Buy-Standard user is unable to perform the action, Enter Session Type( AA, FS,SS)"
        << endl;
    break;
  case -16:
    cout
        << "Error: Maximum sale price exceeded, Re-Enter Sale Price (999.99 or less)"
        << endl;
    break;
  case -17:
    cout
        << "Error: Maximum Length of Title exceeded, Re-Enter Event Title (19 or less)"
        << endl;
    break;
  case -18:
    cout
        << "Error: Maximum Number of Tickets exceeded, Re-Enter Tickets for sale (100 or less)"
        << endl;
    break;
  case -19:
    cout << "Error: User needs to log out first" << endl;
    break;
  case -20:
    cout << "Error: Event Title does not exist, Re-enter Event Tile"
        << endl;
    break;
  case -21:
    cout << "Error: Not Enough Tickets Available, Re-Enter Number of Ticket"
        << endl;
    break;
  case -22:
    cout
        << "Error: Number of Tickets Exceeded, Re-Enter Number of Tickets (4 or less)"
        << endl;
    break;
  case -23:
    cout
        << "Error: refund transaction cannot be performed as a full-standard user, re-enter Session Type"
        << endl;
    break;
  case -24:
    cout << "Error: Seller does not exist, re-enter" << endl;
    break;
  case -25:
    cout
        << "Error: Seller does not have sufficient funds to perform transaction, re-enter new credit amount or Logout"
        << endl;
    break;
  case -26:
    cout
        << "Error: Seller�s credit balance has reached a maximum of 999999.00, re-enter refund amount or Logout"
        << endl;
    break;
  case -27:
    cout << "Error: Addcredit cannot be performed by a Sell-Standard user"
        << endl;
    break;
  case -28:
    cout << "Error: Username does not exist, Re-Enter Account Username"
        << endl;
    break;
  case -29:
    cout << "Error: Account Username left blank, Re-Enter Account Username"
        << endl;
    break;
  case -30:
    cout
        << "Error: Maximum add credit exceeded, Re-Enter Credit Amount (1000.00 or Less)"
        << endl;
    break;
  case -31:
    cout
        << "Error: Total Maximum credit reached 999999.00, re-enter credit amount"
        << endl;
    break;
  case -32:
    cout << "Error: Credit Amount Left Blank, Re-Enter Credit" << endl;
    break;
  case -33:
    cout
        << "Error: credit value of negative can�t be added, re-enter credit amount"
        << endl;
    break;
  case -34:
    cout
        << "Error: Incorrect credit amount form (xxx.xx where x is integer only ), re-enter credit amount"
        << endl;
    break;
  case -35:
    cout << "Error: Invalid Command, retry" << endl;
    break;
  case -36:
    cout
        << "Error Sell- Standard user is unable to perform the action, Re-Enter Session Type (AA,FS,BS) "
        << endl;
    break;
  case -37:
    cout << "Error: Not a valid Account Type, Enter (AA,FS,BS,BB) " << endl;
    break;
  case -38:
    cout << "Error: Not Enough Tickets Available, Re-Enter Number of Ticket"
        << endl;
    break;
  case -39:
	cout << "Error: Invalid Session Type. Enter (AA,FS,BS,SS)"
	     << endl;
	break;
  case -40:
     cout << "Error: Buyer does not exist, re-enter" << endl;
     break;
  case - 41:
    cout << "Error: Not enough credit available" << endl;
    break;
  }
}

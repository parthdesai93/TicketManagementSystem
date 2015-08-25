/*main class
 * contains the main method of the program
 */
#include "ticket_managenment_system.h"
 bool done = false;

int main() {

  string input = "";

  cout << "Welcome to the Ticket Master" << endl;

  while (!done) {
    cout << "Enter Command:"<<endl;
    cin >> input;

    if (input == "login") {
      loging();

    } else if (input == "create") {
      create();
    } else if (input == "delete") {
      delete1();
    } else if (input == "sell") {
      sell();
    } else if (input == "buy") {
      buy();
    } else if (input == "refund") {
      refund();
    } else if (input == "addcredit") {
      addcredit();
    } else if (input == "logout") {
      logout();
    } else if (input == "Quit") {
     quit();
    	break;

    }

    else {
      errorEvaluator(-35);
    }
  }

}

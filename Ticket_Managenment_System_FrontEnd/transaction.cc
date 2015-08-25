#include "ticket_managenment_system.h"
#include "constant.h"
bool logged_AA = false;
bool logged_FS = false;
bool logged_SS = false;
bool logged_BS = false;
double creditamount;

/*logging class
 * It would handle the user login into the system and display appropriate errors.
 */

int
loging() {
  //checks if any type of user account is logged in
  if (!logged_AA and !logged_FS and !logged_SS and !logged_BS) {
    string sessiontype = "";
    cout << "Enter Session Type:" << endl;
    cin >> sessiontype;
    if (sessiontype == "AA") {
      string username = "";
      cout << "Enter Username:" << endl;
      cin >> username;
      SetUsername(username);
      //calls validateUser() to see if the username and sessiontype are valid
      logged_AA = ValidateUser(username, sessiontype);
      if (logged_AA == true) {
        transationEvalutor(1);
      }
      else {
        errorEvaluator(-4);
      }

    }
    else if (sessiontype == "FS") {
      string username = "";
      cout << "Enter Username:" << endl;
      cin >> username;
      SetUsername(username);
      //calls validateUser() to see if the username and sessiontype are valid
      logged_FS = ValidateUser(username, sessiontype);
      if (logged_FS == true) {
        transationEvalutor(1);
      }
      else {
        errorEvaluator(-4);
      }
    }

    else if (sessiontype == "SS") {
      string username = "";
      cout << "Enter Username:" << endl;
      cin >> username;
      SetUsername(username);
      //calls validateUser() to see if the username and sessiontype are valid
      logged_SS = ValidateUser(username, sessiontype);
      if (logged_SS == true) {
        transationEvalutor(1);
      }
      else {
        errorEvaluator(-4);
      }

    }
    else if (sessiontype == "BS") {
      string username = "";
      cout << "Enter Username:" << endl;
      cin >> username;
      SetUsername(username);
      //calls validateUser() to see if the username and sessiontype are valid
      logged_BS = ValidateUser(username, sessiontype);
      if (logged_BS == true) {
        transationEvalutor(1);
      }
      else {
        errorEvaluator(-4);
      }

    }
    else if (sessiontype == "Quit") {
      quit();

    }
    else {
      errorEvaluator(-39);
    }
  }
  else {
    errorEvaluator(-2);
  }
}

/*logout class
 * It would perform logout and display the appropriate errors.
 */
int
logout() {
  if (logged_AA or logged_FS or logged_SS or logged_BS) {
    logged_AA = false;
    logged_FS = false;
    logged_SS = false;
    logged_BS = false;
    transationEvalutor(5);
    transationEvalutor(13);

  }
  else {
    errorEvaluator(-1);
  }
}

/*create class
 * It would handle the buy transactions and display the appropriate errors.
 */
int
create() {
  if (logged_AA) {
    string newusername = "";
    cout << "New Username:" << endl;
    cin >> newusername;

    if (newusername.length() > 15) {
      errorEvaluator(-8);
      create();

    }
    //calls validateUser() to see if the username and sessiontype are valid
    else if (ValidateUser(newusername, "AA") == false) {
      string newsession = "";
      cout << "Enter User Type:" << endl;
      cin >> newsession;
      //makes sure the new session type is valid entry
      if (newsession == "AA" || newsession == "FS" || newsession == "BS" || newsession == "SS") {
          double creditamount = 0.0;
          cout << "Amount of Credit:" << endl;
          cin >> creditamount;
          if (creditamount > MAX_ACCOUNT_CREDIT) {
            errorEvaluator(-10);
            cout << "Amount of Credit:" << endl;
            cin >> creditamount;
          }

          //writes function would be implemented here it woul take these three parameters (newusername,newsession,creditamount)
          WriteDailyTransactionFile("01", " ", newusername, " ", newsession, NULL, creditamount);
          transationEvalutor(6);

      }
      else {
        errorEvaluator(-37);
        create();
      }

    }
    else {
      errorEvaluator(-7);
      create();
    }
  }
  else if (logged_FS or logged_SS or logged_BS) {
    errorEvaluator(-9);
  }
  else {
    errorEvaluator(-1);
  }
}

/*delete1 class
 * It would delete the user and display the appropriate errors.
 */
int
delete1() {
  if (logged_AA) {
    string delusername;
    double creditamount;
    cout << "Enter Username to Delete:" << endl;
    cin >> delusername;
    if (ValidateUser(delusername, "AA") == true) {
      creditamount = ValidateBalance(delusername);
      WriteDailyTransactionFile("02", " ", delusername, " ", "AA", NULL,
          creditamount);
      transationEvalutor(7);
    }
    else if (ValidateUser(delusername, "FS") == true) {
      creditamount = ValidateBalance(delusername);
      WriteDailyTransactionFile("02", " ", delusername, " ", "FS", NULL,
          creditamount);
      transationEvalutor(7);
    }
    else if (ValidateUser(delusername, "BS") == true) {
      creditamount = ValidateBalance(delusername);
      WriteDailyTransactionFile("02", " ", delusername, " ", "BS", NULL,
          creditamount);
      transationEvalutor(7);
    }
    else if (ValidateUser(delusername, "SS") == true) {
      creditamount = ValidateBalance(delusername);
      WriteDailyTransactionFile("02", " ", delusername, " ", "SS", NULL,
          creditamount);
      transationEvalutor(7);
    }
    else {
      errorEvaluator(-13);
    }
  }
  else if (logged_FS or logged_SS or logged_BS) {
    errorEvaluator(-12);
  }
  else {
    errorEvaluator(-1);
  }
}

/*buy class
 * It would handle the buy transactions and display the appropriate errors.
 */
int
buy() {
  if (logged_AA or logged_FS or logged_BS) {
    string eventname = "";
    cout << "Enter Event Title:" << endl;
    cin >> eventname;
    //calls validateEvent() to see if the eventname is invalid

    if (ValidateEvent(eventname) == true) {
      string sellusername;
      cout << "Enter Seller`s Username:" << endl;
      cin >> sellusername;
      //calls validateUser() to see if the username and sessiontype are valid
      if (ValidateUser(sellusername, "AA")) {
        errorEvaluator(-24);
        cout << "Enter Seller`s Username:" << endl;
        cin >> sellusername;
      }
      int numtickets;
      cout << "Enter Number of Tickets:" << endl;
      cin >> numtickets;
      if (logged_FS or logged_SS or logged_BS) {
        if (numtickets > MAX_NUM_TICKET) {
          errorEvaluator(-22);
          cout << "Enter Number of Tickets:" << endl;
          cin >> numtickets;
        }
      }

      double price = RetrivePrice(eventname);
      double total = numtickets * price;
      cout << "Event Title Cost for 1 Ticket is " << price << ".00" << endl;
      cout << "Total Cost " << total << ".00" << endl;
      cout << "Do you wish to continue(yes/no): " << endl;
      string response = "";
      cin >> response;
      if (response == "yes") {
        string Username = RetriveUsername();
        if(ValidateBalance(Username) >= total){
          transationEvalutor(9);
        }
        else{
          errorEvaluator(-41);
        }
      }
      WriteDailyTransactionFile("04", eventname, " ", sellusername, " ",
          numtickets, price);
    }
    else {
      errorEvaluator(-20);
      buy();
    }

  }
  else if (logged_SS) {
    errorEvaluator(-36);
  }
  else {
    errorEvaluator(-1);
  }
}
/*sell class
 * It would handle the sell transactions and display the appropriate errors.
 */
int
sell() {
  //checks if the admin or full standards or sell standard are logged in
  if (logged_AA or logged_FS or logged_SS) {
    string eventname;
    double saleprice;
    int tickets;

    cout << "Enter Event Title:" << endl;
    cin >> eventname;
    //makes sure the event length is valid
    if (eventname.length() > 19) {
      errorEvaluator(-17);
      cout << "Enter Event Title:" << endl;
      cin >> eventname;
    }

    cout << "Enter Sale Price:" << endl;
    cin >> saleprice;
    //makes sure that the sale price is valid
    if (saleprice > 999.99) {
      errorEvaluator(-16);
      cout << "Enter Sale Price:" << endl;
      cin >> saleprice;
    }

    cout << "Enter Number of Tickets for sale:" << endl;
    cin >> tickets;
    //makes sure that the number of tickets for sale are valid
    if (tickets > 100) {
      errorEvaluator(-18);
      cout << "Enter Number of Tickets for sale:" << endl;
      cin >> tickets;
    }
    string Username = RetriveUsername();
    WriteDailyTransactionFile("03", eventname, " ", Username, " ", tickets,
        saleprice);
    transationEvalutor(8);

  }
  else if (logged_BS) {
    errorEvaluator(-15);
  }
  else {
    errorEvaluator(-1);
  }
}

/*add class
 * It would perform addcredit to the user accounts and display the appropriate errors.
 */
int
addcredit() {
  if (logged_AA) {
    double credit;
    string username = "";
    cout << "Credit Amount:" << endl;
    cin >> credit;
    cout << "Account Username:" << endl;
    cin >> username;
    //calls validateUser() to see if the username and sessiontype are valid
    if (ValidateUser(username, "AA") == true) {
      if (credit < 0) {
        errorEvaluator(-33);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //makes sure that credit deposit does not exceed maximum credit allowed
      if (credit > MAX_ADD_CREDIT) {
        errorEvaluator(-30);
        cout << "Credit Amount:" << endl;
        cin >> credit;

      }
      double balance = (ValidateBalance(username));
      if ((balance + credit) > MAX_ACCOUNT_CREDIT) {
        errorEvaluator(-31);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }

      //makes sure user name is entered
      if (username.length() == 0) {
        errorEvaluator(-29);
        cout << "Credit Amount:" << endl;
        cin >> credit;
        cout << "Account Username:" << endl;
        cin >> username;
      }
      //addcredit method to addcredit into the file will be implemented
      WriteDailyTransactionFile("06", " ", username, " ", "AA", NULL, credit);

    }
    else if (ValidateUser(username, "FS") == true) {
      if (credit < 0) {
        errorEvaluator(-33);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //makes sure that credit deposit does not exceed maximum credit allowed
      if (credit > MAX_ADD_CREDIT) {
        errorEvaluator(-30);
        cout << "Credit Amount:" << endl;
        cin >> credit;

      }
      double balance = (ValidateBalance(username));
      if ((balance + credit) > MAX_ACCOUNT_CREDIT) {
        errorEvaluator(-31);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }

      //makes sure user name is entered
      if (username.length() == 0) {
        errorEvaluator(-29);
        cout << "Credit Amount:" << endl;
        cin >> credit;
        cout << "Account Username:" << endl;
        cin >> username;
      }

      //addcredit method to addcredit into the file will be implemented
      WriteDailyTransactionFile("06", " ", username, " ", "FS", NULL, credit);
    }
    else if (ValidateUser(username, "SS") == true) {
      if (credit < 0) {
        errorEvaluator(-33);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //makes sure that credit deposit does not exceed maximum credit allowed
      if (credit > MAX_ADD_CREDIT) {
        errorEvaluator(-30);
        cout << "Credit Amount:" << endl;
        cin >> credit;

      }
      double balance = (ValidateBalance(username));
      if ((balance + credit) > MAX_ACCOUNT_CREDIT) {
        errorEvaluator(-31);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }

      //makes sure user name is entered
      if (username.length() == 0) {
        errorEvaluator(-29);
        cout << "Credit Amount:" << endl;
        cin >> credit;
        cout << "Account Username:" << endl;
        cin >> username;
      }
      //addcredit method to addcredit into the file will be implemented
      WriteDailyTransactionFile("06", " ", username, " ", "SS", NULL, credit);
    }
    else if (ValidateUser(username, "BS") == true) {
      if (credit < 0) {
        errorEvaluator(-33);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //makes sure that credit deposit does not exceed maximum credit allowed
      if (credit > MAX_ADD_CREDIT) {
        errorEvaluator(-30);
        cout << "Credit Amount:" << endl;
        cin >> credit;

      }
      double balance = (ValidateBalance(username));
      if ((balance + credit) > MAX_ACCOUNT_CREDIT) {
        errorEvaluator(-31);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }

      //makes sure user name is entered
      if (username.length() == 0) {
        errorEvaluator(-29);
        cout << "Credit Amount:" << endl;
        cin >> credit;
        cout << "Account Username:" << endl;
        cin >> username;
      }
      //addcredit method to addcredit into the file will be implemented
      WriteDailyTransactionFile("06", " ", username, " ", "BS", NULL, credit);
    }
    else {
      errorEvaluator(-28);
      cout << "Account Username:" << endl;
      cin >> username;
    }
    transationEvalutor(3);
  }
  else if (logged_FS or logged_BS) {
    if (logged_FS) {
      double credit;
      cout << "Credit Amount:" << endl;
      cin >> credit;

      while (cin.fail()) {
        errorEvaluator(-34);
        cin.clear();
      }

      if (credit < 0) {
        errorEvaluator(-33);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //makes sure credit to be deposited does not exceed the maximum allowed
      if (credit > MAX_ADD_CREDIT) {
        errorEvaluator(-30);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      string Username = RetriveUsername();

      double balance = (ValidateBalance(Username));
      if ((balance + credit) > MAX_ACCOUNT_CREDIT) {
        errorEvaluator(-31);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //addcredit method to addcredit into the file will be implemented
      WriteDailyTransactionFile("06", " ", Username, " ", "FS", NULL, credit);
      //addcredit(credit);
      transationEvalutor(3);
    }
    else if (logged_BS) {
      double credit;
      cout << "Credit Amount:" << endl;
      cin >> credit;

      while (cin.fail()) {
        errorEvaluator(-34);
        cin.clear();
        cin.clear();
      }
      if (credit < 0) {
        errorEvaluator(-33);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //makes sure credit to be deposited does not exceed the maximum allowed
      if (credit > MAX_ADD_CREDIT) {
        errorEvaluator(-30);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      string Username = RetriveUsername();

      double balance = (ValidateBalance(Username));
      if ((balance + credit) > MAX_ACCOUNT_CREDIT) {
        errorEvaluator(-31);
        cout << "Credit Amount:" << endl;
        cin >> credit;
      }
      //addcredit method to addcredit into the file will be implemented
      WriteDailyTransactionFile("06", " ", Username, " ", "BS", NULL, credit);
      //addcredit(credit);
      transationEvalutor(3);

    }
  }
  else if (logged_SS) {
    errorEvaluator(-27);
  }
  else {
    errorEvaluator(-1);
  }
}
/*refund class
 * It would perform refund to the user accounts and display the appropriate errors.
 */
int
refund() {
  if (logged_AA) {
    string sellusername;
    string buyusername;
    cout << "Seller`s Username:" << endl;
    cin >> sellusername;
    cout << "Buyer`s Username:" << endl;
    cin >> buyusername;
    //calls validateUser() to see if the username and sessiontype are valid

    double credit;
    cout << "Amount of Credit to Transfer:" << endl;
    cin >> credit;
    if (ValidateUser(sellusername, "AA") or ValidateUser(sellusername, "FS")
        or ValidateUser(sellusername, "BS")
        or ValidateUser(sellusername, "SS") == true) {
      if ((ValidateUser(buyusername, "AA")) or (ValidateUser(buyusername, "FS"))
          or (ValidateUser(buyusername, "BS"))
          or (ValidateUser(buyusername, "SS")) == true) {
        //ValidateBalace() is called to check if refund issued is not more the user's account balance
        //cout << ValidateBalance(sellusername);
        if (ValidateBalance(sellusername) < credit) {
          errorEvaluator(-25);

        }
        //ValidateBalace() is called to check if buyer balance does not exceed maximum allowed credit
        if ((ValidateBalance(buyusername) + credit) > MAX_ACCOUNT_CREDIT) {
          errorEvaluator(-26);
        }
        //refund function will be implemented later
        WriteDailyTransactionFile("05", " ", buyusername, sellusername, " ",
            NULL, credit);
        //refund(sellusername,buyusername,credit);

      }
      else {
        errorEvaluator(-40);
        cout << "Buyer`s Username:" << endl;
        cin >> buyusername;
      }
    }
    else {
      errorEvaluator(-24);
      cout << "Seller`s Username:" << endl;
      cin >> sellusername;
    }

    transationEvalutor(10);

  }
  else if (logged_FS or logged_SS or logged_BS) {
    errorEvaluator(-23);
    logged_AA = false;
    logged_FS = false;
    logged_SS = false;
    logged_BS = false;
    loging();
  }
  else {
    errorEvaluator(-1);
  }
}

int
quit() {
  transationEvalutor(12);
  logged_AA = false;
  logged_FS = false;
  logged_SS = false;
  logged_BS = false;
//function would be implemented to write to the files
  WriteDailyTransactionFile("00", " ", " ", " ", " ", NULL, 0.0);
//writetofile();
}

#include "ticket_managenment_system.h"
#include "username_storage.h"

/*
 * This class is used to convet the desired string in to a double
 */
class StringConverterHelper {
  public:
    // template represents if it is either a conversion from or to a string
    template<typename T>
      static string
      toString(const T &subject) {
        stringstream ss;
        ss << fixed << setprecision(2) << subject ;
        return ss.str();
      }

    template<typename T>
      static T
      fromString(const string &subject) {
        stringstream ss(subject);
        T target;
        ss >> target;
        return target;
      }
};

/*
 * This methids represents a retrivie of the users from the main vector
 */
vector<string>
GetUsers() {
  //Storing return user information vector from the File I/O class
  vector<string> user_information = vector<string>(ReadCurrentUserAccounts());
  vector<string> all_usernames = vector<string>();

  //Extracting only the user names user information vector
  for (int j = 0; j < user_information.size(); j += 3) {
    all_usernames.push_back(user_information[j]);
  }
  return all_usernames;
}

/*
 * This method parse the session type from the main vector and stores it into a new vector
 */
vector<string>
GetSessionType() {
  //Storing return user information vector from the File I/O class
  vector<string> user_session = vector<string>(ReadCurrentUserAccounts());
  vector<string> all_user_sessions = vector<string>();

  //Extracting only the user names user information vector
  for (int j = 1; j < user_session.size(); j += 3) {
    all_user_sessions.push_back(user_session[j]);
  }
  return all_user_sessions;
}

/*
 * This method parses the balance from the main vector and stores it into a new vector
 */
vector<string>
GetBalance() {
  vector<string> user_balance = vector<string>(ReadCurrentUserAccounts());
  vector<string> all_user_balances = vector<string>();

  for (int j = 2; j < user_balance.size(); j += 3) {
    all_user_balances.push_back(user_balance[j]);
  }
  return all_user_balances;
}

/*
 * This method is used to validate the user
 * @param username shows the username that needs to be validated
 * @param session_type shows the session type of the user
 */
bool
ValidateUser(string username, string session_type) {
  string compare_username = username;
  string compare_session_type = session_type;

  vector<string> system_users = vector<string>(GetUsers());
  vector<string> system_users_session_type = vector<string>(GetSessionType());

  for (int j = 0; j < system_users.size(); j++) {
    //Comparing inputs with vectors

    if (system_users.at(j) == compare_username
        && system_users_session_type.at(j) == compare_session_type) {

      return true;
    }

  }
  return false; //false
}
;

/*
 * This method is used to validate the user's balance to make sure that there is succefient funds
 * @param username shows the username that needs to be validated to retrive the balance for that account
 */
double
ValidateBalance(string username) {
  string system_username = username;

  vector<string> system_user_balance = vector<string>(GetBalance());
  vector<string> system_users = vector<string>(GetUsers());

  for (int j = 0; j < system_users.size(); j++) {
    if (system_users.at(j) == system_username) {
      double balance = StringConverterHelper::fromString<double>(
          system_user_balance[j]);

      return balance;
      break;
    }
  }
}

void SetUsername (string user) {
  username = user;
}

string RetriveUsername (){
  return username;
}

#include <string>
#include <vector>
#include <iostream>
#include <sstream>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <iomanip>


using namespace std;


// the functions that are used for the transactions 
int loging();
int buy();
int sell();
int create();
int delete1();
int addcredit();
int refund();
int logout();
int quit();

// methiods that are used to perform a corrent transaction
int UnderscoreParUing(int, string);
string ReplaceChar(string, char, char);
vector<string> ReadCurrentUserAccounts();
vector<string> ReadAvailableTicketFile();
void WriteDailyTransactionFile(string, string, string, string, string, int, double);

vector<string> GetUsers();
vector<string> GetSessionType();
vector<string> GetBalance();
bool ValidateUser(string, string);
double ValidateBalance(string);
void RemoveUser(string);
void AddUser(string, string, double);
void SetUsername (string);
string RetriveUsername ();

vector<string> GetEventTitle();
bool ValidateEvent(string);
vector<string> GetPrice();
double RetrivePrice(string);

// ethids that are used for user interface handeling 
int transationEvalutor(int number);
void errorEvaluator(int code);



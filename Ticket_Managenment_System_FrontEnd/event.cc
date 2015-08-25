#include "ticket_managenment_system.h"

/*
 * This class is used to convet the desired string in to a double
 */
class StringConverterHelper {
public:
	// template represents if it is either a conversion from or to a string
	template<typename T>
	static string toString(const T &subject) {
		stringstream ss;
		ss << subject;
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
 * This methods retrive the title from the main vectore and stores it into a new vector
 */
vector<string> GetEventTitle() {
	//Storing return ticket vector from the File I/O class
	vector<string> ticket_information = vector<string>(
			ReadAvailableTicketFile());
	vector<string> all_event_titles = vector<string>();

	//Extracting only the event titles from the ticket vector
	for (int j = 0; j < ticket_information.size(); j += 4) {
		all_event_titles.push_back(ticket_information[j]);
	}
	return all_event_titles;
}

//Checking to see if the input event title is an existing one
bool ValidateEvent(string event_title) {
	string compare_event_title = event_title;

	vector<string> system_event_title = vector<string>(GetEventTitle());

	for (int j = 0; j < system_event_title.size(); j++) {
		//Comparing input with existing event titles
		//cout<< system_event_title.at((j))<<endl;
		if (system_event_title.at(j) == compare_event_title) {
			return true;
		}
	}
	return false;
}

/*
 * This method is used to retrive the prive of the ticket from the main vector
 */
vector<string> GetPrice() {
	//Storing return ticket vector from the File I/O class
	vector<string> ticket_information = vector<string>(
			ReadAvailableTicketFile());
	vector<string> all_event_price = vector<string>();

	//Extracting only the event price from the ticket vector
	for (int j = 3; j < ticket_information.size(); j += 4) {
		all_event_price.push_back(ticket_information[j]);
	}
	return all_event_price;
}

/*
 * This method is used to retrive the price depending on the event name
 * @param event_title is the title of the event that the price is required
 */
double RetrivePrice(string event_title) {
	vector<string> system_event_price = vector<string>(GetPrice());
	vector<string> system_event_title = vector<string>(GetEventTitle());
	double price;
	for (int j = 0; j < system_event_title.size(); j++) {
		//Comparing input with existing event titles
		if (system_event_title.at(j) == event_title) {
			price = StringConverterHelper::fromString<double> (system_event_price.at(j));

		}
	}
	return price;
}
;



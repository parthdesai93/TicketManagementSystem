ticket: transaction.o evaluator.o main.o  event.o file_io.o user.o
	g++ -o ticket transaction.o evaluator.o main.o event.o file_io.o user.o

transaction.o: transaction.cc constant.h ticket_managenment_system.h
	g++ -c transaction.cc

evaluator.o: evaluator.cc ticket_managenment_system.h
	g++ -c evaluator.cc

main.o: main.cc ticket_managenment_system.h
	g++ -c main.cc
event.o: event.cc ticket_managenment_system.h
	g++ -c event.cc
file_io.o: file_io.cc ticket_managenment_system.h daily_transaction.h 
	g++ -c file_io.cc
user.o: user.cc ticket_managenment_system.h username_storage.h
	g++ -c user.cc


clean:
	rm ticket transaction.o evaluator.o main.o event.o file_io.o user.o

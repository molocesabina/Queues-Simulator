# Queues-Simulator
A simulation application aiming to analyse queuing based systems for 
determining and minimizing client's waiting time. 

The application simulates a series of N clients arriving for service, entering Q queues,
waiting, being served and finally leaving the queues. All clients are generated when the
simulation is started, and are characterized by three parameters: ID (a number between 1 and N), 
𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to go to the queue; i.e. time when the client finished shopping)
and 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the  client; i.e. waiting time when the client is in front of the queue). 
The application tracks the total time spent by every client in the queues and computes the average waiting time.

I used multithreading (one thread per queue), appropriate synchronized data structures to assure thread safety, log
of events and Java Swing.

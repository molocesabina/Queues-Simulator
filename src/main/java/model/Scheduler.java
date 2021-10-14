package model;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;



public class Scheduler {
	private List<Queue> queues;
	
	public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
		queues = new ArrayList<Queue>();
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<maxNoQueues;i++) {
			Queue s= new Queue(new ArrayBlockingQueue<Client>(maxClientsPerQueue), new AtomicInteger(0));
			queues.add(s);
			threads.add(new Thread(s));
		}
		for(Thread t: threads) {
			t.start();
		}
	}
	
	public int  dispatchClient(Client c) {
		int min=Integer.MAX_VALUE;
		for(Queue q: queues) {
			if(q.getWaitingPeriod().getPlain()<min) {
				min=q.getWaitingPeriod().getPlain();	
			}
		}
		for(Queue q:queues) {
			if(q.getWaitingPeriod().getPlain()==min) {
				int howMuchUntilFront = q.getWaitingPeriod().getPlain();
				q.addClient(c);
				return howMuchUntilFront;
			}
		}
		return 0;
	}
    

	public List<Queue> getQueues() {
		return queues;
	}
	

}

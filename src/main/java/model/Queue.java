package model;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    
    private AtomicBoolean stop=new AtomicBoolean(false);
    
    public Queue(ArrayBlockingQueue<Client> c, AtomicInteger wp) {
    	clients=c;
    	waitingPeriod=wp;
  
    }
    
    public void setStop(AtomicBoolean stop) {
		this.stop = stop;
	}

	public void addClient(Client newClient) {
    	clients.add(newClient);
    	waitingPeriod.addAndGet(newClient.getServiceTime());
    }
    
	public void run() {
		while(stop.get() == false) {
			try {	
				if(clients.peek()!=null) {
					if(clients.peek().getServiceTime()!=1) {
						waitingPeriod.addAndGet(-1);
						clients.peek().setServiceTime(clients.peek().getServiceTime()-1);
					}
					else {
						clients.remove();
						waitingPeriod.addAndGet(-1);
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	

	public BlockingQueue<Client> getClients() {
		return clients;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

    
}


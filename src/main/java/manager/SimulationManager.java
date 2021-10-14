package manager;
import model.*;
import gui.*;
import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class SimulationManager implements Runnable{
		private int timeLimit;
		private int maxServiceTime;
		private int minServiceTime;
		private int maxArrivalTime;
		private int minArrivalTime;
		private int numberOfQueues;
		private int numberOfClients;
		
	   
		private String log;
		private int peakHour;
		private float averageWaitingTime=0;
		private float averageServiceTime=0;
		private int nbMaxClients=0;
		
		private SimulationFrame gui;
		private Scheduler scheduler;
		private List<Client> generatedClients;
		
		public SimulationManager(SimulationFrame gui) {
			this.gui = gui;
			timeLimit=gui.getSimulationTimeI();
			maxServiceTime=gui.getMaxServiceTimeI();
			minServiceTime=gui.getMinServiceTimeI();
			maxArrivalTime=gui.getMaxArrivalTimeI();
			minArrivalTime=gui.getMinArrivalTimeI();
			numberOfQueues=gui.getNbQueuesI();
		    numberOfClients=gui.getNbClientsI();
			scheduler = new Scheduler(numberOfQueues,numberOfClients);
			generatedClients = new ArrayList<Client>();
			generateNRandomClients();
			Collections.sort(generatedClients);		
		}
        
		public void generateNRandomClients() {
			Random r = new Random();
			for(int i=1;i<=numberOfClients;i++) {
				int serviceTime = r.nextInt((maxServiceTime - minServiceTime) + 1) + minServiceTime;
			    int arrivalTime = r.nextInt((maxArrivalTime - minArrivalTime) + 1) + minArrivalTime;
				Client c = new Client(i, arrivalTime, serviceTime);
				generatedClients.add(c);
			}
		}
		
		
		public boolean continueSimulation() {
			boolean dontStop = false;
			for(Queue s: scheduler.getQueues()) {
				dontStop = dontStop | (!s.getClients().isEmpty());
			}
			dontStop = dontStop | (!generatedClients.isEmpty());
		    return dontStop;
		}
		
		
		public void run() {
			int currentTime=0;
		    int nbClientsThatEnteredQueue=0;
		    log="";
			List<Client> toDelete = new ArrayList<Client>();
			while(currentTime<timeLimit && continueSimulation()) {	
				for(Client c: generatedClients) {
					if(c.getArrivalTime()==currentTime) {
						averageWaitingTime+=scheduler.dispatchClient(c);
						averageServiceTime+=c.getServiceTime();
						nbClientsThatEnteredQueue++;
						toDelete.add(c);
					}
				}
				gui.updateSimulation(scheduler.getQueues(), currentTime); 
				generatedClients.removeAll(toDelete);
			    writeToLog(currentTime);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentTime++;
			}
		    stopQueues();
		    averageWaitingTime /= nbClientsThatEnteredQueue;
			averageServiceTime /= nbClientsThatEnteredQueue;
			finishLog();
			writeLogToFile();
			gui.updateFinished(averageWaitingTime,averageServiceTime,peakHour);
		}
		
		public void stopQueues() {
			for(Queue s: scheduler.getQueues()) {
				s.setStop(new AtomicBoolean(true));
			}
		}
		
	
		
		public void writeToLog(int currentTime) {
			log += "Time "+currentTime+'\n'+"Waiting clients: ";
			for(Client c: generatedClients) {
				log+="("+c.getId()+","+c.getArrivalTime()+","+c.getServiceTime()+"); ";
			}
			log += '\n';
			int i=1;
			int nbClientsInQueues=0;
			for(Queue q: scheduler.getQueues()) {
				log += "Queue "+i+": ";
				int ok = 0;
				for(Client c: q.getClients()) {
					nbClientsInQueues++;
					ok = 1;
					log += "("+c.getId()+","+c.getArrivalTime()+","+c.getServiceTime()+"); ";
				}
				if(ok==0) {
					log += "closed";
				}
				log+='\n';
				i++;
			}
			if(nbClientsInQueues>nbMaxClients) {
				nbMaxClients=nbClientsInQueues;
				peakHour=currentTime;
			}
		    log += '\n';
		}
		
		public void finishLog() {
			log += "Simulation finished"+'\n';
			log += "Average waiting time : "+averageWaitingTime+'\n';
			log += "Average service time : "+averageServiceTime+'\n';
			log +="Peak Hour : "+peakHour;
		}
		
		public void writeLogToFile() {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("Log.txt"));
			    out.write(log);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

}

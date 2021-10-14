package model;

public class Client implements Comparable<Client> {
	private int id;
	private int arrivalTime;
	private int serviceTime;
	
	public Client(int i, int a, int p) {
		id=i;
		arrivalTime=a;
		serviceTime=p;
	}
	

	public int compareTo(Client o) {
		if(this.arrivalTime<o.arrivalTime)
			return -1;
		else if(this.arrivalTime==o.arrivalTime)
			return 0;
		return 1;
	}

	public int getId() {
		return id;
	}



	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	
}


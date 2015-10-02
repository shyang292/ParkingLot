import java.util.ArrayList;
import java.util.Random;




public class Car implements Runnable{

	
	private Thread t;
	private ArrayList<Entry> entries;
	private Entry entry;
	private ArrayList<Exit> exits;
	private Exit exit;
	private boolean willExit;
	public enum CarStatus {ENTERING, EXITING, PARKED, WAITINGTOENTER, WAITINGTOEXIT};
	private CarStatus carStatus;
	private String carID;
	
	
	
	public Car(String carID, ArrayList<Entry> entries, ArrayList<Exit> exits){
		this.carStatus = CarStatus.ENTERING;
		this.carID = carID; 
		this.entries = entries;
		this.entry = entries.get(selectEntry(entries.size()));
		this.exits = exits;
		this.exit = exits.get(selectExit(exits.size()));
	}
	
	public CarStatus getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}
	
	
	
	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public int selectEntry(int numOfEntries){
		
		int index;
		Random randomizer = new Random();
		index = randomizer.nextInt(numOfEntries);
		
		
		return index;
	}
	
	public Exit getExit() {
		return exit;
	}

	public void setExit(Exit exit) {
		this.exit = exit;
	}
	
	public int selectExit(int numOfExits){
		int index;
		Random randomizer = new Random();
		index = randomizer.nextInt(numOfExits);
		
		
		return index;
		
	}

	public void notifyEntry(){
		entry.checkLotCapacity();
	}
	
	public void parkCar(){
		entry.notifyLotOfParkedCar();
	}
	
	public void unparkCar(){
		exit.notifyLotOfExitingCar();
	}
	
	public void run(){
		try{
            System.out.println(carID + " is entering through " + entry.getEntryID());
			
            notifyEntry();           
            while(entry.isLocked()){
            	notifyEntry();
            }
            
            System.out.println(carID + " has passed through");
            parkCar();
            System.out.println(carID + " has parked");
            
			Thread.sleep(100);

			System.out.println(carID + " is exiting");
			unparkCar();
			
		}catch(InterruptedException ie){
	        System.out.println(carID +" interrupted.");
		}
		

	}
	
	public void start(){
	     if (t == null)
	      {
	         t = new Thread(this, "car");
	         t.start();
	      }
	}
	
		
	
	
}
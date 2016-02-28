
public class Room {
	
	private boolean isMale;
	private String name;
	private static int ID = 1;
	private int freePlace;
	
	public Room(boolean isMale) {
		this.isMale = isMale;
		this.name = "room"+this.ID++;
		this.freePlace = 3;
	}
	
	public boolean addPatient(){
		if(this.freePlace > 0){
			this.freePlace-=1;
			return true;
		}
		else return false;
	}
	
	public boolean removePatient(){
		if(this.freePlace < 3){
			this.freePlace +=1;
			return true;
		}else
			return false;
	}
	
	public boolean isMale(){
		return this.isMale;
	}
	
	public String getName(){
		return this.name;
	}
	
}

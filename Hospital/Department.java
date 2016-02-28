import java.util.ArrayList;
import java.util.Random;



public class Department {
	
	private static final int NUMBER_OF_ROOMS = 10;
	private String name;
	private ArrayList<Room> rooms;
	private ArrayList<Doctor.MedicalRecord> records;
	private int freePlace;
	
	Random r = new Random();
	public Department(String name) {
		this.name = name;
		this.rooms = new ArrayList<Room>(10);
		this.records = new ArrayList<Doctor.MedicalRecord>();
		this.freePlace = 30;
		for(int i=0; i<this.NUMBER_OF_ROOMS; i++){
			this.rooms.add(new Room(r.nextBoolean()));
		}
	}
	
	public void addPatient(Doctor.MedicalRecord record){
		for(Room r : this.rooms){
			if(record.getPatient().isMale() == r.isMale()){
				if(r.addPatient()){
					this.records.add(record);
					record.setRoomName(r.getName());
					this.freePlace -= 1;
					return;
				}
			}
		}
	}
	
	public void removePatient(Doctor.MedicalRecord record){
		for(Room r : this.rooms){
			if(record.getPatient().isMale() == r.isMale()){
				if(r.removePatient()){
					this.records.remove(record);
					this.freePlace+=1;
				}
			}
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Doctor.MedicalRecord> getPatients(){ 
		return this.records;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}

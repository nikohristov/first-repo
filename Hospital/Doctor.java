import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Doctor extends Person implements Runnable{
	

	public class MedicalRecord {
		
		private Doctor doctor;
		private Patient patient;
		private int daysLeft;
		private HashMap<String,ArrayList<String>> diagnose;
		private String roomName;
		private String department;
		
		public MedicalRecord(Doctor doctor,Patient patient,int daysLeft) {
			this.doctor = doctor;
			this.daysLeft = daysLeft;
			this.patient = patient;
			this.diagnose = new HashMap<String,ArrayList<String>>();
			this.roomName = "unknown";
			this.department = null;
		}

		public Doctor getDoctor() {
			return doctor;
		}

		public Patient getPatient() {
			return patient;
		}

		public int getDaysLeft() {
			return daysLeft;
		}
		
		public void setRoomName(String room){
			this.roomName = room;
		}
		
		public void setDepartment(String dep){
			this.department = dep;
		}

		public String getRoomName() {
			return roomName;
		}

		public String getDepartment() {
			return department;
		}

		public HashMap<String, ArrayList<String>> getDiagnose() {
			return diagnose;
		}
		
		public boolean decreaseDays(){
			this.daysLeft -= 1;
			if(this.daysLeft > 0)
				return false;
			return true;
		}
		
	}
	
	private static String[] diagnoses = {"ill","very ill","reanimation"};
	private static String[] medicines = {"injection","syrup","tablets"};
	
	private String specialisation;
	private boolean isFree;
	private ArrayList<MedicalRecord> ownRecords;
	Random r = new Random();
	
	public Doctor(String name, String telNumber,String specialisation) {
		super(name, telNumber);
		this.isFree = true;
		this.specialisation = specialisation;
		this.ownRecords = new ArrayList<MedicalRecord>();
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public boolean isFree() {
		return isFree;
	}
	
	public MedicalRecord giveDiagnose(Patient p){
		
		MedicalRecord rec = new MedicalRecord(this, p, r.nextInt(3)+3);
		String diag = this.diagnoses[r.nextInt(this.diagnoses.length)];
		rec.diagnose.put(diag, new ArrayList<String>());
		int n = r.nextInt(this.medicines.length-1);
		rec.diagnose.get(diag).add(this.medicines[2]);
		rec.diagnose.get(diag).add(this.medicines[n]);
		this.ownRecords.add(rec);
		return rec;
		
	}
	
	public synchronized void giveVisition(){
		while(true){
			this.isFree = false;
			for(MedicalRecord record : this.ownRecords){
				System.out.println("Doctor "+this.getName() +" visited "+record.getPatient().getName()+ " in room: "+record.roomName+" from department - "+record.department);
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.isFree = true;
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
			this.giveVisition();
	}
	
	
	
	
}

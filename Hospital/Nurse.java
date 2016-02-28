import java.util.ArrayList;
import java.util.HashSet;

public class Nurse extends Person implements Runnable{

	private int ageOfWork;
	private HashSet<String> departments;
	private Hospital hospital;
	
	public Nurse(String name, String telNumber,int ageOfWork) {
		super(name, telNumber);
		this.ageOfWork = ageOfWork;
		this.departments = new HashSet<String>();
	}
	
	public void addDepartment(String dep){
		this.departments.add(dep);
	}
	
	public  synchronized void giveMedicines(){
		while(true){
			for(Department p : hospital.getPatients()){
				for(String nameDepart : this.departments){
					if(p.equals(nameDepart)){
						for(Doctor.MedicalRecord record : p.getPatients()){
							System.out.println("Nurse "+this.getName()+" give to patient "+record.getPatient().getName() +" in room "+record.getRoomName()+" from department "+record.getDepartment()+" medicine "+record.getDiagnose().get(record.getDepartment()));
							if(record.decreaseDays()){
								p.removePatient(record);
							}
						}
					}
				}
			}
			
			try {
				wait();
			} catch (InterruptedException e) {
				}
			}
	}
	public void setHospital(Hospital s){
		this.hospital = s;
	}

	@Override
	public void run() {
		this.giveMedicines();
	}
	
	
}

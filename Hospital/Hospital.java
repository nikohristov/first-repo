import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Hospital {
	
	private static String[] departments = {"Ortopedia","Kardioologia","Virusologiq"};
		
	private ArrayList<Nurse> nurses;
	private ArrayList<Doctor> doctors;
	private HashSet<Department> patients;
	 
	Random r = new Random();
	public Hospital() {
		this.nurses = new ArrayList<Nurse>();
		this.doctors = new ArrayList<Doctor>();
		this.patients = new HashSet<Department>();
		for(int i=0; i<this.departments.length; i++){
			this.patients.add(new Department(this.departments[i]));
		}
	}
	
	public void addNurse(Nurse c){
		int n = r.nextInt(2)+1;
		for(int i=0; i<n; i++){
			c.addDepartment(this.departments[r.nextInt(this.departments.length)]);
		}
		this.nurses.add(c);
		c.setHospital(this);
	}
	
	public void addDoctor(Doctor d){
		this.doctors.add(d);
	}
	
	public void addPatient(Patient p){
		while(true){
			for(Doctor doctor : this.doctors){
				if(doctor.isFree()){
					String dep = this.departments[r.nextInt(this.departments.length)];
					System.out.println(dep);
					for(Department department : this.patients){
						if(department.getName().equals(dep)){
							Doctor.MedicalRecord record = doctor.giveDiagnose(p);
							department.addPatient(record);
							record.setDepartment(dep);
							String gender = (p.isMale()) ? "male" : "female";
							System.out.println("Patient "+p.getName()+" gender: "+ gender + " with diagnose: "+record.getDiagnose().keySet()+" doctor: "+record.getDoctor().getName());
							return;
						}
					}
				}
			}
		}
	}
	
	public HashSet<Department> getPatients(){
		return this.patients;
	}
	
	
	
}

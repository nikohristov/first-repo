
public class DemoHospital {

	public static void main(String[] args) {
		
		Hospital hospital = new Hospital();
		Doctor one = new Doctor("Nikolay Ivanov", "000", "Hirurg");
		Doctor two = new Doctor("Ivan Hristov", "000", "Lichen");
		Doctor tree = new Doctor("Hristo Traikov", "000", "Ortoped");
		
		Nurse nurse1 = new Nurse("Hristiana Stoeva", "no-mobile", 12);
		Nurse nurse2 = new Nurse("Radka Nikolova", "no-mobile", 7);
		Nurse nurse3 = new Nurse("Katq Stanislavova", "no-mobile", 15);
		
		hospital.addDoctor(one);
		hospital.addDoctor(two);
		hospital.addDoctor(tree);
		
		hospital.addNurse(nurse1);
		hospital.addNurse(nurse2);
		hospital.addNurse(nurse3);
		
		Patient pat = new Patient("Stefko", "no number", 22, true);
		Patient pat2 = new Patient("Hristo", "no number", 25, true);
		Patient pat3 = new Patient("Mitko", "no number", 27, false);
		Patient pat4 = new Patient("Ancho", "no number", 31, false);
		Patient pat5 = new Patient("Boxi", "no number", 33, true);
		
		Thread a = new Thread(one);
		a.start();
		Thread a2 = new Thread(two);
		a2.start();
		Thread a3 = new Thread(tree);
		a3.start();
		Thread a4 =new Thread(nurse1);
		a4.start();
		Thread a5 = new Thread(nurse2);
		a5.start();
		Thread a6 = new Thread(nurse3);
		a6.start();
		long time = System.currentTimeMillis();
		hospital.addPatient(pat2);
		hospital.addPatient(pat3);
		hospital.addPatient(pat4);
		hospital.addPatient(pat5);
		for(int i=0; i<10; i++){
			while(System.currentTimeMillis() - time <  5000){
				//System.out.println(System.currentTimeMillis() - time);
			}
			a.interrupt();
			a2.interrupt();
			a3.interrupt();
			a4.interrupt();
			a5.interrupt();
			a6.interrupt();
			
		}
		
	}

}

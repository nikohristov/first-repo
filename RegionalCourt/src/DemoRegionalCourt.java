import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class DemoRegionalCourt {
	private static File file = new File("court1.txt");
	public static PrintStream fileWriter;
	public static final String[] NAMES = { "Nikolay","Ekaterina",
			"Ivan","Pencho","Hrisi","Simona",
			"Jordan","Krasimir","Roki","Joro","Mitko","Sliven","Montre",
			"Kiril","Martin","Stefan","Liubka","Bobi","Rikardo"};
	public static final String[] FAMILIES  = {"Hristov","Daskalov","Stoineva","Georgieva",
			"Djambazov","Borisov","Mitrev","Nikolov"};

	public static void main(String[] args) throws FileNotFoundException {
		
		DemoRegionalCourt.fileWriter = new PrintStream(DemoRegionalCourt.file);
		
		Random r = new Random();
		
		RegionalCourt court = new RegionalCourt("Regional Court - Veliko Turnovo", "Veliko Turnovo, Shipchenska N-17");
		for(int i=0; i<3; i++){
			String name = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
			court.addLegalEntity(new Judge(name, r.nextInt(10), r.nextInt(10)));
		}
		
		for(int i=0; i<15; i++){
			String name = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
			court.addLegalEntity(new Juror(name, r.nextInt(5), r.nextInt(5)));
		}
		
		for(int i=0; i<7; i++){
			String name = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
			court.addLegalEntity(new Lawyer(name, r.nextInt(12), r.nextInt(12)));
		}
		
		ArrayList Judges = new ArrayList<>(court.getLegalEntities());
		
		
		for(int i=0; i<3; i++){
			Collections.shuffle(Judges);
			for(Iterator it = Judges.iterator(); it.hasNext();){
				LegalEntity j = (LegalEntity) it.next();
					if(j instanceof Judge){
						String nameDef = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
						String nameAcc = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
						court.addCase(new CivilCase((Judge) j, "CIVIL CASE", new Defendant(nameDef, "Sofia,Levski", 23), new Accuser(nameAcc, "Sofia,Levski", 33)));
					}
						
			}
		}
		
		for(int i=0; i<3; i++){
			for(Iterator it = court.getLegalEntities().iterator(); it.hasNext();){
				if(it.next() instanceof Judge){
					for(Iterator it2 = court.getLegalEntities().iterator(); it2.hasNext();){
						if(it2.next() instanceof Prosecutor){
							String nameDef = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
							String nameAcc = DemoRegionalCourt.NAMES[r.nextInt(NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(FAMILIES.length)];
							court.addCase(new CriminalCase((Judge) it.next(), "CRIMINAL CASE", new Defendant(nameDef, "Sofia,Levski", 25), (Prosecutor)it2.next()));
							break;
							}
						}
					}	
				}
		}
		
		court.startsAllCases();
			
			
			
		
		
	}
		
}

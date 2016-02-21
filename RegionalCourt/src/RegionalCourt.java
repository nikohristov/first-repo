import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class RegionalCourt {
	
	Random r = new Random();
	
	private String name;
	private String address;
	private HashSet<LegalEntity> legalEntities;
	private ArrayList<Case> cases;
	
	public RegionalCourt(String name,String address) {
		this.name = name;
		this.address = address;
		this.legalEntities = new HashSet<>();
		this.cases = new ArrayList<>();
	}
	
	protected void addLegalEntity(LegalEntity e){
		this.legalEntities.add(e);
	}
	
	protected void addCase(Case c){
		
		for(Iterator it = this.legalEntities.iterator(); it.hasNext();){
			LegalEntity hs = (LegalEntity) it.next();
			if(hs instanceof Lawyer){
				if(c.getDefendent().getLawyers().size() <= 2){
						c.getDefendent().addLawyer((Lawyer)hs);
						continue;
				}else{
					if(c instanceof CivilCase){
						if(((CivilCase)c).getAccuser().getLawyers().size() < 2){
							((CivilCase)c).getAccuser().addLawyer((Lawyer) hs);
							continue;
						}
					}else{
						continue;
					}
				}
				continue;
			}else if(hs instanceof Juror){
				if(c instanceof CivilCase){
					if(c.getJurors().size() < 11){
						c.addJuror((Juror) hs);
					}
				}else{
					if(c.getJurors().size() < 3){
						c.addJuror((Juror)hs);
					}
				}
			}
		}
		for(int i = 0; i<7; i++){
			String name = DemoRegionalCourt.NAMES[r.nextInt(DemoRegionalCourt.NAMES.length)]+" "+DemoRegionalCourt.FAMILIES[r.nextInt(DemoRegionalCourt.FAMILIES.length)];
			c.addWitness(new Witness(name, "Sofia", (r.nextInt(40)+18)));
		}
		
		this.cases.add(c);
	}

	public HashSet<LegalEntity> getLegalEntities() {
		return legalEntities;
	}
	
	private void infoEntities(){
		TreeSet entities = new TreeSet<>(this.legalEntities);
		for(Iterator iterator = entities.iterator(); iterator.hasNext();){
			LegalEntity entity = (LegalEntity) iterator.next();
			DemoRegionalCourt.fileWriter.println(entity.getName() +" - "+entity.getNumberOfCase());
		}
	}
	
	protected void startsAllCases(){
		this.infoEntities();
		for(Iterator it = this.cases.iterator(); it.hasNext();){ 
			DemoRegionalCourt.fileWriter.println();
			Case c = (Case) it.next();
			c.startCase();
			DemoRegionalCourt.fileWriter.println();
			DemoRegionalCourt.fileWriter.println();
		}
		this.infoEntities();
	}
	
}

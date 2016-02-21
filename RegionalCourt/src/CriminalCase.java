import java.util.Iterator;
import java.util.Random;

public class CriminalCase extends Case{
	
	private Prosecutor prosecutor;

	public CriminalCase(Judge judge, String type, Defendant def, Prosecutor p) {
		super(judge, type, 10, def);
		this.prosecutor = p;
	}

	@Override
	protected void startCase() {
	
		this.getJudge().printInfoForEntity();
		
		this.getJudge().wasInCase();
		for(Iterator<Juror> it = this.getJurors().iterator(); it.hasNext();){
			Juror j = it.next();
			j.printInfoForEntity();
			j.wasInCase();
		}
		
		this.prosecutor.wasInCase();
		for(int i=0; i<5; i++){
			this.prosecutor.askQuestion(this.getDefendent());
		}
		
		for(Iterator<Witness> it = this.getWitnesess().iterator(); it.hasNext();){
			Witness wit = it.next();
			for(int i=0; i<5; i++){
				this.prosecutor.askQuestion(wit);
			}
		}
		
		for(Iterator<Lawyer> it = this.getDefendent().getLawyers().iterator(); it.hasNext();){
			Lawyer defLaws = it.next();
			defLaws.printInfoForEntity();
			defLaws.wasInCase();
			for(Iterator<Witness> iterateWitness = this.getWitnesess().iterator(); it.hasNext();){
				Witness wit = iterateWitness.next();
				for(int i=0; i<5; i++){
					defLaws.askQuestion(wit);
				}
			}
		}
		
		int positiveVote = 0;
		boolean isGuilty = false;
		
		Random r = new Random();
		for(Iterator it = this.getJurors().iterator(); it.hasNext();){
			Juror j = (Juror) it.next();
			double vote = r.nextDouble();
			if(vote > 0.5){
				DemoRegionalCourt.fileWriter.println(j.getName() +" voted with guilty !");
				positiveVote++;
			}else{
				DemoRegionalCourt.fileWriter.println(j.getName() + " voted with no guilty !");
			}
		}
		
		if(positiveVote > this.getJurors().size()/2);
			isGuilty = true;
			
		if(isGuilty){
			DemoRegionalCourt.fileWriter.println("\t GUILTY !!!");
			DemoRegionalCourt.fileWriter.println(this.getJudge().getName() +" convict "+ (r.nextInt(38)+3)+ " years of prison !!!");
		}else{
			DemoRegionalCourt.fileWriter.println("\t NO GUILTY !!!");
		}
	}
	
}

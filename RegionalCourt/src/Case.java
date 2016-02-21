import java.util.HashSet;

public abstract class Case {
	
	private Judge judge;
	private String type;
	private HashSet<Juror> jurors;
	private Defendant defendent;
	private HashSet<Witness> witnesess;
	
	public Case(Judge judge, String type, int numOfJuros, Defendant def) {
		this.judge = judge;
		this.type = type;
		this.defendent = def;
		this.jurors = new HashSet<Juror>(numOfJuros);
		this.witnesess = new HashSet<Witness>();
	}
	
	
	
	public Judge getJudge() {
		return judge;
	}



	public String getType() {
		return type;
	}



	public HashSet<Juror> getJurors() {
		return jurors;
	}



	public Defendant getDefendent() {
		return defendent;
	}



	public HashSet<Witness> getWitnesess() {
		return witnesess;
	}



	abstract protected void startCase();
	
	protected void addJuror(Juror j){
		this.jurors.add(j);
	}
	
	protected void addWitness(Witness w){
		this.witnesess.add(w);
	}
	
}

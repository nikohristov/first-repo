import java.util.ArrayList;
import java.util.HashMap;

public abstract class LegalEntity implements Comparable<LegalEntity>{
	
	private String name;
	private int ageOfWork;
	private int numberOfCase;
	private HashMap<Case,ArrayList<String>> answersForCases;
	private String position;
	
	public LegalEntity(String name, int ageOfWork, int numberOfCase,String position) {
		
		this.name = name;
		this.ageOfWork = ageOfWork;
		this.numberOfCase = numberOfCase;
		this.position = position;
		this.answersForCases = new HashMap<Case,ArrayList<String>>();
	}

	public String getName() {
		return name;
	}

	public int getAgeOfWork() {
		return ageOfWork;
	}

	public int getNumberOfCase() {
		return numberOfCase;
	}
	
	public HashMap<Case, ArrayList<String>> getAnswersForCases() {
		return answersForCases;
	}
	
	public String getPosition() {
		return position;
	}

	protected void askQuestion(Citizen z){
		DemoRegionalCourt.fileWriter.println(this.getPosition() +": " + this.getName()+ " asks question to "+z.getName());
	}
	
	protected void writeAnswer(Case c,String answer){
		if(this.getAnswersForCases().get(c) == null){
			this.getAnswersForCases().put(c, new ArrayList<String>());
		}
		this.getAnswersForCases().get(c).add(answer);
	}
	
	protected void printInfoForEntity(){
		DemoRegionalCourt.fileWriter.println(this.getName() +"\t" +this.getPosition());
		DemoRegionalCourt.fileWriter.println("Age of work : "+this.getAgeOfWork()  );
		DemoRegionalCourt.fileWriter.println("Number of cases : "+this.getNumberOfCase());
	}
	
	protected void wasInCase(){
		this.numberOfCase += 1;
	}
	
	@Override
	public int compareTo(LegalEntity o) {
		if(this.name.compareTo(o.name) == 0){
			return this.position.compareTo(o.position);
		}else
			return name.compareTo(o.name);
	}
	
}

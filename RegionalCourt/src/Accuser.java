import java.util.HashSet;

public class Accuser extends Citizen{
	
	private HashSet<Lawyer> lawyers;

	public Accuser(String name, String address, int age) {
		super(name, address, age);
		this.lawyers = new HashSet<Lawyer>();
	}

	public HashSet getLawyers(){
		return this.lawyers;
	}

	protected void addLawyer(Lawyer l){
		this.lawyers.add(l);
	}
}

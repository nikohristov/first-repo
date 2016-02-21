
public class LargeProvider extends Provider{

	public LargeProvider(String name, String address, String workingTime) {
		super(name, address, workingTime, 15);
	}

	@Override
	protected double makeDiscount(int amount) {
		double disc = (amount*this.getPercentDiscount())/100;
		return disc;
	}

}

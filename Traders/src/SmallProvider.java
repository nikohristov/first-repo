
public class SmallProvider extends Provider{

	public SmallProvider(String name, String address, String workingTime) {
		super(name, address, workingTime, 0);
	}

	@Override
	protected double makeDiscount(int amount) {
		return 0;
	}

}

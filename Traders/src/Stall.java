
public class Stall extends TradeCenter{

	public Stall(String address, String workingTime, int area) {
		super(address, workingTime, 50, setArea(area));
	}
	
	private static int setArea(int area){
		if(area < 2 || area > 10){
			throw new IllegalArgumentException("Invalid area for Stall !");
		}
		return area;
	}
	
}

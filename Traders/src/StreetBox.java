
public class StreetBox extends TradeCenter{

	public StreetBox(String address, String workingTime,int area) {
		super(address, workingTime, 50, setArea(area));
	}
		
	private static int setArea(int area){
		if(area < 4 || area > 6){
			throw new IllegalArgumentException("Invalid area for StreetBox !");
		}
		return area;
	}
	
}


public class ShopInMall extends TradeCenter{

	public ShopInMall(String address, String workingTime,int area) {
		super(address, workingTime, 150, setArea(area));
	}
	
	private static int setArea(int area){
		if(area < 10 || area > 100){
			throw new IllegalArgumentException("Invalid area for Shop in Mall !");
		}
		return area;
	}
	
}

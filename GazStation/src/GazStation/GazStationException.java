package GazStation;

public class GazStationException extends Exception{
	
	public GazStationException() {
	}
	
	public GazStationException(String massage) {
		super(massage);
	}
	
	public GazStationException(String massage,Throwable cause) {
		super(massage,cause);
	}
	
	public GazStationException(Throwable cause) {
		super(cause);
	}
	
}

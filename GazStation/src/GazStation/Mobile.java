package GazStation;

public abstract class Mobile {
	
	private String model;
	private int ageOfProd;
	private boolean haveVintage;
	private Vignette vignette;
	
	public Mobile(String model,int ageOfProd){
		this.model = model;
		this.ageOfProd = ageOfProd;
		this.haveVintage = false;
		this.vignette = null;
	}

	public String getModel() {
		return model;
	}

	public int getAgeOfProd() {
		return ageOfProd;
	}
	
	public void setVintage(Vignette vignette){
		this.vignette = vignette;
		this.haveVintage = true;
		DemoStation.writer.println("the vingate was set of: " + this.vignette.setOnMobile()+" seconds.");
	}
	
	public Vignette getVignette(){
		return this.vignette;
	}
	
	public boolean haveVintage(){
		return this.haveVintage;
	}
	
	@Override
	public String toString() {
		if(this.haveVintage){
			return this.model + " " + this.ageOfProd + " " + this.getVignette().getValidate().toString();
		}else
			return this.model + " " + this.ageOfProd + " " + "no vignette";
	}
}

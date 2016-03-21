package model;

public class Film implements Comparable<Film>{
	
	private String title;
	private String date;
	private String genre;
	private String director;
	private String runtime;
	private double rating;
	private boolean isWatched;
	public boolean isFavourite;
	private boolean isUpload;
	private String comment;
	private String poster;
	

	public Film(String title, String date, String genre, String director,String runtime, double rating, boolean isWatched, boolean isFavourite, boolean isUpload){
			
		this.title = title;
		this.date = date;
		this.genre = genre;
		this.director = director;
		this.runtime = runtime;
		this.rating = rating;
		this.isWatched = isWatched;
		this.isFavourite = isFavourite;
		this.isUpload = isUpload;
		this.comment = " ";
	}

	
	public Film(String title,String date,String genre,String director){
		this.title = title;
		this.date = date;
		this.genre = genre;
		this.director = director;
		this.isFavourite = false;
		this.isWatched = false;
		this.isUpload = false;
		this.comment = " ";
	}
	
	public String getTitle() {
		return title;
	}




	public String getDate() {
		return date;
	}




	public String getGenre() {
		return genre;
	}




	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}


	public String getDirector() {
		return director;
	}




	public String getRuntime() {
		return runtime;
	}




	public double getRating() {
		return rating;
	}




	public boolean isWatched() {
		return isWatched;
	}




	public boolean getisFavourite() {
		return isFavourite;
	}

	public boolean isUpload(){
		return this.isUpload;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment){
		this.comment = comment;
	}
	
	public void setIsFavourite(boolean isFavourite){
		this.isFavourite = isFavourite;
	}
	
	public void setIsWatched(boolean isWatched){
		this.isWatched = isWatched;
	}
	
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}


	public String getPoster() {
		return poster;
	}


	@Override
	public int compareTo(Film arg0) {
		if(this.title.equals(arg0.title)){
			return this.date.compareTo(arg0.date);
		}
		return this.title.compareTo(arg0.title);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	
	
}

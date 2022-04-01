package vttp2022.ssf.assessment.videosearch.models;

// DO NOT MODIFY THIS CLASS

public class Game {

	public static Object gameList;
	private String name;
	private String released;
	private String backgroundImage;
	private Float rating;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setReleased(String released) { this.released = released; }
	public String getReleased() { return this.released; }
	
	public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }
	public String getBackgroundImage() { return this.backgroundImage; }

	public void setRating(Float rating) { this.rating = rating; }
	public Float getRating() { return this.rating; }

	@Override
	public String toString() {
		return "name: %s, rating: %f, backgroundImage: %s"
				.formatted(name, rating, backgroundImage);
	}
}

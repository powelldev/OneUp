package com.teambad.oneup;

public class Challenge {
	int userid; 
	int numRatings; 
	int challengeId;
	double lat, lng, rating;
	String url;
	
	public Challenge() {
		
	}
	public Challenge(int userid, int numRatings, int challengeId, double lat, double lng, double rating, String url) 
	{	
		this.userid = userid;
		this.numRatings = numRatings;
		this.challengeId = challengeId;
		this.lat = lat;
		this.lng = lng;
		this.rating = rating;
		this.url = url;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	public int getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
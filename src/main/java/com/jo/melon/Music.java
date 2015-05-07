package com.jo.melon;

public class Music {

	private int rank;
	private String title;
	private String artist;
	private String album;
	private String imageurl;

	public Music() {
	}

	public Music(int rank, String title, String artist, String album, String imageurl) {
		super();
		this.rank = rank;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.imageurl = imageurl;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "Music [rank=" + rank + ", title=" + title + ", artist=" + artist + ", album=" + album + ", imageurl=" + imageurl + "]";
	}

}

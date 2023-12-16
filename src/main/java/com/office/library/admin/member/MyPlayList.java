package com.office.library.admin.member;

public class MyPlayList {
	private int playlistId;
	private int songId;
	String singer;
	String title;
	String genre;
	
	public int getplaylistId() {
        return playlistId;
    }

    public void setplaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getsongId() {
        return songId;
    }

    public void setsongId(int songId) {
        this.songId = songId;
    }
    
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

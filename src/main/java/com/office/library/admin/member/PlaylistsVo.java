package com.office.library.admin.member;

public class PlaylistsVo {
	 private int playlistId;
	 private int userId;
	 private String playlistName;
	 
	 public int getPlaylistId() {
	        return playlistId;
	    }

	 public void setPlaylistId(int playlistId) {
	     this.playlistId = playlistId;
	  }

	 public String getPlaylistName() {
	     return playlistName;
	 }

	 public void setPlaylistName(String playlistName) {
	     this.playlistName = playlistName;
	 }

	 public int getUserId() {
	        return userId;
	    }

	 public void setUserId(int userId) {
	     this.userId = userId;
	  }
}

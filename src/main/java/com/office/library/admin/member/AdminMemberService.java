package com.office.library.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminMemberService {
	
	@Autowired
	AdminMemberDao adminMemberDao;
	
	
	public AdminMemberVo loginConfirm(String username) {
		
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(username);
		
		
		return loginedAdminMemberVo;
	}
	
	public Boolean createAccountConfirm(String username) {
		
		Boolean createAdminMemberVo = adminMemberDao.isUsernameAvailable(username);
		
		if (createAdminMemberVo == true) {
			Boolean check = adminMemberDao.registerUser(username);
			
			if (check == true) {
				return true;
			}
			else return false;
		}
		
		return createAdminMemberVo;
		
	}
	
	public List<SongVo> listipSong(){
		return adminMemberDao.selectAdmin();
	}
	
	public List<SongVo> listupSong(String keyword){
		return adminMemberDao.selectAdmins(keyword);
	}
	
	public List<SongVo> nameSort(List<SongVo> songVos){
		return adminMemberDao.nameSort(songVos);
	}
	
	public List<SongVo> titleSort(List<SongVo> songVos){
		return adminMemberDao.titleSort(songVos);
	}
	
	public List<SongVo> genreSort(List<SongVo> songVos){
		return adminMemberDao.genreSort(songVos);
	}
	
	public List<SongVo> genreAdd(List<SongVo> songVos, int songId, String genreInput){
		return adminMemberDao.genreAdd(songVos,songId,genreInput);
	}
	
	public void createNewPlaylist(int userId,String playlistName) {
		adminMemberDao.createNewPlaylist(userId,playlistName);
	}

	public List<PlaylistsVo> getPlaylistsByUserId(int userId) {
	    return adminMemberDao.getPlaylistsByUserId(userId);
	}
	
	public void playlistAdd(int songId, int playlistId) {
		adminMemberDao.playlistAdd(songId, playlistId);
	}
	
	public List<MyPlayList> playlistLoad(int playlistId){
		return adminMemberDao.playlistLoad(playlistId);
	}
	
	public void removeList(int songId, int playlistId) {
		adminMemberDao.removeList(songId,playlistId);
	}
		
}

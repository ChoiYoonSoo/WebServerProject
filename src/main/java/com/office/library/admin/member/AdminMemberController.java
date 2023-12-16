package com.office.library.admin.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	//@Autowired
    //private HttpSession httpSession; // HttpSession
	
	int num;
	int user;
	List<SongVo> songVos = new ArrayList<SongVo>();
	List<MyPlayList> playlist = new ArrayList<MyPlayList>();
	
	// 회원가입
	@RequestMapping(value = "/createAccountForm",method = RequestMethod.GET)
	public String createAccountForm() {
		String nextPage = "admin/member/create_account_form";
		
		return nextPage;
	}
	
	// 회원가입 확인
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(@RequestParam("username")String username) {
		
		String nextPage = "admin/member/login_form";
		
		Boolean result = adminMemberService.createAccountConfirm(username);
		
		if (result == false)
			nextPage = "admin/member/create_account_ng";
		
		return nextPage;
	}
	
	// 로그인
	@GetMapping("/loginForm")
	public String loginForm() {
		
		String nextPage = "admin/member/login_form";
		
		return nextPage;
	}
	
	// 로그인 확인
	@PostMapping("/loginConfirm")
	public String loginConfirm(@RequestParam("username")String username) {
		
		String nextPage = "admin/member/login_ok";
		
		AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(username);
		
		if (loginedAdminMemberVo == null) {
			nextPage = "admin/member/login_ng";
		}
		else {
			user = loginedAdminMemberVo.get_userId();
			 //httpSession.setAttribute("userId", loginedAdminMemberVo.get_userId());
		}
		
		
		return nextPage;
	}
	
	// 메인 화면 이동
	@GetMapping("/mainload")
	public String mainload(Model model) {
		//int user = (int) httpSession.getAttribute("userId");

	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists", playlists);
		
		String nextPage = "admin/home";
		
		return nextPage;
	}
	
	// 검색 화면 이동
	@GetMapping("/searchload")
	public String searchload() {
		String nextPage = "admin/member/search";
		
		return nextPage;
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public String listupSong(Model model) {
		String nextPage = "admin/member/search";
		
		songVos = adminMemberService.listipSong();
		
		//int user = (int) httpSession.getAttribute("userId");

	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);

	    model.addAttribute("playlists",playlists);
		
		model.addAttribute("songVos",songVos);
		
		return nextPage;
	}
	
	// 노래 검색
	@GetMapping("/searchSongs")
    public String searchSongs(@RequestParam("keyword") String keyword, Model model) {
        songVos = adminMemberService.listupSong(keyword);
        //int user = (int) httpSession.getAttribute("userId");
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists",playlists);
        model.addAttribute("songVos", songVos);
        return "admin/member/search";
    }
	
	// 이름 순 정렬
	@GetMapping("/nameSort")
	public String nameSort(Model model) {
		songVos = adminMemberService.nameSort(songVos);
		//int user = (int) httpSession.getAttribute("userId");
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists",playlists);
		model.addAttribute("songVos",songVos);
		return "admin/member/search";
	}
	
	// 제목 순 정렬
	@GetMapping("/titleSort")
	public String titleSort(Model model) {
		songVos = adminMemberService.titleSort(songVos);
		//int user = (int) httpSession.getAttribute("userId");
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists",playlists);
		model.addAttribute("songVos",songVos);
		return "admin/member/search";
	}
	
	// 장르 순 정렬
	@GetMapping("/genreSort")
	public String genreSort(Model model) {
		songVos = adminMemberService.genreSort(songVos);
		//int user = (int) httpSession.getAttribute("userId");
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists",playlists);
		model.addAttribute("songVos",songVos);
		return "admin/member/search";
	}
	
	// 장르 추가
	@GetMapping("/genreAdd")
    public String genreAdd(@RequestParam("songId") int songId, @RequestParam("genreInput") String genreInput, Model model) {
        songVos = adminMemberService.genreAdd(songVos,songId,genreInput);
        //int user = (int) httpSession.getAttribute("userId");
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists",playlists);
        model.addAttribute("songVos",songVos);
        return "admin/member/search";
    }
	
	// 새로운 플레이리스트 생성
	@GetMapping("/createNewPlaylist")
	public String createNewPlaylist(@RequestParam("playlistName") String playlistName,Model model) {
		//int user = (int) httpSession.getAttribute("userId");
		adminMemberService.createNewPlaylist(user,playlistName);
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    model.addAttribute("playlists",playlists);
		model.addAttribute("songVos",songVos);
		return "admin/member/search";
	}
	
	// 플레이리스트 제목 출력
	@GetMapping("/playlistPrint")
	public String playlistPrint(Model model) {
		//int user = (int) httpSession.getAttribute("userId");

	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);

	    model.addAttribute("playlists", playlists);

	    return "admin/member/playlistPrint";
	}
	
	// 플레이리스트 노래 추가
	@GetMapping("/playlistAdd")
	public String playlistAdd(@RequestParam("songId") int songId, @RequestParam("playlistId") int playlistId, Model model) {
		//int user = (int) httpSession.getAttribute("userId");
	    List<PlaylistsVo> playlists = adminMemberService.getPlaylistsByUserId(user);
	    
	    adminMemberService.playlistAdd(songId, playlistId);
	    
	    model.addAttribute("playlists",playlists);
		model.addAttribute("songVos",songVos);
		
		return "admin/member/search";
	}
	
	// 플레이리스트 노래 출력
	@GetMapping("/playlistLoad")
	public String playlistLoad(@RequestParam("playlistId") int playlistId, @RequestParam("playlistName") String playlistName, Model model) {
		num = playlistId;
		playlist = adminMemberService.playlistLoad(playlistId);
		model.addAttribute("myplaylist",playlist);
		model.addAttribute("playlistName",playlistName);
		return "admin/member/playlist";
	}
	
	// 노래 삭제
	@GetMapping("/removeList")
	public String removeList(@RequestParam("songId")int songId, @RequestParam("playlistId")int playlistId, Model model) {
		
		adminMemberService.removeList(songId,playlistId);
		
		playlist = adminMemberService.playlistLoad(num);
		model.addAttribute("myplaylist",playlist);
		
		return "admin/member/playlist";
	}
	
	
}

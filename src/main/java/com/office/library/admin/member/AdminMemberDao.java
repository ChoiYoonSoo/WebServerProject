package com.office.library.admin.member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMemberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminMemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AdminMemberVo selectAdmin(String username) {
        String sql = "SELECT id, username FROM USER WHERE username = ?";
        
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, username);
            if (!rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);
                AdminMemberVo adminMemberVo = new AdminMemberVo();
                adminMemberVo.set_userId((Integer) row.get("id"));
                adminMemberVo.set_username((String) row.get("username"));
                System.out.println(adminMemberVo.get_userId());
                return adminMemberVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }


    // username 조회
    private List<PlaylistsVo> getPlaylistsByUsername(String username) {
        String sql = "SELECT p.playlist_id, p.playlist_name, s.id, s.singer, s.title, s.genre " +
                "FROM playlists p " +
                "LEFT JOIN playlists_songs ps ON p.playlist_id = ps.playlist_id " +
                "LEFT JOIN song_table s ON ps.song_id = s.id " +
                "WHERE p.user_id = (SELECT id FROM USER WHERE username = ?)";
        try {
            return jdbcTemplate.query(sql, new PlaylistsRowMapper(), username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class AdminMemberRowMapper implements RowMapper<AdminMemberVo> {
        @Override
        public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminMemberVo adminMemberVo = new AdminMemberVo();
            adminMemberVo.set_username(rs.getString("username"));
            adminMemberVo.set_userId(rs.getInt("id"));
            return adminMemberVo;
        }
    }

    private static class PlaylistsRowMapper implements RowMapper<PlaylistsVo> {
        @Override
        public PlaylistsVo mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaylistsVo playlistsVo = new PlaylistsVo();
            playlistsVo.setPlaylistId(rs.getInt("playlist_id"));
            playlistsVo.setPlaylistName(rs.getString("playlist_name"));
            playlistsVo.setUserId(rs.getInt("user_id"));

            return playlistsVo;
        }
    }
    
    // 회원가입 가능 여부
    public Boolean isUsernameAvailable(String username) {
        String sql = "SELECT COUNT(*) FROM USER WHERE username = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        
        if (count > 0) {
        	return false;
        } else {
            return true;
        }
    }

    // 회원가입
    public boolean registerUser(String username) {
        String sqlUser = "INSERT INTO USER (username) VALUES (?)";
        String sqlPlaylist = "INSERT INTO playlists (user_id, playlist_name) VALUES ((SELECT id FROM USER WHERE username = ?), 'Default Playlist')";

        try {
            jdbcTemplate.update(sqlUser, username);

            jdbcTemplate.update(sqlPlaylist, username);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

 	public List<SongVo> selectAdmin() {
 		
 		String sql = "SELECT * FROM song_table";
 		
 		List<SongVo> songVos = new ArrayList<SongVo>();
 		
 		try {
 			songVos = jdbcTemplate.query(sql, new RowMapper<SongVo>() {
 				
 				@Override
 				public SongVo mapRow(ResultSet rs, int rowNum) throws SQLException {
 					
 					SongVo SongVO = new SongVo();
 					
 					SongVO.setSongId(rs.getInt("id"));
 					SongVO.setSinger(rs.getString("singer"));
 					SongVO.setTitle(rs.getString("title"));
 					SongVO.setGenre(rs.getString("genre"));
 					
 					return SongVO;
 					
 				}
 				});
 	}catch (Exception e) {
 		e.printStackTrace();
 	}
 		return songVos;
 	}
 	
 	public List<SongVo> selectAdmins(String keyword) {
 	    String sql = "SELECT * FROM song_table WHERE singer LIKE ? OR title LIKE ? OR genre LIKE ?";
 	    
 	    List<SongVo> songVos = new ArrayList<>();

 	    try {
 	        songVos = jdbcTemplate.query(sql, new RowMapper<SongVo>() {
 	            @Override
 	            public SongVo mapRow(ResultSet rs, int rowNum) throws SQLException {
 	                SongVo songVO = new SongVo();
 	                songVO.setSongId(rs.getInt("id"));
 	                songVO.setSinger(rs.getString("singer"));
 	                songVO.setTitle(rs.getString("title"));
 	                songVO.setGenre(rs.getString("genre"));

 	                return songVO;
 	            }
 	        }, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    }

 	    return songVos;
 	}
 	
 	public List<SongVo> nameSort(List<SongVo> songVos) {
        Collections.sort(songVos, Comparator.comparing(SongVo::getSinger));
        return songVos;
    }
 	
 	public List<SongVo> titleSort(List<SongVo> songVos) {
        Collections.sort(songVos, Comparator.comparing(SongVo::getTitle));
        return songVos;
    }
 	
 	public List<SongVo> genreSort(List<SongVo> songVos) {
        Collections.sort(songVos,
                Comparator.nullsFirst(
                        Comparator.comparing(SongVo::getGenre, Comparator.nullsLast(Comparator.naturalOrder()))
                )
        );
        return songVos;
    }

 	
 
 	public List<SongVo> genreAdd(List<SongVo> songVos, int songId, String genreInput) {
 	    String sql = "UPDATE song_table SET genre = ? WHERE id = ?";
 	    
 	    try {
 	        jdbcTemplate.update(sql, genreInput, songId);
 	        
 	       int rowsAffected = jdbcTemplate.update(sql, genreInput, songId);
 	      System.out.println("Rows affected: " + rowsAffected);


 	        
 	        for (SongVo songVo : songVos) {
 	        	if (songVo.getSongId() == songId) {
 	        		songVo.setGenre(genreInput);
 	        		break;
 	        	}
 	        }
 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    }
 	    
 	    
 	    return songVos;
 	}
 	

    public void createNewPlaylist(int userId, String playlistName) {
        String sql = "INSERT INTO playlists (user_id, playlist_name) VALUES (?, ?)";

        jdbcTemplate.update(sql, userId, playlistName);
    }
    
    public List<PlaylistsVo> getPlaylistsByUserId(int userId) {
        String sql = "SELECT * FROM playlists WHERE user_id = ?";
        
        try {
            return jdbcTemplate.query(sql, new PlaylistsRowMapper(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public void playlistAdd(int songId, int playlistId) {
        String sql = "INSERT INTO playlists_songs (playlist_id, song_id) VALUES (?, ?)";
        
        jdbcTemplate.update(sql, playlistId, songId);

    }
    
    public List<MyPlayList> playlistLoad(int playlistId) {
        String sql = "SELECT s.*, ps.playlist_id FROM playlists_songs ps " +
                     "JOIN song_table s ON ps.song_id = s.id " +
                     "WHERE ps.playlist_id = ?";

        try {
            return jdbcTemplate.query(sql, new MyPlayListRowMapper(), playlistId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static class MyPlayListRowMapper implements RowMapper<MyPlayList> {
        @Override
        public MyPlayList mapRow(ResultSet rs, int rowNum) throws SQLException {
            MyPlayList myPlayList = new MyPlayList();
            myPlayList.setSinger(rs.getString("singer"));
            myPlayList.setTitle(rs.getString("title"));
            myPlayList.setGenre(rs.getString("genre"));
            myPlayList.setsongId(rs.getInt("id"));
            myPlayList.setplaylistId(rs.getInt("playlist_id"));

            return myPlayList;
        }
    }
    
    public void removeList(int songId, int playlistId) {
        String sql = "DELETE FROM playlists_songs WHERE song_id = ? AND playlist_id = ?";
        
        try {
            jdbcTemplate.update(sql, songId,playlistId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

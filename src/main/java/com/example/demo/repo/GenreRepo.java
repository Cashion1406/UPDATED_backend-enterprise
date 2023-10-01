package com.example.demo.repo;


import com.example.demo.model.Genre;
import com.example.demo.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {

    @Query("select s.name from Genre s where s.id = :id")
    String getSongName (@Param("id") Long id);

//    @Transactional
//    @Modifying
//    @Query(value = "insert into song_playlist_tbl (playlist_id,song_id) values (:playlist_id,:song_id)", nativeQuery = true)
//    void insertSongToPlaylist(@Param("playlist_id") long song_id, @Param("song_id") long playlist_id);
//
//    @Query("update Song s set s.playlist.id = :playlist_id where s.id = :song_id")
//    @Transactional
//    @Modifying
//    void addSong(@Param("song_id") long song_id, @Param("playlist_id") long playlist_id);
//
//    @Query("update Song s set s.playlist.id = null where s.id = :song_id")
//    @Transactional
//    @Modifying
//    void removeSong(@Param("song_id") long song_id);
//
//    @Transactional
//    @Modifying
//    @Query(value = "delete from song_playlist_tbl sp where sp.song_id = :song_id and sp.playlist_id = :playlist_id", nativeQuery = true)
//    void removeSongFromPlaylist(@Param("song_id") Long song_id, @Param("playlist_id") Long playlist_id);


}

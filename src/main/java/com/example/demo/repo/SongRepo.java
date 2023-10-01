package com.example.demo.repo;


import com.example.demo.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {

    //This is get by ID, fix pending
    @Query("select s.name from Song s where s.id = :id")
    String getSongName(@Param("id") Long id);


    @Query("select s from Song s where s.album.id = :id")
    List<Song> getSongFromAlbum(@Param("id") Long id);


    List<Song> findByNameIgnoreCaseContaining(String name);


    @Query("select s from Song s inner join s.playlists p where p.id=:id ")
    List<Song> getSongFromPlaylist(@Param("id") Long id);

    Optional<Song> findByImageurl(String name);

    Optional<Song> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into song_playlist_tbl (playlist_id,song_id) values (:playlist_id,:song_id)", nativeQuery = true)
    void insertSongToPlaylist(@Param("playlist_id") long playlist_id, @Param("song_id") long song_id);

    @Transactional
    @Modifying
    @Query(value = "delete from song_playlist_tbl sp where sp.song_id = :song_id and sp.playlist_id = :playlist_id", nativeQuery = true)
    void removeSongFromPlaylist(@Param("song_id") Long song_id, @Param("playlist_id") Long playlist_id);


}

package com.example.demo.repo;

import com.example.demo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlaylistRepo extends JpaRepository<Playlist, Long> {

//    @Transactional
//    @Modifying
//    @Query(value = "update playlist_tbl pl values (playlist_id,playlist_name) values (:playlist_id,:song_id) where pl.id = :id", nativeQuery = true)
//    void updatePLaylist(@Param("playlist_url") long song_id, @Param("playlist_name") long playlist_id,@Param("id") long id);

    @Query("select p from Playlist p where p.client.id =:id ")
    List<Playlist> getPlaylistByUser(@Param("id") String id);


}

package com.example.demo.repo;

import com.example.demo.model.Album;
import com.example.demo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {

//    @Transactional
//    @Modifying
//    @Query(value = "update playlist_tbl pl values (playlist_id,playlist_name) values (:playlist_id,:song_id) where pl.id = :id", nativeQuery = true)
//    void updatePLaylist(@Param("playlist_url") long song_id, @Param("playlist_name") long playlist_id,@Param("id") long id);

    @Query("select a from Album a where a.client.id =:id ")
    List<Album> getAlbumByUser(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "update song_tbl s set album_id = null where album_id = :id", nativeQuery = true)
    void deleteAlbum(@Param("id") Long id);


}

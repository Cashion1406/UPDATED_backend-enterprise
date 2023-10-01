package com.example.demo.service;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Playlist.PlaylistRequest;
import com.example.demo.model.Client;
import com.example.demo.model.Playlist;
import com.example.demo.model.Song;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.PlaylistRepo;
import com.example.demo.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PlaylistRepo playlistRepo;

    public Playlist createPlaylist(PlaylistRequest playlistRequest) {
        Optional<Client> client = clientRepo.findById(playlistRequest.getClient_id());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        Playlist newplaylist = new Playlist();
        newplaylist.setImageurl(playlistRequest.getImageurl());
        newplaylist.setName(playlistRequest.getName());
        newplaylist.setClient(client.get());
        newplaylist.setDate(timeStamp);
        return playlistRepo.save(newplaylist);
    }

    public Playlist updatePlaylist(PlaylistRequest playlistRequest) {

        Playlist existlist = playlistRepo.findById(playlistRequest.getId()).get();
        existlist.setName(playlistRequest.getName());
        if (playlistRequest.getImageurl() == null) {
            existlist.setImageurl(existlist.getImageurl());
        } else {

            existlist.setImageurl(playlistRequest.getImageurl());
        }

        return playlistRepo.save(existlist);
    }

/*    public Playlist addSongToList(PlaylistRequest playlistRequest){


    }*/


    public List<Playlist> findUserPLaylist(String id) {
        return playlistRepo.getPlaylistByUser(id);
    }

    public Optional<Playlist> getPlaylist(Long id) {
        return playlistRepo.findById(id);
    }

    public List<Playlist> getAllPLaylist() {
        return playlistRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }


    //    public Playlist addSingleSongToList(Long playlist_id, Long songid) throws Exception {
//        Playlist existlist = playlistRepo.findById(playlist_id).get();
//        Song existsong = songRepo.findById(songid).get();
//
//        if (existsong.getPlaylists().contains(existlist)) {
//            throw new Exception("Song already");
//        }
//        existlist.getSongs().add(existsong);
//
//        return playlistRepo.save(existlist);
//
//    }
    public Optional<Playlist> addSingleSongToList(Long playlist_id, Long songid) throws Exception {
        Playlist existlist = playlistRepo.findById(playlist_id).get();
        Song existsong = songRepo.findById(songid).get();

        if (existlist.getSongs().contains(existsong)) {
            throw new Exception("Song already");
        }
        try {
            existlist.getSongs().add(existsong);
            // existsong.getPlaylists().add(existlist);
            playlistRepo.save(existlist);
            //songRepo.insertSongToPlaylist(playlist_id, songid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlistRepo.findById(playlist_id);

    }

    public Optional<Playlist> removeSingleSongToList(Long playlist_id, Long songid) {
        Playlist existlist = playlistRepo.findById(playlist_id).get();
        Song existsong = songRepo.findById(songid).get();


        try {
            existlist.getSongs().remove(existsong);
            // existsong.getPlaylists().remove(existlist);
            playlistRepo.save(existlist);

            // songRepo.removeSongFromPlaylist(songid, playlist_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlistRepo.findById(playlist_id);


    }
/*

    public Playlist updatePlaylist(PlaylistRequest playlistRequest) {
        Playlist existlist = playlistRepo.findById(playlistRequest.getId()).get();
        existlist.setImageurl(playlistRequest.getImageurl());
        existlist.setName(playlistRequest.getName());
        return playlistRepo.save(existlist);
    }
*/

    public DeleteResponse deletePLaylist(long id) {
        String name = playlistRepo.findById(id).get().getName();
        playlistRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted playlist " + name, timestamp, true);
    }

}

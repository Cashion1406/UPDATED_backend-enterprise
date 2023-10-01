package com.example.demo.service;


import com.example.demo.DTO.Album.AlbumRequest;
import com.example.demo.DTO.DeleteResponse;
import com.example.demo.model.Album;
import com.example.demo.model.Client;
import com.example.demo.repo.AlbumRepo;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.PlaylistRepo;
import com.example.demo.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private UploadService uploadService;


    public Album createAlbum(AlbumRequest albumRequest, MultipartFile album_image) {
        Optional<Client> client = clientRepo.findById(albumRequest.getClient_id());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        Album newalbum = new Album();
        newalbum.setImageurl("images/" + album_image.getOriginalFilename());
        newalbum.setName(albumRequest.getName());
        newalbum.setClient(client.get());
        newalbum.setDate(timeStamp);
        try {
            uploadService.saveImageUrl(album_image.getOriginalFilename(), album_image);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return albumRepo.save(newalbum);
    }


    public byte[] getAlbumImage(Long id) throws IOException {
        Album existalbum = albumRepo.findById(id).get();

        String albumPath = existalbum.getImageurl();

        return Files.readAllBytes(new File(albumPath).toPath());
    }


    public Optional<Album> updateAlbum(AlbumRequest albumRequest, MultipartFile image) {

        Album existAlbum = albumRepo.findById(albumRequest.getId()).get();
        existAlbum.setName(albumRequest.getName());


        if (albumRequest.getImageurl() == null) {
            existAlbum.setImageurl(existAlbum.getImageurl());
        } else {

            existAlbum.setImageurl("images/" + image.getOriginalFilename());
        }
        albumRepo.save(existAlbum);

        return albumRepo.findById(existAlbum.getId());
    }

/*    public Playlist addSongToList(PlaylistRequest playlistRequest){


    }*/


    public List<Album> findUserAlbum(String id) {
        return albumRepo.getAlbumByUser(id);
    }

    public List<Album> getAllAlbum() {
        return albumRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Optional<Album> findAlbum(Long id) {
        return albumRepo.findById(id);
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
/*    public Optional<Album> addSingleSongToAlbum(Long album_id, Long songid) throws Exception {
        Album existalbum = albumRepo.findById(album_id).get();
        Song existsong = songRepo.findById(songid).get();

        if (existalbum.getSongs().contains(existsong)) {
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
        return albumRepo.findById(album_id);

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


    }*/
/*

    public Playlist updatePlaylist(PlaylistRequest playlistRequest) {
        Playlist existlist = playlistRepo.findById(playlistRequest.getId()).get();
        existlist.setImageurl(playlistRequest.getImageurl());
        existlist.setName(playlistRequest.getName());
        return playlistRepo.save(existlist);
    }
*/

    public DeleteResponse deleteAlbum(long id) {
        String name = albumRepo.findById(id).get().getName();
        albumRepo.deleteAlbum(id);
        albumRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted playlist " + name, timestamp, true);
    }

}

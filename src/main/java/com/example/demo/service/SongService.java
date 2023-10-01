package com.example.demo.service;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Song.SongRequest;
import com.example.demo.model.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private AlbumRepo albumRepo;

    public Song uploadSong(SongRequest song, MultipartFile imageURL, MultipartFile songURL) {
        Client client = clientRepo.findById(song.getClient_id()).get();
        //Album album = albumRepo.findById(song.getAlbum_id()).get();
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(imageURL.getOriginalFilename()));
        String songName = StringUtils.cleanPath(Objects.requireNonNull(songURL.getOriginalFilename()));
        Genre genre = genreRepo.findById(song.getGenre_id()).get();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        Song newsong = new Song();
        newsong.setGenre(genre);
        newsong.setDate(timeStamp);
        newsong.setUrl("songs/" + songURL.getOriginalFilename());
        newsong.setName(song.getName());
        newsong.setClient(client);
        //newsong.setAlbum(album);
        newsong.setImageurl("images/" + imageURL.getOriginalFilename());

        try {
            uploadService.saveImageUrl(imageName, imageURL);
            uploadService.saveMP3(songName, songURL);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return songRepo.save(newsong);
    }

    //OG fetch image
    public byte[] getSongImage(String name) throws IOException {
        Song existSong = songRepo.findByName(name).get();

        String songPath = existSong.getImageurl();

        return Files.readAllBytes(new File(songPath).toPath());
    }

    public byte[] getSongUrl(String name) throws IOException {
        Song existSong = songRepo.findByName(name).get();

        String songPath = existSong.getUrl();


        return Files.readAllBytes(new File(songPath).toPath());
    }
//    public InputStreamResource getSongImage(String name) throws IOException {
//        int index = name.indexOf("/");
//        name="images/"+name.substring(index+1);
//
//        return new InputStreamResource(inputStream);
//    }

    public Song updateSong(SongRequest song) {

        Song existsong = songRepo.findById(song.getId()).get();
        existsong.setImageurl(song.getImageurl());
        existsong.setUrl(song.getUrl());
        existsong.setName(song.getName());


        return songRepo.save(existsong);
    }

    public List<Song> getAllSong() {
        return songRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }


    public Optional<Song> findSong(Long id) {
        return songRepo.findById(id);
    }

    public List<Song> getSongFromAlbum(Long id) {
        return songRepo.getSongFromAlbum(id);
    }

    public List<Song> searchSong(String name) {
        return songRepo.findByNameIgnoreCaseContaining(name);
    }

    public List<Song> getSongFromPlaylist(Long id) {

        return songRepo.getSongFromPlaylist(id);
    }

    public DeleteResponse deleteSong(long id) {
        String name = songRepo.getSongName(id);
        songRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted Category " + name, timestamp, true);
    }


}

package com.example.demo.service;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Genre.GenreRequest;
import com.example.demo.DTO.Song.SongRequest;
import com.example.demo.model.Client;
import com.example.demo.model.Genre;
import com.example.demo.model.Song;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.GenreRepo;
import com.example.demo.repo.PlaylistRepo;
import com.example.demo.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private GenreRepo genreRepo;

    public Genre addGenre(GenreRequest genreRequest) {
        Optional<Song> client = songRepo.findById(genreRequest.getSong_id());
        Genre newgerne = new Genre();

        newgerne.setName(genreRequest.getName());

        return genreRepo.save(newgerne);
    }


    public Song updateSong(SongRequest song) {

        Song existsong = songRepo.findById(song.getId()).get();
        existsong.setImageurl(song.getImageurl());
        existsong.setUrl(song.getUrl());
        existsong.setName(song.getName());


        return songRepo.save(existsong);
    }

    public List<Genre> getAllGenre() {
        return genreRepo.findAll();
    }


    public Optional<Genre> findGerne(Long id) {
        return genreRepo.findById(id);
    }

    public DeleteResponse deleteSong(long id) {
        String name = songRepo.getSongName(id);
        songRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted Category " + name, timestamp, true);
    }


}

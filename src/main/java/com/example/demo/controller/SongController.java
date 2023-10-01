package com.example.demo.controller;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Song.SongRequest;
import com.example.demo.model.Song;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {


    @Autowired
    private SongService songService;


    @GetMapping()
    public List<Song> getAllIdea() {
        return songService.getAllSong();
    }


    @GetMapping("/{id}")
    public Optional<Song> findSong(@PathVariable Long id) {
        return songService.findSong(id);
    }

    @GetMapping("/album/{id}")
    public List<Song> getSongFromAlbum(@PathVariable Long id) {
        return songService.getSongFromAlbum(id);
    }

    @GetMapping("/name")
    public List<Song> getSongFromAlbum(@RequestParam String name) {
        return songService.searchSong(name);
    }

    @GetMapping("/playlist/{id}")
    public List<Song> getSongFromPlaylist(@PathVariable Long id) {
        return songService.getSongFromPlaylist(id);
    }


    @Transactional
    @PostMapping("/upload")
    public Song createSong(@RequestPart("songinfo") SongRequest songRequest, @RequestParam("file") MultipartFile image, @RequestParam("song") MultipartFile song) {
        return songService.uploadSong(songRequest, image, song);
    }

    //testing new return image
    @GetMapping("/i/{name}")
    public ResponseEntity<?> getImage(@PathVariable String name) throws IOException {

        byte[] songData = songService.getSongImage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(songData);
    }

    @GetMapping("/s/{name}")
    public ResponseEntity<?> getSong(@PathVariable String name) throws IOException {

        byte[] resource = songService.getSongUrl(name);
        return ResponseEntity.status(HttpStatus.OK).header("Accept-Ranges", "bytes").contentType(MediaType.valueOf("audio/mpeg")).body(resource);
    }

//    @GetMapping("/{name}")
//    public ResponseEntity<InputStreamResource> getSong(@PathVariable String name) throws IOException {
//
//        InputStreamResource resource = songService.getSongImage(name);
//
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(resource);
//    }


    @PutMapping("/update")
    public Song updateSong(@RequestBody SongRequest songRequest) {
        return songService.updateSong(songRequest);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteIdea(@PathVariable Long id) {

        return songService.deleteSong(id);
    }


//    @PostMapping("/cate_idea")
//    public String addCateToIdea(@RequestBody Idea_Cate_Request ideaCateRequest) {
//
//
//        ideaService.insertIdeaCate(ideaCateRequest);
//
//        return "Added Category to Idea: " + ideaService.getIdeaName(ideaCateRequest.getIdea_id());
//    }

/*

    //Update Idea
    @PutMapping("/update")
    public Idea updateIdea(@RequestBody IdeaRequest idea) {

        return ideaService.updateIdea(idea);
    }


    //Insert comment to Idea
    @PostMapping("/comment")
    public Comment addComment(@RequestBody CommentRequest commentRequest) {

        return ideaService.insertComment(commentRequest);
    }

    //Update comment
    @PutMapping("/comment/update")
    public Comment updateComment(@RequestBody CommentRequest commentRequest) {
        return ideaService.updateComment(commentRequest);
    }


    //Delete comment from IDea
    @DeleteMapping("/comment/delete/{id}")
    public DeleteResponse deleteComment(@PathVariable Long id) {
        return ideaService.deleteComment(id);
    }


    //Insert reaction
    @PostMapping("/reaction")
    public Reaction addReaction(@RequestBody ReactionRequest reactionRequest) {

        return ideaService.insertReaction(reactionRequest);
    }


    //Update Reaction
    @PutMapping("/reaction/update")
    public Reaction updateReaction(@RequestBody ReactionRequest reactionRequest) {

        return ideaService.updateReaction(reactionRequest);
    }


    //Get Nums of Idea for each Department
    @GetMapping("/department")
    public List<IdeasPerDepartment> ideasPerDepartment() {
        return ideaService.ideasPerDepartment();
    }


    //Get top 7 idea for each Category
    @GetMapping("/top7ideas")
    public List<IdeasPerCate> ideasPerCates() {
        return ideaService.ideasPerCate();
    }


    //Get top 5 Ideas with Most view
    @GetMapping("/top5views")
    public List<Idea> top5views() {

        return ideaService.top5views();
    }


    @GetMapping("/analytics")
    public List<IdeaAnalytics> ideaAnalytics() {
        return ideaService.ideaAnalytics();
    }

*/

}

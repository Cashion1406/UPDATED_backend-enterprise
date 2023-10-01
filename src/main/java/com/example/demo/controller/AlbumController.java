package com.example.demo.controller;


import com.example.demo.DTO.Album.AlbumRequest;
import com.example.demo.DTO.DeleteResponse;
import com.example.demo.model.Album;
import com.example.demo.service.AlbumService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/album")
public class AlbumController {


    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;


    @GetMapping()
    public List<Album> getAllAlbum() {
        return albumService.getAllAlbum();
    }

    @GetMapping("/{id}")
    public Optional<Album> findAlbum(@PathVariable("id") Long id) {
        return albumService.findAlbum(id);
    }

    @GetMapping("/client/{id}")
    public List<Album> getListOfUserPlaylist(@PathVariable String id) {
        return albumService.findUserAlbum(id);
    }

//    @PutMapping("/{album_id}/song/{song_id}")
//    public Optional<Album> addsong(@PathVariable Long album_id, @PathVariable Long song_id) throws Exception {
//        return albumService.addSingleSongToAlbum(album_id, song_id);
//    }

    @PutMapping("/update")
    public Optional<Album> updateAlbum(@RequestPart AlbumRequest albumRequest, @RequestPart MultipartFile image) {
        return albumService.updateAlbum(albumRequest, image);
    }

//    @DeleteMapping("/{playlist_id}/song/{song_id}")
//    public Optional<Playlist> removeSong(@PathVariable Long playlist_id, @PathVariable Long song_id) throws Exception {
//        return albumService.removeSingleSongToList(playlist_id, song_id);
//    }

    @Transactional
    @PostMapping("/upload")
    public Album createAlbum(@RequestPart("albuminfo") AlbumRequest albumRequest, @RequestPart("image") MultipartFile image) {
        return albumService.createAlbum(albumRequest, image);
    }

    @GetMapping("/i/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) throws IOException {

        byte[] albumData = albumService.getAlbumImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(albumData);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteIdea(@PathVariable Long id) {

        return albumService.deleteAlbum(id);
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

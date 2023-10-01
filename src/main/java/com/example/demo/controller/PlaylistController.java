package com.example.demo.controller;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Playlist.PlaylistRequest;
import com.example.demo.model.Playlist;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/playlist")
public class PlaylistController {


    @Autowired
    private SongService songService;
    @Autowired
    private PlaylistService playlistService;


    @GetMapping()
    public List<Playlist> getAllIdea() {
        return playlistService.getAllPLaylist();
    }

    @GetMapping("/client/{id}")
    public List<Playlist> getListOfUserPlaylist(@PathVariable String id) {
        return playlistService.findUserPLaylist(id);
    }

    @GetMapping("/{id}")
    public Optional<Playlist> getPlaylist(@PathVariable Long id) {
        return playlistService.getPlaylist(id);
    }

    @PutMapping("/{playlist_id}/song/{song_id}")
    public Optional<Playlist> addsong(@PathVariable Long playlist_id, @PathVariable Long song_id) throws Exception {
        return playlistService.addSingleSongToList(playlist_id, song_id);
    }

    @PutMapping("/update")
    public Playlist updatePlaylist(@RequestBody PlaylistRequest playlistRequest) {
        return playlistService.updatePlaylist(playlistRequest);
    }

    @DeleteMapping("/{playlist_id}/song/{song_id}")
    public Optional<Playlist> removeSong(@PathVariable Long playlist_id, @PathVariable Long song_id) throws Exception {
        return playlistService.removeSingleSongToList(playlist_id, song_id);
    }

    @Transactional
    @PostMapping("/upload")
    public Playlist creatPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        return playlistService.createPlaylist(playlistRequest);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteIdea(@PathVariable Long id) {

        return playlistService.deletePLaylist(id);
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

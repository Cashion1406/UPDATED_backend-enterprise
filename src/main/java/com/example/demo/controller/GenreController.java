package com.example.demo.controller;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Genre.GenreRequest;
import com.example.demo.DTO.Song.SongRequest;
import com.example.demo.model.Genre;
import com.example.demo.model.Song;
import com.example.demo.service.GenreService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/genre")
public class GenreController {


    @Autowired
    private SongService songService;

    @Autowired
    private GenreService genreService;


    @GetMapping()
    public List<Genre> getAllIdea() {
        return genreService.getAllGenre();
    }


    @GetMapping("/{id}")
    public Optional<Genre> findSong(@PathVariable Long id) {
        return genreService.findGerne(id);
    }


    @Transactional
    @PostMapping("/create")
    public Genre createGenre(@RequestBody GenreRequest genreRequest) {
        return genreService.addGenre(genreRequest);
    }


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

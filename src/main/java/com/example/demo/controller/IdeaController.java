package com.example.demo.controller;


import com.example.demo.DTO.CommentRequest;
import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Idea.IdeaRequest;
import com.example.demo.DTO.Idea.IdeasPerCate;
import com.example.demo.DTO.Idea.IdeasPerDepartment;
import com.example.demo.DTO.ReactionRequest;
import com.example.demo.DTO.Topic.IdeaAnalytics;
import com.example.demo.model.Comment;
import com.example.demo.model.Idea;
import com.example.demo.model.Reaction;
import com.example.demo.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/idea")
@CrossOrigin(origins = "http://localhost:3000")
public class IdeaController {


    @Autowired
    private IdeaService ideaService;


    @GetMapping()
    public List<Idea> getAllIdea() {
        return ideaService.getAllIdea();
    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public Idea createIdea(@RequestBody IdeaRequest idea) {
        return ideaService.createIdea(idea);
    }

    @GetMapping("/{id}")
    public Optional<Idea> getIdeaById(@PathVariable long id) {

        return ideaService.getIdeaById(id);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteIdea(@PathVariable Long id) {

        return ideaService.deleteIdea(id);
    }


//    @PostMapping("/cate_idea")
//    public String addCateToIdea(@RequestBody Idea_Cate_Request ideaCateRequest) {
//
//
//        ideaService.insertIdeaCate(ideaCateRequest);
//
//        return "Added Category to Idea: " + ideaService.getIdeaName(ideaCateRequest.getIdea_id());
//    }


    //Update Idea
    @PutMapping("/update")
    public Idea updateIdea(@RequestBody IdeaRequest idea) {

        return ideaService.updateIdea(idea);
    }


    //Insert comment to Idea
//    @PostMapping("/comment")
//    public Comment addComment(@RequestBody CommentRequest commentRequest) {
//
//        return ideaService.insertComment(commentRequest);
//    }

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


//    //Insert reaction
//    @PostMapping("/reaction")
//    public Reaction addReaction(@RequestBody ReactionRequest reactionRequest) {
//
//        return ideaService.insertReaction(reactionRequest);
//    }


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


}

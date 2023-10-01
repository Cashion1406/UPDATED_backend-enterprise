package com.example.demo.controller;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Topic.TopicRequest;
import com.example.demo.DTO.Topic.TopicWithMostFollowers;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    @Autowired
    private TopicService topicServce;


    @GetMapping()
    public List<Topic> getAllTopic() {

        return topicServce.getAllTopic();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public Topic createTopic(@RequestBody TopicRequest topic) {
        return topicServce.createTopic(topic);
    }

    @GetMapping("/{id}")
    public Optional<Topic> getTopic(@PathVariable Long id) {
        return topicServce.getTopDetail(id);
    }


    @PutMapping("/update")
    public Topic updateTopic(@RequestBody TopicRequest topic) {
        return topicServce.updateTopic(topic);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteTopic(@PathVariable Long id) {

        return topicServce.deleteTopic(id);

    }

    @DeleteMapping("/softdelete/{id}")
    public String softDeleteTopic(@PathVariable Long id) {

        return topicServce.softDeleteTopic(id);
    }

    @GetMapping("/top7followers")
    public List<TopicWithMostFollowers> topicWithMostFollowers (){

        return topicServce.top7follower();

    }




}

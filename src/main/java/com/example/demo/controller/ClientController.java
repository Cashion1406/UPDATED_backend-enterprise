package com.example.demo.controller;


import com.example.demo.DTO.*;
import com.example.demo.DTO.Authentication.AuthenticationRequest;
import com.example.demo.DTO.Authentication.AuthenticationResponse;
import com.example.demo.DTO.Client.ClientDepartmentRequest;
import com.example.demo.DTO.Client.ClientUpdateRequest;
import com.example.demo.DTO.Client.Client_Topic_Request;

import com.example.demo.model.Client;
import com.example.demo.model.Idea;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthenticationService authenticationService;


    //get list of all user
    @GetMapping()
    public List<Client> getAllClient() {

        return clientService.getAllClient();
    }

//
//    //create user info
//    @PostMapping("/signup")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public Client addClient(@RequestBody ClientDepartmentRequest client) {
//
//
//        return clientService.saveClient(client);
//    }
//

    @PatchMapping()
    public ResponseEntity<?> changePassword(
            @RequestBody UpdatePassRequest updatePassRequest,
            Principal authentedUser) {

        clientService.changePassword(updatePassRequest,authentedUser);

        return ResponseEntity.ok("Password Updated");

    }

    //get user detail with id
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable String id) {

        return clientService.getClientByid(id);
    }

    //search list of user with name
    @GetMapping("/searchbyname")
    public List<Client> getClientbyName(@RequestParam String name) {
        return clientService.getClientByName(name);

    }

    //update user detail
    @PutMapping("/update")
    public Client updateClient(@RequestBody ClientUpdateRequest client) {
        return clientService.updateClient(client);
    }

    @PostMapping("/deleteall")
    public String deleteAllClient() {

        return clientService.deleteAllClient();
    }

    //set user follow topic
    @PostMapping("/topic")
    public String insertFollowTopic(@RequestBody Client_Topic_Request clientTopicRequest) {
        clientService.followTopic(clientTopicRequest);
        return "Added topics to Client " + clientService.getClientLastName(clientTopicRequest.getClient_id());
    }


    //get user's current follwed topic
//    @GetMapping("/topic/{id}")
//    public List<FollowTopic> getFollowTopic(@PathVariable String id) {
//
//
//        return clientService.followTopic(id);
//
//    }

    //user unfollow topic
    @DeleteMapping("/topic/delete")
    public String removeFollowTopic(@RequestBody Client_Topic_Request clientTopicRequest) {
        return clientService.removeTopic(clientTopicRequest.getClient_id(), clientTopicRequest.getTopic_id());
    }

//    @GetMapping("/reaction/{id}")
//    public List<ClientReaction> getReactionWithClient(@PathVariable String id) {
//
//        return clientService.getClientReaction(id);
//    }


    @GetMapping("/idea/{id}")
    public List<Idea> getIdeaWithClient(@PathVariable String id) {

        return clientService.getIdeaByClientId(id);
    }

//    @GetMapping("/notification/{id}")
//    public List<ClientNotification> getUserNotification(@PathVariable String id) {
//
//        return clientService.clientNotifications(id);
//    }


}

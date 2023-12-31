package com.example.demo.service;

import com.example.demo.DTO.Client.ClientDepartmentRequest;
import com.example.demo.DTO.Client.ClientNotification;
import com.example.demo.DTO.Client.ClientUpdateRequest;
import com.example.demo.DTO.Client.Client_Topic_Request;
import com.example.demo.DTO.ClientReaction;
import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.FollowTopic;
import com.example.demo.DTO.UpdatePassRequest;
import com.example.demo.model.Client;
import com.example.demo.model.Department;
import com.example.demo.model.ERole;
import com.example.demo.model.Idea;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.DepartmentRepo;
import com.example.demo.repo.IdeaRepo;
import com.example.demo.repo.TopicRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;


    private final DepartmentRepo departmentRepo;


    private final IdeaRepo ideaRepo;


    private final TopicRepo topicRepo;


    //Create User
    public Client saveClient(ClientDepartmentRequest clientDepartmentRequest) {

        Optional<Department> department = departmentRepo.findById(clientDepartmentRequest.getDepartment_id());
        Client newClient = new Client();
        newClient.setId(clientDepartmentRequest.getId());
        newClient.setFirstname(clientDepartmentRequest.getFirstname());

        newClient.setLastname(clientDepartmentRequest.getLastname());
        newClient.setEmail(clientDepartmentRequest.getEmail());

        if (clientDepartmentRequest.getIsDeleted() == null) {
            newClient.setIsDeleted(false);
        }
        if (clientDepartmentRequest.getRole() == null) {

            newClient.setRole(ERole.USER);
        }

        return clientRepo.save(newClient);
    }


    //Get list of Users
    public List<Client> getAllClient() {

        return clientRepo.findAll();
    }

    public Optional<Client> getClientByid(String id) {

        return clientRepo.findById(id);
    }

    public String getClientLastName(String id) {

        return clientRepo.getClientLastName(id);
    }


    //Delete User by Id
    public DeleteResponse deleteClient(String id) {
        clientRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted user " + id, timestamp, true);
    }


    //Update User
    public Client updateClient(ClientUpdateRequest client) {
        Client existClient = clientRepo.findById(String.valueOf(client.getId())).get();
//        existClient.setClient_info(client.getClient_info());
//        existClient.setAge(client.getAge());
        if (client.getRole() == null) {
            existClient.setRole(ERole.USER);

        }
        existClient.setEmail(client.getEmail());
        existClient.setFirstname(client.getFirstname());
        existClient.setLastname(client.getLastname());
        return clientRepo.save(existClient);
    }

/*    public Optional<Client> getClientByname(String firstname) {

        return clientRepo.findBynameContaining(firstname);
    }*/

    //Delete all User in db
    public String deleteAllClient() {
        clientRepo.deleteAll();
        return "SUCCESSFULLY DELETE ALL USER";
    }

    //Get list User by last name
    public List<Client> getClientByName(String name) {
        if (name.isEmpty()) {

            return clientRepo.findAll();
        }
        return clientRepo.findBylastnameContaining(name);
    }

    public void followTopic(Client_Topic_Request clientTopicRequest) {

        try {
            clientRepo.insertfollowtopic(clientTopicRequest.getTopic_id(), clientTopicRequest.getClient_id());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String removeTopic(String clientId, Long topicId) {
        try {
            clientRepo.removeFollowTopic(topicId, clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Unfollowed Topic : " + topicRepo.getTopicname(topicId);
    }

//    public List<FollowTopic> followTopic(String client_id) {
//
//        return clientRepo.findfollowtopic(client_id);
//    }
//

    public List<Idea> getIdeaByClientId(String id) {

        return ideaRepo.getideabyclientid(id);
    }

    public void changePassword(UpdatePassRequest updatePassRequest, Principal authenticatedUser) {


        //First time cast a Principal into the UsernamePasswordAuthenticationToken object, then second cast into a Client object
        var user = (Client) ((UsernamePasswordAuthenticationToken) authenticatedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(updatePassRequest.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!updatePassRequest.getNewPassword().equals(updatePassRequest.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        user.setPassword(passwordEncoder.encode(updatePassRequest.getNewPassword()));

        clientRepo.save(user);

    }


//    public List<ClientReaction> getClientReaction(String id) {
//        return clientRepo.findClientReaction(id);
//    }

//    public Client getClientQA(Long id) {
//
//        return clientRepo.getClientQA(id);
//    }

//    public List<ClientNotification> clientNotifications(String id) {
//        return clientRepo.findClientNotification(id);
//    }
}
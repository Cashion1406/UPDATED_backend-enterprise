package com.example.demo.DTO.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client_Topic_Request {

   private long topic_id;

   private String client_id;
}

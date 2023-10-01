package com.example.demo.DTO.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientNotification {
    private long noti_id;

    private String content;

    private String noti_time;

    private Boolean status;

    private String client_noti_title;
}

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification_tbl")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "noti_content")
    private String content;

    @Column(name = "client_id")
    private String client_id;

    @Column(name = "noti_time")
    private String createdAt;

    @Column(name = "noti_status")
    private Boolean status;

    @Column(name = "isDeleted")
    private Boolean isDelete;

    @Column(name = "noti_title")
    private String title;
}

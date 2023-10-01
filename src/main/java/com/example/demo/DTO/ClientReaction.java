package com.example.demo.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientReaction {
    private Long idea_id;

    private long reaction_id;

    private Boolean reaction;
}

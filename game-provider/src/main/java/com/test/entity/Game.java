package com.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "game_id")
    private long gameId;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

}

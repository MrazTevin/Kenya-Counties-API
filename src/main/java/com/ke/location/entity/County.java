package com.ke.location.entity;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name="counties")
@Entity
public class County {
    @Id
    private Integer id;
    private String name;

    public County(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

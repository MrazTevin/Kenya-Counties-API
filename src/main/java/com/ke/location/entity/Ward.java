package com.ke.location.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name="tbl_wards")
@Entity
public class Ward {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private SubCounty subCounty;

    public Ward(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}







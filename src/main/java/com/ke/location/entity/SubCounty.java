package com.ke.location.entity;


import lombok.AllArgsConstructor;
import lombok.Setter;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="subcounties")
@Entity
public class SubCounty {
    @Id
    private Integer id;

    private String name;
    private String ward;
    private String alias;

    @ManyToOne
    @JoinColumn(name = "county_id")
    private County county;

    public SubCounty(int id, String name,String ward) {
        this.id = id;
        this.name = name;
        this.ward=ward;
    }
}

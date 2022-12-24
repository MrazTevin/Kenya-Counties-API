package com.ke.location.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_counties")
@Entity
public class SubCounty {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "subCounty_id")
    private County county;

}

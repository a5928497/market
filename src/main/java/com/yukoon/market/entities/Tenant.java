package com.yukoon.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RENTAL")
    private Float rental;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ID_CARD")
    private String ID_Card;

    @Column(name = "PHONE_NUMBER")
    private String phone_number;
}

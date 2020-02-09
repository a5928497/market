package com.yukoon.market.entities;

import javax.persistence.*;

@MappedSuperclass

public abstract class BasicBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "START_DEGREE")
    private Float start_degree;

    @Column(name = "END_DEGREE")
    private Float end_degree;

    @Column(name = "START_DATE")
    private String start_date;

    @Column(name = "END_DATE")
    private String end_date;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "IS_PAID")
    private Integer is_paid;

}

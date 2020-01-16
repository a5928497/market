package com.yukoon.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "MANAGEMENT_FEES")
    private Float management_fees;

    @Column(name = "OTHER_FEE")
    private Float other_fees;

    @Column(name = "ELE_PRICE")
    private Float ele_price;

    @Column(name = "WATER_PRICE")
    private Float water_price;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ID_CARD")
    private String ID_Card;

    @Column(name = "PHONE_NUMBER")
    private String phone_number;

    @Column(name = "DEPOSIT")
    private Float deposit;

    //是否纯收租金
    @Column(name = "IS_PURE_INCOME")
    private Integer is_pure_income;

    //缴款方式：0日缴 1月缴 2年缴
    @Column(name = "RENT_STYLE")
    private Integer rent_style;

    //缴款周期 n 年/月/日一缴
    @Column(name = "RENT_CYCLE")
    private Integer rent_cycle;

    //下次缴款日期
    @Column(name = "NEXT_PAYMENT_DATE")
    private String next_payment_date;

    //0=已撤场 1=营业中
    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CHANGE_DATE",columnDefinition = "text")
    private String change_date;

    @JoinColumn(name = "MARKET_ID")
    @ManyToOne
    private Market market;
}

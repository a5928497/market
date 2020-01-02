package com.yukoon.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WaterBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "START_DEGREE")
	private Float start_degree;

	@Column(name = "END_DEGREE")
	private Float end_degree;

	@Column(name = "START_DATE")
	private Data start_date;

	@Column(name = "END_DATE")
	private Date end_date;

	@Column(name = "PRICE")
	private Float price;

	@Column(name = "IS_PAID")
	private Integer is_paid;

	@JoinColumn(name = "TENANT_ID")
	@ManyToOne
	private Tenant tenant;
}

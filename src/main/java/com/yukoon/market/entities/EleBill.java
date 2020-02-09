package com.yukoon.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EleBill extends BasicBill{

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

	@JoinColumn(name = "TENANT_ID")
	@ManyToOne
	private Tenant tenant;

	@JoinColumn(name = "ELEMETER_ID")
	@ManyToOne
	private EleMeter meter;
}

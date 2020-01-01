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
public class WaterMeter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DEGREE_NOW")
	private Float degree_now;

	@Column(name = "DEGREE_OLD")
	private Float degree_old;

	@Column(name = "DEGREE_NOW_DATE")
	private Date degree_now_date;

	@Column(name = "DEGREE_OLD_DATE")
	private Date degree_old_date;

	@JoinColumn(name = "TENANT_ID")
	@ManyToOne
	private Tenant tenant;
}

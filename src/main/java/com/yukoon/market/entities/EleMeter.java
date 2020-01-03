package com.yukoon.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class EleMeter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DEGREE")
	private Float degree;

	@Column(name = "UPDATE_DATE")
	private String update_date;

	@JoinColumn(name = "TENANT_ID")
	@ManyToOne
	private Tenant tenant;

	@JoinColumn(name = "MARKET_ID")
	@ManyToOne
	private Market market;
}

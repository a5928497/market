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

	@Column(name = "DEGREE")
	private Float degree;

	@Column(name = "UPDATE_DATE")
	private String update_date;

	//0停用 1启用
	@Column(name = "STATUS")
	private Integer status;

	@JoinColumn(name = "TENANT_ID")
	@ManyToOne
	private Tenant tenant;

	@JoinColumn(name = "MARKET_ID")
	@ManyToOne
	private Market market;


	//0表数异常 1成功更改客户 2生成新的欠款
	@Transient
	private Integer change_status;
}

package com.yukoon.market;

import com.yukoon.market.entities.Market;
import com.yukoon.market.repository.MarketRepo;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketApplicationTests {

	@Autowired
	private MarketRepo marketRepo;

	@Test
	public void contextLoads() {
//		Market market = new Market().setName("111");
		System.out.println(marketRepo.findAll());
	}

}

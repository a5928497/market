package com.yukoon.market;

import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.entities.Market;
import com.yukoon.market.entities.Tenant;
import com.yukoon.market.repository.MarketRepo;
import com.yukoon.market.services.EleMeterService;
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
	@Autowired
	private EleMeterService eleMeterService;

	@Test
	public void contextLoads() {
		EleMeter eleMeter = eleMeterService.findById(8);
		eleMeter.setTenant(new Tenant().setId(1));
		eleMeterService.save(eleMeter);
	}

}

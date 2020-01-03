package com.yukoon.market.controllers;

import com.yukoon.market.services.EleMeterService;
import com.yukoon.market.services.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WaterMeterController {
    @Autowired
    private WaterMeterService waterMeterService;
}

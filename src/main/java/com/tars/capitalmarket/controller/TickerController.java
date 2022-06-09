package com.tars.capitalmarket.controller;

import com.tars.capitalmarket.service.TickerService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class TickerController {

    TickerService tickerService;

    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @Get("/ltp/{instrument}")
    public void getLtp(String instrument) {
        tickerService.getLtp("NSE:NIFTY BANK");
    }

}

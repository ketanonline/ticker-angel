package com.tars.capitalmarket.service;

import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.tars.capitalmarket.client.KiteClient;
import jakarta.inject.Singleton;

@Singleton
public class TickerService {

    KiteClient kiteClient;

    public TickerService(KiteClient kiteClient) {
        this.kiteClient = kiteClient;
    }

    public void getLtp(String instrument) {
        kiteClient.tickerUsage();
    }
}

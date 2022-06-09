package com.tars.capitalmarket.client;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.ticker.OnConnect;
import com.angelbroking.smartapi.ticker.OnTicks;
import com.angelbroking.smartapi.ticker.SmartAPITicker;
import jakarta.inject.Singleton;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.Optional;

@Singleton
public class KiteClient {

    private final SmartAPITicker tickerProvider;
    User user;
    Logger logger = LoggerFactory.getLogger(KiteClient.class);

    public KiteClient(User user) {
        this.user = user;
        tickerProvider = new SmartAPITicker("R83031", user.feedToken,
                "nse_fo|40681", "mw");
        tickerUsage();
    }

    public void tickerUsage() {

        tickerProvider.setOnConnectedListener(new OnConnect() {
            @Override
            public void onConnected() {
                logger.info("subscribe() called!");
                tickerProvider.subscribe();
            }
        });

        tickerProvider.setOnTickerArrivalListener(new OnTicks() {
            @Override
            public void onTicks(JSONArray ticks) {
                logger.info("ticker data: " + ticks.toString());
            }
        });

        /**
         * connects to Smart API ticker server for getting live quotes
         */
        tickerProvider.connect();

        /**
         * You can check, if websocket connection is open or not using the following
         * method.
         */
        boolean isConnected = tickerProvider.isConnectionOpen();
        logger.info("Connected: " + isConnected);
        tickerProvider.subscribe();

        // After using SmartAPI ticker, close websocket connection.
        // tickerProvider.disconnect();

    }
}

package com.tars.capitalmarket.config;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.SessionExpiryHook;
import com.angelbroking.smartapi.models.User;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Factory
public class SmartConnectFactory {

    Logger logger = LoggerFactory.getLogger(SmartConnectFactory.class);
    @Singleton
    public User user() {
        // Initialize Samart API using clientcode and password.
        SmartConnect smartConnect = new SmartConnect();

        // Provide your api key here
        smartConnect.setApiKey("uCV61rbj");

        // Set session expiry callback.
        smartConnect.setSessionExpiryHook(new SessionExpiryHook() {
            @Override
            public void sessionExpired() {
                logger.info("session expired");
            }
        });

        User user = smartConnect.generateSession("R83031", "Bliss00");
        logger.info(user.toString());
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());
        return user;
    }
}

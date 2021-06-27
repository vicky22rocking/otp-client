package com.cloudbloom.service;

import com.cloudbloom.model.SmsPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
public class SmsService {


    private final String ACCOUNT_SID = "ACCOUNT_SID";

    private final String AUTH_TOKEN = "AUTH_TOKEN";

    private final String FROM_NUMBER = "FROM_NUMBER";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                                 .create();
        System.out.println("here is my id:" + message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}

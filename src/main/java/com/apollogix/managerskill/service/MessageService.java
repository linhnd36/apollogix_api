package com.apollogix.managerskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import static com.apollogix.managerskill.constants.MessageLabel.MSG_DEFAULT_LABEL_NOT_FOUND;

@Service
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    public String getMessages(String label){
        return messageSource.getMessage(label, null, MSG_DEFAULT_LABEL_NOT_FOUND, null);
    }

    public String getMessages(String label, String... args){
        return messageSource.getMessage(label, args, MSG_DEFAULT_LABEL_NOT_FOUND, null);
    }

}

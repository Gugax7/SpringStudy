package com.ggx.dev.services;

import com.ggx.dev.models.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private List<Message> messagesHistory = new ArrayList<>();

    public void sendMessage(Message message){
        messagesHistory.add(message);
        System.out.println(message.getSender().getName() +
                "sent a message to" +
                message.getReceiver().getName()+
                ": " +
                message.getContent());
    }

    public void printHistory(){
        System.out.println("---------------------- \nMESSAGE HISTORY:");
        for(Message message : messagesHistory){
            System.out.println(message.getSender().getName() +
                    "sent a message to" +
                    message.getReceiver().getName()+
                    ": " +
                    message.getContent());
        }
    }
}

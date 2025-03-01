package com.ggx.dev.controllers;

import com.ggx.dev.models.Message;
import com.ggx.dev.models.User;
import com.ggx.dev.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    public void startChat(){
        Scanner sc = new Scanner(System.in);
        User ana = new User("Ana");
        User pedro = new User("Pedro");

        boolean running = true;
        while(running){
            System.out.print("Whats the sender? (Ana/Pedro):");
            String senderName = sc.nextLine();

            User sender = senderName.equalsIgnoreCase("Ana") ? ana : pedro;
            User receiver = senderName.equalsIgnoreCase("Ana") ? pedro : ana;
            System.out.print("Type the message: ");
            String message = sc.nextLine();

            chatService.sendMessage(new Message(message,sender,receiver));

            System.out.print("""
                    Type:
                    history -> type entire message history
                    continue -> send next message
                    exit -> end program
                    
                    -> """);

            String answer = sc.nextLine();
            switch (answer.toLowerCase()){
                case "history" -> chatService.printHistory();
                case "continue" -> {
                    // do nothing :)
                }
                case "exit" -> {
                    running = false;
                }
                default -> System.out.println("Sorry i don't understand what '" + answer + "' means");

            }
        }
    }
}

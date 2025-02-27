package com.ggx.dev.services;

import org.apache.tomcat.util.net.IPv6Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component
public class Handler {
    @Autowired
    private InputService inputService;
    @Autowired
    private PeekingIterator<Integer> peekingIterator;

    public void handleInputOutput(){
        List<Integer> numberList = inputService.collectInput();
        Iterator<Integer> iterator = numberList.iterator();

        peekingIterator.setIterator(iterator);
        Scanner sc = new Scanner(System.in);

        System.out.println("Peeking Iterator functionality: ");
        while(peekingIterator.hasNext()){

            System.out.println("Peek: " + peekingIterator.peek());
            System.out.println("Next: " + peekingIterator.next());

        }
    }
}

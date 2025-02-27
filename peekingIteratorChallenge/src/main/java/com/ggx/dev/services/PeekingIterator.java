package com.ggx.dev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PeekingIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private T nextElement;

    public void setIterator(Iterator<T> iterator){
        this.iterator = iterator;
        if(iterator.hasNext()){
            nextElement = iterator.next();
        }
    }

    public T peek(){
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public T next() {
        T current = nextElement;
        nextElement = iterator.hasNext() ? iterator.next() : null;
        return current;
    }
}



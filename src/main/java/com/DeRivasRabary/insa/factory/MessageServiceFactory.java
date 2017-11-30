package com.DeRivasRabary.insa.factory;

public interface MessageServiceFactory<T> {
        T onSend();

        T onReceive();
    }


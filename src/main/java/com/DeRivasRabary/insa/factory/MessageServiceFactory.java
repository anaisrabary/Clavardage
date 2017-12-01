package com.DeRivasRabary.insa.factory;

public interface MessageServiceFactory<T> {
        T onUDP();

        T onTCP();

    }


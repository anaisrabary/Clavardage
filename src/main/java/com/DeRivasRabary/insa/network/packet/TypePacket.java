package com.DeRivasRabary.insa.network.packet;


/**
 * Classe non utilisée
 */
public enum TypePacket {
        MESSAGE("MSG"), HELLO("HELLO"), BYE("BYE");

        private String type;

        TypePacket(String type) {
            this.type = type;
        }

    }



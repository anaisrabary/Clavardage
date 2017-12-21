package com.DeRivasRabary.insa.model.packet;

public enum TypePacket {
    MESSAGE("MSG"),
    HELLO("HELLO"),
    BYE("BYE"),
    FILE("FILE"),
    NOTIF("NOTIFICATION");

    private String type;

    TypePacket(String type) {
        this.type = type;
    }
    @Override
    public String toString(){ return type;}

}
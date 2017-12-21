package com.DeRivasRabary.insa.model.packet;


import java.net.InetAddress;

import static com.DeRivasRabary.insa.model.packet.TypePacket.FILE;

/**
 * @author alex205
 */
public class File extends PacketManager {
    private String fileName;
    private String mimeType;
    private double size;
    private byte[] content;

    public File(InetAddress addrSource, InetAddress addrDestination, String pseudo,  String fileName, String mimeType, double size, byte[] content) {
        super(addrSource, addrDestination, pseudo, FILE);
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.size = size;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public double getSize() {
        return size;
    }

    public byte[] getContent() {
        return content;
    }
}

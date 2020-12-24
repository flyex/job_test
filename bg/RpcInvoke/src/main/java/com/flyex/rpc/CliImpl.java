package com.flyex.rpc;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class CliImpl implements Cli {

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return PROTOCOL_VERSION;
    }

    @Override
    public String hello(String name) {
        System.out.println("invoked");
        return "hello" + name;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }
}

package com.flyex.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class Server {

    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 12345;

    public static void main(String[] args) {

        RPC.getServerAddress(new Object());

    }

}

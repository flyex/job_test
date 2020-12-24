package com.flyex.rpc;


import org.apache.hadoop.ipc.VersionedProtocol;

public interface Cli extends VersionedProtocol {

    long PROTOCOL_VERSION = 12321443L;

    String hello(String name);

}

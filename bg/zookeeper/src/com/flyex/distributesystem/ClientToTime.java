package com.flyex.distributesystem;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientToTime {
    ZooKeeper zk = null;
    private volatile ArrayList<String> onlineServers = new ArrayList<>();

    public void connectZK() throws Exception{
        zk = new ZooKeeper("hdp-02:2181,hdp-03:2181,hdp-04:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected&&event.getType()== Event.EventType.NodeChildrenChanged){
                    try {
                        getOnlineServers();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //查询在线服务器，并设置监听
    public void getOnlineServers() throws Exception{
        List<String> childrens = zk.getChildren("/servers", true);
        ArrayList<String> servers = new ArrayList<String>();

        for (String s:childrens){
            byte[] data = zk.getData("/servers/" + s, false, null);
            String info = new String(data);
            servers.add(info);
        }
        onlineServers = servers;
        System.out.println("在线的服务器有："+servers);
    }

    //client业务逻辑
    public void getTime() throws Exception{
        Random random = new Random();
        while (true){
            try {
                int neeInt = random.nextInt(onlineServers.size());
                String server = onlineServers.get(neeInt);
                String hostname = server.split(":")[0];
                int port = Integer.parseInt(server.split(":")[1]);
                System.out.println("本次请求的时间服务器是："+server);

                Socket socket = new Socket(hostname,port);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                outputStream.write("go".getBytes());
                outputStream.flush();

                byte[] buf = new byte[256];
                int read = inputStream.read(buf);
                System.out.println(new java.lang.String(buf,0,read));

                outputStream.close();
                inputStream.close();
                socket.close();

                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ClientToTime clientToTime = new ClientToTime();

        clientToTime.connectZK();

        clientToTime.getOnlineServers();

        clientToTime.getTime();
    }
}

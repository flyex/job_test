package com.flyex.distributesystem;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.jboss.netty.handler.timeout.IdleState;

public class TimeServerRegist {
    ZooKeeper zk = null;
    public void connectZK() throws Exception{
        zk = new ZooKeeper("hdp-02:2181,hdp-03:2181,hdp-04:2181",2000,null);
    }

    public void registerServerInfo(String hostname,String port) throws Exception{
        Stat stat = zk.exists("/servers", false);
        if (stat==null){
            zk.create("/servers", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        String create = zk.create("/servers/server",(hostname+":"+port).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+"成功注册到zk服务器，注册的节点为"+create);
    }

    public static void main(String[] args) throws Exception {
        TimeServerRegist timeServerRegist = new TimeServerRegist();

        timeServerRegist.connectZK();

        timeServerRegist.registerServerInfo(args[0],args[1]);

        new TimeService(Integer.parseInt(args[1])).start();
    }
}

package com.flyex.demo;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

public class WatchDemo {
    ZooKeeper zk = null;
    @Before
    public void init() throws Exception{
        zk = new ZooKeeper("hdp-02:2181,hdp-03:2181,hdp-04:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected&&event.getType() == Event.EventType.NodeDataChanged){
                    System.out.println(event.getPath());
                    System.out.println(event.getType());
                    try {
                        byte[] data = zk.getData("/root", true, null);
                        System.out.println("root节点数据改为："+new String(data,"UTF-8"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if (event.getState() == Event.KeeperState.SyncConnected&&event.getType() == Event.EventType.NodeChildrenChanged){
                    System.out.println("子节点发生了变化");
                }
            }
        });
    }
    @Test
    public void testWatch() throws Exception{
        byte[] data = zk.getData("/root", true, null);
        zk.getChildren("/root",true);
        System.out.println(new String(data,"UTF-8"));

        Thread.sleep(Long.MAX_VALUE);
    }
}

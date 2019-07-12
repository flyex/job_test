package com.flyex.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ZookeeperClientDemo {
    ZooKeeper zk = null;

    @Before
    public void init() throws Exception{
        zk = new ZooKeeper("hdp-02:2181,hdp-03:2181,hdp-04:2181",2000,null);
    }

    @Test
    public void testCreate() throws Exception{
        String path = zk.create("/idea", "hello idea".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
        zk.close();
    }
    @Test
    public void testUpdate() throws Exception{
        zk.setData("/idea","hello idea&&eclipse".getBytes(),-1);
        zk.close();
    }
    @Test
    public void testGet() throws Exception{
        byte[] data = zk.getData("/idea", false, null);
        String s = new String(data,"UTF-8");
        System.out.println(s);
        zk.close();
    }

    @Test
    public void testGetChildren() throws Exception{
        List<String> childrens = zk.getChildren("/idea", false);
        for (String s : childrens) {
            System.out.println(s);
        }
        zk.close();
    }
    @Test
    public void testDelete() throws Exception{
        zk.delete("/aa",-1);
        zk.close();
    }
}

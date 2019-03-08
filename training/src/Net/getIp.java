package Net;

/**
 * Created by flyex on 2018/10/29.
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class getIp {
    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName("shfile");
        }
        catch (UnknownHostException e) {
            System.exit(2);
        }
        System.out.println(address.getHostName() + "=" + address.getHostAddress());
        System.exit(0);
    }
}
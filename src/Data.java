import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;

// Getting ip and saving user data
public class Data {
    
    public static AtomicReference<ArrayList<User>> users = new AtomicReference<>(new ArrayList<>());

    public static String getIp() {
        if(System.getProperty("os.name").contains("Windows")){
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) { }
        }

        String retString = "";
        try {
            Enumeration<InetAddress> addrs = NetworkInterface.getNetworkInterfaces().nextElement().getInetAddresses();
            while (addrs.hasMoreElements()) {
                InetAddress addr = addrs.nextElement();
                retString = addr.getHostAddress();
            }
        } catch (SocketException e) { }
        return retString;
    }

    
}


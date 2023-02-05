import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Data {
    public static ArrayList<String> ansList = new ArrayList<String>();

    public static int numOfQ = 0;
    
    public ArrayList<User> users = new ArrayList<User>();

    public static String getIp() {
        if(System.getProperty("os.name").contains("Windows")){

            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }


        }

        String retString = "";
        try {
            Enumeration<InetAddress> addrs = NetworkInterface.getNetworkInterfaces().nextElement().getInetAddresses();
            while (addrs.hasMoreElements()) {
                InetAddress addr = addrs.nextElement();
                retString = addr.getHostAddress();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return retString;
    }
}


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Data {
    public static ArrayList<String> ansList = new ArrayList<String>();

    public static int numOfQ = 0;
    
    public ArrayList<User> users = new ArrayList<User>();

    public void getData(String dataIn) {
        // received string turn into data (int, string, char...) 
    }

    public static String getIp() {
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


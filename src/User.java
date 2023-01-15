import java.util.ArrayList;

public class User {
    public String userName;
    public char group;
    public int id, points, grade;
    public ArrayList<Integer> ans;

    public User(String dataIn) {                                                // dataIn = "userName@group@points@grade"
        ans = new ArrayList<Integer>();
        String[] strArr = dataIn.split("@", 4);

        userName = strArr[0];
        group = strArr[1].charAt(0);
        points = Integer.parseInt(strArr[2]);
        grade = Integer.parseInt(strArr[3]);
    }

    public User() {
    }
}
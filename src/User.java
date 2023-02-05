import java.util.ArrayList;

public class User {
    public String userName;
    public int id, numOfQuestions;
    public ArrayList<Integer> ans;
    public ArrayList<String> ansClient;

    enum side {
        HOST, 
        CLIENT
    }

    public User(String dataIn, side s) {                                                
        if(s == side.HOST) constructHostSide(dataIn);
        else constructClientSide(dataIn);
    }

    // dataIn = "id@userName@ans0@ans1@ans2@ans3..."
    private void constructHostSide(String dataIn) {
        ans = new ArrayList<Integer>();
        String[] strArr = dataIn.split("@", 4);

        id = Integer.parseInt(strArr[0]);
        userName = strArr[1];
        for(int i = 2; i<strArr.length; i++) ansClient.add(strArr[i]);
    }

    // dataIn = id@numOfQuestions@ans0@ans1@ans2@ans3...
    private void constructClientSide(String dataIn) {
        ansClient = new ArrayList<String>();
        String[] strArr = dataIn.split("@", 0);

        id = Integer.parseInt(strArr[0]);
        numOfQuestions = Integer.parseInt(strArr[1]);
        for(int i = 2; i<strArr.length; i++) ansClient.add(strArr[i]);
    }
}
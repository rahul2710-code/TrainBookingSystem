import java.util.HashMap;
import java.util.Map;


public class userservice {

    private Map<String,user> userMap=new HashMap<>();

    private user currentUser=null;

    public boolean registerUser(String username,String password,String fullname,String contact)
    {
        if(userMap.containsKey(username))
        {
            System.out.println("Username already taken,Please choose another");
            return false;
        }
        user user=new user (username,password,fullname,contact);
        userMap.put(username,user);
        System.out.println("Registration Successful!");
        return true;
    }

    public boolean loginUser(String username,String password)
    {
        if(!userMap.containsKey(username))
        {
            System.out.println("No user found with this username");
            return false;
        }

        user user=userMap.get(username);
        System.out.println("Entered password: " + password);
        System.out.println("Stored password: " + user.getPassword());

        if (user.getPassword() == null || !user.getPassword().equals(password))
        {
            System.out.println("Incorrect password.");
            return false;
        }

        currentUser=user;
        System.out.println("Welcome :"+ currentUser.getFullname()+"!");
        return true;
    }

    public void logoutuser()
    {
         if(currentUser!=null){
             System.out.println("Logged Out"+currentUser.getFullname());
         }
         currentUser=null;
    }

    public user getCurrentUser(){
        return currentUser;
    }

    public boolean isLoggin(){
        return currentUser!=null;
    }
}

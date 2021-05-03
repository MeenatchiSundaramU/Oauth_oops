
package prog1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import static prog1.Print.print;
import static prog1.get.get;
interface Authenticate //Using interface to abstract the level of access like Access Tokens
{
    void printCredentials();
}
interface Location 
{
    void printLocation();
}
class Google implements Authenticate,Location   //It is similar to google server(Resource Hosting Server)
{
    private String username,pass,firstname,lastname,dob,Address;
    public static int totalusers=0;
    private static HashMap<Integer,Google> users=new HashMap<Integer,Google>();
    Google()
    {
        totalusers++;
        getCredentials();
        getLocation();
        print("Note your google Authentication id:('"+totalusers+"')");
        users.put(totalusers, this);
    }
    static boolean check(int k)
    {
        if(users.containsKey(k))
            return true;
        else
            return false;
    }
    static void displayData(int k,String abstracts_level)
    {
        Google g=users.get(k);
        if(abstracts_level=="auth")
        {
            Authenticate auth=g;
            auth.printCredentials();
        }
        else if(abstracts_level=="location")
        {
            Location loc=g;
            loc.printLocation();
        }
    }
    private void getCredentials() {
        print("Enter the username: ");
        username=get().next();
        print("Enter the password: ");
        pass=get().next();
        print("Enter the first name: ");
        firstname=get().next();
        print("Enter the last name: ");
        lastname=get().next();
         print("Enter the DOB: ");
        dob=get().next();
    }

    @Override
    public void printCredentials() {
       print("\nYour username:"+username);
       print("\nYour pass:"+pass);
       print("\nFirst Name:"+firstname);
       print("\nLast Name: "+lastname+"\n");
    }
    private void getLocation() {
         print("\nEnter the Location:");
        Address=get().next();
    }

    @Override
    public void printLocation() {
        print("\nYour location"+Address+"\n");
    }
}
class Server 
{
    public static void createAccount()
    {
           Google go=new Google();
    }
    public static boolean checkAcc(int key)
    {
        return Google.check(key);
    }
    public static boolean permitClient(String p)
    {
        if(p.equals("auth"))
        {
            print("The Client is going access your authentication details.Will you agree to Allow (true/false)");
            return get().nextBoolean();
        }
        else if(p.equals("location"))
        {
            print("The Client is going access your locations.Will you agree to Allow (true/false)");
            return get().nextBoolean();
        }
        else
        {
            print("The Client is going access your google drive.Will you agree to Allow (true/false)");
            return get().nextBoolean();
        }
    }
    public static void retrieveData(int k,String abstracts_level)
    {
           Google.displayData(k, abstracts_level);
    }
}

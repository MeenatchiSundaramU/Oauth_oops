package prog1;
import java.util.HashMap;
import static prog1.Print.print;
import static prog1.get.get;
class ClientServer
{
    static int clientcnt=0;
    private static HashMap<Integer,ClientServer>clientUser=new HashMap<Integer,ClientServer>();
    int gid=0;
    boolean auth=false;
    ClientServer(String s,int ids) // Sign In with google
    {
       if(Server.permitClient(s))
       {
           print("Permission Granted");
           clientcnt++;
           gid=ids;
           print("\nThe following Authentications datas are granted by the Resource Server for sign in using google\n");
           Server.retrieveData(gid,s);
           auth=true;
           print("Note your Client Id for further request to server ('"+clientcnt+"')");
           clientUser.put(clientcnt,this);
       }
       else
       {
           print("Permission Denied");
       }
    }
    static void getLocation(String s)//Getlocations using google
    {
        print("Enter your Client auth Id");
        int ids=get().nextInt();
        if(Server.permitClient(s))
        {
         print("\nNow we can track the locations with the help of google\n");
        Server.retrieveData(clientUser.get(ids).gid,s);
        }
        else
       {
           print("Permission Denied");
       }
    }
}
class Client  //Client Web Application
{
    public static void main(String args[])
    {
        int ch=0;
        do
        {
            print("\n1.Create an Google Account\n2.SignIn with Google\n3.Get Locations\n4.Exit App");
            ch=get().nextInt();
            switch(ch)
            {
                case 1:Server.createAccount();
                       break;
                case 2:print("Enter your google auth id: ");
                       int ids=get().nextInt();
                       if(Server.checkAcc(ids))
                       {
                          ClientServer client=new ClientServer("auth",ids);
                       }
                       else
                           print("Your id is not found");
                       break;
                case 3:ClientServer.getLocation("location");
                       break;
            }
        }while(ch!=4);
    }
}

package cyberSecurity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class User_B_Deffi_Hel {
	  static int moduloOf(int num,int pow,int n)
		{
			int result=1;
			
			while(pow>0)
			{
				if(pow%2==1)
				{
					result=(result*num)%n;
				}
				num=(num*num)%n;
				pow=pow/2;
			}
			
			return result%n;
		}
	
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	
        try{
            ServerSocket ss=new ServerSocket(6767);
            Socket s=ss.accept();
            
            int prime;
            System.out.println("Enter public prime number (P): ");
            prime= scanner.nextInt();
            
            System.out.println("Enter Choosen primitive root (G)");
            int g=scanner.nextInt();
            
            System.out.println("User B enter your Secret key (a):");
            int B = scanner.nextInt();
            
            int y = moduloOf(g,B,prime);
            
            System.out.println("User B your calculated public key is (Y):"+y);
            
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String  str=(String)dis.readUTF();
            
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF(String.valueOf(y));
            
            System.out.println("Recieved Public key of User A is (X) :"+str);
            System.out.println();
            
            System.out.println("Kb : " + moduloOf(Integer.parseInt(str),B,prime));
            
            
   
            ss.close();
            scanner.close();
        }catch(Exception e){System.out.println(e);}
    }
     
}


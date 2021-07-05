package cyberSecurity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class User_A_Deffi_Hel {
	
    static boolean isprime(int x){
       for(int i=2;i<= Math.sqrt(x);i++){
           if (x % i == 0)
               return false;
       }
       return true;
   } 

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
   
   
   static void find_Prime_factors(Set<Integer> st, int n) 
   {
       while (n % 2 == 0) 
       {
           st.add(2);
           n = n / 2;
       }
 
       for (int i = 3; i <= Math.sqrt(n); i = i + 2) 
       {
           while (n % i == 0) 
           {
               st.add(i);
               n = n / i;
           }
       }
       if (n > 2) 
       {
           st.add(n);
       }
   }
   
   
   static int choose_primitive(int n) 
   {
       Set<Integer> s = new HashSet<Integer>();

       if (isprime(n) == false) 
       {
           return -1;
       }

       int phi = n - 1;

       find_Prime_factors(s, phi);

       for (int root = 2; root <= phi; root++) 
       {
           boolean flag = false;
           for (Integer a : s) 
           {
               if (moduloOf(root, phi / (a), n) == 1) 
               {
                   flag = true;
                   break;
               }
           }
 
           if (flag == false)
           {
               return root;
           }
       }
       return -1;
   }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            Socket s=new Socket("localhost",6767);
            
            int prime;
            System.out.println("Enter public prime number (P): ");
            prime= scanner.nextInt();
   
            while(!isprime(prime)){
                System.out.println("You must enter a prime Number : ");
                prime=scanner.nextInt();
            }
            
            int g =choose_primitive(prime);
            
            System.out.println("Choosen primitive root of "+prime+" is :"+g);
            
            System.out.println("User A enter your Secret key (a):");
            int A = scanner.nextInt();
            
            int x =  moduloOf(g,A,prime);
            
            System.out.println("User A your calculated public key (X) is:"+x);
            System.out.println();
            
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF(String.valueOf(x));
            
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String  str=(String)dis.readUTF();

            System.out.println("Recieved Public key of User B is (Y) :"+str);
            System.out.println();
            
            System.out.println("Ka : " + moduloOf(Integer.parseInt(str),A,prime));
            
            
            dout.flush();
            dout.close();
            s.close();
            scanner.close();
        }catch(Exception e){System.out.println(e);}
    }


}

package cyberSecurity;

import java.util.Scanner;

public class RSA_Algo {
	
	static boolean isPrime(int a)
	{
		for(int i=2;i<=Math.sqrt(a);i++)
		{
			if(a%i==0)
			{
				return false;
			}
		}
		return true;
	}
  
	static int gcd(int a,int b)
	{
		if(b==0)
		{
			return a;
		}
		return gcd(b,a%b);
	}
	
	 static int[] gcde(int p, int q) {
	        if (q == 0)
	            return new int[] {p, 1, 0 };

	        int[] vals = gcde(q, p % q);
	        int d = vals[0];
	        int a = vals[2];
	        int b = vals[1] - (p / q) * vals[2];
	        return new int[] { d, a, b };
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the first prime number");
		int p=sc.nextInt();
		
		while(!isPrime(p))
		{
			System.out.println("You must enter prime number");
			p=sc.nextInt();
		}
		
		System.out.println("Enter the second prime number");
		int q=sc.nextInt();
		
		while(!isPrime(q))
		{
			System.out.println("You must enter prime number");
			q=sc.nextInt();
		}
		
		int N=p*q;
		int phiOfN=(p-1)*(q-1);
		int e,d;
		
		for(e=5;e<phiOfN;e++)
		{
			if(gcd(phiOfN,e)==1)
			{
				break;
			}
		}
		
		d=gcde(e,phiOfN)[1];
		
		System.out.println("N :" +N);
		System.out.println("Phi of N :" +phiOfN);
		System.out.println("Public Key is : " +e +" ,"+N);
		System.out.println("Private Key is : " +d+" ,"+N);
		
		System.out.println("Enter the Message");
		int M=sc.nextInt();
		int cipher=moduloOf(M,e,N);
		System.out.println("Encrypted message is : "+ cipher);
		
		System.out.println("Decrypted message is : "+ moduloOf(cipher,d,N));
		
		
	}

}

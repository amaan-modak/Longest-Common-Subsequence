import java.io.*;
import java.util.*;

public class LCS_Amaan_Modak_amaanakh
{
	public static void main(String[] args)
	{
		try
		{
			//Scanner in = new Scanner(new File("C:/Users/Aslam/Desktop/UB Courses/CSE 531 - Algorithms/Projects/Project 2/test10.txt")); //Read Input File
			Scanner in = new Scanner(new File("input.txt")); //Read Input File
			String s1 = in.next();   //String number 1
			String s2 = in.next();	//String number 2
			int n = s1.length(); //Length of first string
			int m = s2.length(); //Length of second string
			
			char []a = new char[n+1];
			char []b = new char[m+1];
			
			//Reading each character and storing individually one at a time
			for (int i=0; i<n; i++) 
			{
				a[i+1] = new Character(s1.charAt(i));
			}
			for (int i=0; i<m; i++)
			{
				b[i+1] = new Character(s2.charAt(i));
			}
			
			int [][]opt = new int[n+1][m+1];	//Optimal values
			char [][]f = new char[n+1][m+1];	//Flag values in DP Table

			for(int j=0;j<=m;j++)
				opt[0][j]=0;
			
			for(int i=1;i<=n;i++)
			{
				opt[i][0] = 0;
				for(int j=1;j<=m;j++)
				{
					if(a[i]==b[j])
					{
						opt[i][j] = opt[i-1][j-1]+1;
						f[i][j] = 'd'; //Diagonal
					}
					else if(opt[i][j-1]<opt[i-1][j])
					{
						opt[i][j] = opt[i-1][j];
						f[i][j] = 'u'; //Up
					}
					else
					{
						opt[i][j] = opt[i][j-1];
						f[i][j] = 'l'; //Left
					}
				}
			}
			
			int x = n;
			int y = m;
			StringBuilder lcs = new StringBuilder(); //String to store output LCS
			
			while(x>0 && y>0)
			{
				if(f[x][y]=='d')
				{
					lcs.append(a[x]);
					x--;
					y--;
				}
				else if(f[x][y]=='u')
					x--;
				else
					y--;
			}
			lcs = lcs.reverse(); //Print subsequence in actual order of occurrence by reversing order in which it is stored
			
			//PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/Aslam/Desktop/UB Courses/CSE 531 - Algorithms/Projects/Project 2/out10.txt")),true); //Create Output File
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("output.txt")),true); //Create Output File
			writer.write(new Integer(lcs.length()).toString()); //Print Length of LCS in file
			writer.write(System.getProperty("line.separator")); //Next Line
			writer.append(lcs); //Print subsequence in file at the end
			writer.flush();
			writer.close();	
		
			//System.out.println("Output LCS = "+lcs); //Output String
			//System.out.println("LCS Length = "+lcs.length()); //Length of LCS
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
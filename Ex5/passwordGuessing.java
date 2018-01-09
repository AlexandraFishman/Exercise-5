package ex5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class passwordGuessing {
	
	private String path= "D:/Eclipse Workspace/Ex5/src/ex5/password.txt";
	
	public void readFile(String username, String password){
		// try read from the file
		try {
			boolean ans=false;
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine();

			while(str != null){
				ans= guess(str, password);
				if(ans){
					//System.out.println("str="+str+", password="+password);
					JOptionPane.showMessageDialog(null, "The password is: "+password);
					break;
				}
				str = br.readLine();
			}

			br.close();
			fr.close();
		}
		catch(IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	public boolean guess(String trying, String password){
		if(trying.equals(password)){
			return true;
		}
		return false;
	}

	/*public static void main(String [] args){
		String path= "C:\\Users\\Paz Cheredman\\workspace\\computerNetworks\\src\\ex5\\password.txt";
		String username= "paz";
		String password= "012345";
		readFile(path, username, password);
	}*/

}

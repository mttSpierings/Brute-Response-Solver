//Brute-forcing a password in an HTTP response header.
//Response and nonce is known. Simulating a man-in-the-middle attack.
//Author: Matthew Spierings 27/3/15

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MD5 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s;

		s = new Scanner(new File("C:/Users/Admin/Desktop/CS215/1510_words.txt"));
//			List<String> words = new ArrayList<String>();
		while(s.hasNext()){
			String words = s.next();

			String userandpass = "5288656:Mordor:";

			String userandguesspass = userandpass + words.trim();

			//MD5(METHOD:URI)
			String HA2 = "80e33c06ee081829a3e2988489e9abc6";
			String nonce = "03e2abb8a924e966bee59d41cef32851";

			//------------------------------------------------
			String HA1 = mD5(userandguesspass);
			String toRespond = HA1 +":" + nonce + ":" +  HA2;
			String response = mD5(toRespond);
			//------------------------------------------------

			System.out.println("Checking against ebb5caedabf5a84d73b0e42730090f90");
			System.out.println("Checking...      " + response);


			if("ebb5caedabf5a84d73b0e42730090f90".equals(response)){

				System.out.println("Password is cracked:	" + words);
				break;
			}else{
				System.out.println("	Try again");
			}
			}
			s.close();
	}
	public static String mD5(String message){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for(byte b : digest){
				sb.append(String.format("%02x", b & 0xff));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

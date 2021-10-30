import java.util.Scanner;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.lang.IndexOutOfBoundsException;

public class JavaHashingApplication {
	
	static ArrayList<String> hashValue = new ArrayList<String>(10);
	static ArrayList<String> hashValue2 = new ArrayList<String>(10);
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Welcome to my Hashing Application ");
		System.out.println("----------------------------");
		selection();
	  	arrayComparison();
		
	}
	
	public static void selection() throws Exception {
		System.out.println("1 - For text hashing in MD5 ");
		System.out.println("2 - For text hashing in SHA-1 ");
		System.out.println("3 - For text hashing in SHA-256 ");
		System.out.println("4 - For file hashing in SHA-256 ");
		System.out.println("****************************** ");
		System.out.println("****************************** ");
		System.out.print("Enter Your option: ");
		
		Scanner optionSelect = new Scanner(System.in);
		int number= optionSelect.nextInt(); 
		switch(number){
		  	case 1:
		  		MD5Hash();
		  		break;
		  	case 2:
		  		SHA1Hash();
		  		break;
		  	case 3:
		  		SHA256Hash();
		  		break;
		  	case 4:
		  		FileHashSHA256();
		  		break;
		}
		
	}	
	
	public static void MD5Hash() throws Exception  {
		System.out.print("Enter text you want to hash: ");
		Scanner inputMD5 = new Scanner(System.in);
		String inputstring = inputMD5.next(); 
		System.out.println("You have entered: "+inputstring);
	    
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputstring.getBytes());
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
  	    for (int i=0;i<byteData.length;i++) {
  		    String hex=Integer.toHexString( byteData[i] & 0xff );
 	     	if(hex.length()==1) hexString.append('0');
 	     		hexString.append(hex);
  	    }
  	    System.out.println("----------------------------");
  	    System.out.println("The MD5 hash: " + hexString.toString());  
  	    System.out.println("----------------------------");
  	    System.out.println("");
  	    selection();
	}
	
	public static void SHA1Hash() throws Exception {
		System.out.print("Enter text you want to hash: ");
		Scanner inputSHA1 = new Scanner(System.in);
		String inputString = inputSHA1.next();
		System.out.println("You have entered. "+ inputString);
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
	    md.update(inputString.getBytes());
	    byte byteData[] = md.digest();
	    
	    StringBuffer hexString = new StringBuffer();
	  	for (int i=0;i<byteData.length;i++) {
	  		String hex=Integer.toHexString( byteData[i] & 0xff );
	 	    if(hex.length()==1) hexString.append('0'); 
	 	    	hexString.append(hex);	
	  	}
	  	System.out.println("----------------------------");
	  	System.out.println("The SHA-1 hash: "+ hexString.toString());
	  	System.out.println("----------------------------");
	  	System.out.println("");
  	    selection();
	}
	
	public static void SHA256Hash() throws Exception {
		System.out.print("Enter text you want to hash: ");
		Scanner inputSHA256 = new Scanner(System.in);
		String inputString = inputSHA256.next();
		System.out.println("You have entered. "+ inputString);
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(inputString.getBytes());
	    byte byteData[] = md.digest();
	    
	    StringBuffer hexString = new StringBuffer();
	  	for (int i=0;i<byteData.length;i++) {
	  		String hex=Integer.toHexString( byteData[i] & 0xff );
	 	    if(hex.length()==1) hexString.append('0'); 
	 	    	hexString.append(hex);	
	  	}
	  	System.out.println("----------------------------");
	  	System.out.println("The SHA-256 hash: "+ hexString.toString());
	  	System.out.println("----------------------------");
	  	System.out.println("");
  	    selection();
	}
	
	public static void FileHashSHA256() throws Exception {
		 
		 String currentdir = Paths.get(".").toAbsolutePath().normalize().toString();
		 System.out.println("Would you like to use the default directory? ");
		 System.out.println("Where the default directory is: "+ currentdir);
		 System.out.print("Please enter Y or N: ");
		 Scanner chooseDir = new Scanner(System.in);
		 String c = chooseDir.next();
		 String filepath = "";
		 
		 if(c.equalsIgnoreCase("N")){
			 System.out.print("Enter the custom directory you would like to choose?: ");
			 Scanner customDir = new Scanner(System.in);
			 String dir = customDir.next();
			 System.out.print("Enter the name of the file with its extension (e.g. 123.txt): ");	
		     Scanner inputDir = new Scanner(System.in);	  
		     String inputstring = inputDir.next(); 
			 System.out.println("You have entered: "+inputstring);
			 filepath = dir+"\\"+ inputstring;
		 }else if (c.equalsIgnoreCase("Y")){
			 System.out.print("Enter the name of the file with its extension (e.g. 123.txt): ");	
		     Scanner inputNoDir = new Scanner(System.in);	  
		     String inputstring = inputNoDir.next(); 
			 System.out.println("You have entered: "+inputstring);
			 currentdir = Paths.get(".").toAbsolutePath().normalize().toString(); 
			 filepath = currentdir + "\\" + inputstring;
		 }

		 MessageDigest md = MessageDigest.getInstance("SHA-256");
		 System.out.println("File path is: "+ filepath);
		 FileInputStream fis = new FileInputStream(filepath);
		 
		 byte[] dataBytes = new byte[1024];
	     int nread = 0;
	     while ((nread = fis.read(dataBytes)) != -1) {
	    	 md.update(dataBytes, 0, nread);	//md is the MessageDigest instance
	     }
	     byte byteData[] = md.digest();
	     
	     StringBuffer hexString = new StringBuffer();	//
	  	 for (int i=0;i<byteData.length;i++) {
	  		 String hex=Integer.toHexString( byteData[i] & 0xff );
	  		 if(hex.length()==1) 
	  			 hexString.append('0');
	  		 	 hexString.append(hex);	
	  	 }
	  	 hashValue.add(hexString.toString());
	  	 System.out.println("----------------------------");
	  	 System.out.println("The file Hash is: " + hexString.toString());
	  	 System.out.println("----------------------------");
	  	 
	  	 System.out.print("Would you like to store another hash again? Y/N: ");
	  	 Scanner inputAgain = new Scanner(System.in);
	  	 String again = inputAgain.next();
	  	 if (again.equalsIgnoreCase("Y")){
	  		 FileHashSHA256();
	  	 }else if (again.equalsIgnoreCase("N")) {
	  		 compareFile();
	  	 }else {
	  		System.out.println("Invalid option chosen! ");
	  		System.exit(0);
	  	 }
	  	 
		
	}
	
	public static void compareFile() throws Exception {
		System.out.println("");
		System.out.print("Would you like to compare another file with the existing database? Y/N: ");
		Scanner inputCompare2 = new Scanner(System.in);
		String compare2 = inputCompare2.next();
		if (compare2.equalsIgnoreCase("Y")){
			System.out.println("Loading... ");
			
			String currentdir2 = Paths.get(".").toAbsolutePath().normalize().toString();
			System.out.println("Would you like to use the default directory? ");
			System.out.println("Where the default directory is: "+ currentdir2);
			System.out.print("Please enter Y or N: ");
			Scanner chooseDir2 = new Scanner(System.in);
			String c2 = chooseDir2.next();
			String filepath2 = "";
			
			 if(c2.equalsIgnoreCase("N")){
				 System.out.print("Enter the custom directory you would like to choose?: ");
				 Scanner customDir2 = new Scanner(System.in);
				 String dir2 = customDir2.next();
				 System.out.print("Enter the name of the file with its extension (e.g. 123.txt): ");	
			     Scanner inputDir2 = new Scanner(System.in);	  
			     String inputstring2 = inputDir2.next(); 
				 System.out.println("You have entered: "+inputstring2);
				 filepath2 = dir2+"\\"+ inputstring2;
			 }else if (c2.equalsIgnoreCase("Y")){
				 System.out.print("Enter the name of the file with its extension (e.g. 123.txt): ");	
			     Scanner inputNoDir2 = new Scanner(System.in);	  
			     String inputstring2 = inputNoDir2.next(); 
				 System.out.println("You have entered: "+inputstring2);
				 currentdir2 = Paths.get(".").toAbsolutePath().normalize().toString(); 
				 filepath2 = currentdir2 + "\\" + inputstring2;
			 }
			 
			 MessageDigest md2 = MessageDigest.getInstance("SHA-256");
			 System.out.println("File path is: "+ filepath2);
			 FileInputStream fis2 = new FileInputStream(filepath2);
			 
			 byte[] dataBytes2 = new byte[1024];
		     int nread2 = 0;
		     while ((nread2 = fis2.read(dataBytes2)) != -1) {
		    	 md2.update(dataBytes2, 0, nread2);	//md is the MessageDigest instance
		     }
		     byte byteData2[] = md2.digest();
		     
		     StringBuffer hexString2 = new StringBuffer();	//
		  	 for (int i=0;i<byteData2.length;i++) {
		  		 String hex2=Integer.toHexString( byteData2[i] & 0xff );
		  		 if(hex2.length()==1) 
		  			 hexString2.append('0');
		  		 	 hexString2.append(hex2);	
		  	 }
		  	 hashValue2.add(hexString2.toString());
		  	 System.out.println("----------------------------");
		  	 System.out.println("The file Hash is: " + hexString2.toString());
		  	 System.out.println("----------------------------");
		}else {
			System.out.println("Thanks for hashing...Bye! ");
			System.exit(0);
		}
	}
	
	public static void arrayComparison()  throws Exception {
		try {
		//works with multiple elements. however gives problem of array searching out of bounds.
			for(int i = 0; i<hashValue.size(); i++){
				if(hashValue.contains(hashValue2.get(i))){
					System.out.println("The program has detected an identical hash stored within the system! ");
					System.out.println("Therefore, you have already hashed the same file");
					System.out.println("There exists this hash: "+hashValue2.get(i));
					System.out.println("****************************** ");
					System.out.println("****************************** ");
				} else {
					System.out.println("There was no match for the hash in the system!");
					System.out.println("This hash doesn't exist: "+hashValue2.get(i));
					System.out.println("****************************** ");
					System.out.println("****************************** ");
				}
			}
		} catch (IndexOutOfBoundsException i) {
			System.exit(0);
		}
	}
}

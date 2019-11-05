package edu.neu.csye6200.launch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.BufferedReader;
import java.io.File;

public class RocketIO {

	public static Logger log = Logger.getLogger(RocketIO.class.getName()); // instance of Logger - to log messages
	String logPath = "/Users/apple/Git Repository/Eclipse-Java/KrutikTatibandwale_001316225_Assignment4/src/edu/neu/csye6200/launch/"; // directory location for log file

// array of 5 rocket object is declared
	
	Rocket obj[] = new Rocket[5];
	
	public RocketIO() {
		log.setUseParentHandlers(false); // using this - logs will print only on file and not on console 
		File logDirFile = new File(logPath); 
		
		try {
			if (!logDirFile.exists()) {
				logDirFile.mkdir();
			}
			Handler handler = new FileHandler(logPath + "rocketIO.log"); // create a handler to dump log messages into log file 
			handler.setFormatter(new SimpleFormatter());
			log.addHandler(handler);
		}
		catch(SecurityException | IOException e) {
			e.printStackTrace();
		}
		log.info("RocketIO constructor called...");
	}

// this method save the rocket instance information into the text file 
// I have not created new rocket object in RocketIO class, instead I am loading rocket object from earlier create in RocketTest class 		
// written two exceptions and try/catch block to handle it
	
	private void save(Rocket rocketObj, String fileName) {
				
//		String base = "/Users/apple/Downloads/";
		try {
			FileWriter  writer = new FileWriter(logPath +  fileName);
			writer.write("Name        Manufacture    Rocket Weight   Fuel Weight   Payload Weight   Thrust   Total Rocket Weight    Acceleration  " + "\n");
			writer.write("                               (kg)           (kg)            (kg)         (N)      (at Launch Pad)         (m/s2)     " + "\n");
			writer.write("---------   -------------  -------------   -----------   --------------   -----   --------------------   --------------" + "\n");
			writer.write(rocketObj.toFormattedString() + "\n");
			writer.close();			
		}		
		catch(FileNotFoundException e) {
			System.out.println("File not found: \n");
			e.printStackTrace();
			log.severe("Exception Occurred: File Not Found");
		}
		catch(IOException e) {
			e.printStackTrace();
			log.severe("Exception Occurred: IO Exception");
		}
		log.info("Rocket Instance saved on file successfully");
	}
	
	public void save(Rocket rocketInstances[], String fileName) {
		
		save(rocketInstances[0], "Write-Output-New.txt");

//		String base = "/Users/apple/Downloads/";
		try {
			FileWriter  writer = new FileWriter(logPath +  fileName);
			writer.write("Name        Manufacture    Rocket Weight   Fuel Weight   Payload Weight   Thrust   Total Rocket Weight    Acceleration  " + "\n");
			writer.write("                               (kg)           (kg)            (kg)         (N)       (at Launch Pad)         (m/s2)     " + "\n");
			writer.write("---------   -------------  -------------   -----------   --------------   ------   --------------------   --------------" + "\n");
			for(Rocket instance : rocketInstances) {
				writer.write(instance.toFormattedString() + "\n");				
			}
			writer.close();			
		}		
		catch(FileNotFoundException e) {
			System.out.println("File not found: \n");
			e.printStackTrace();
			log.severe("Exception Occurred: File Not Found");
		}
		catch(IOException e) {
			e.printStackTrace();
			log.severe("Exception Occurred: IO Exception");
		}
		log.info("List of rocket instances saved on file successfully");
	}

// this method reads the text file (create from save method) and create instances of rocket using the information present in the text file	
// written two exceptions and try/catch block to handle it
	
	public void load(String fileName) {
		String base = "/Users/apple/Downloads/";		
// used BufferedReader to read text file line-by-line		
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(base +  fileName));
// to skip first three line			
			System.out.println(buffReader.readLine());
			System.out.println(buffReader.readLine());
			System.out.println(buffReader.readLine());
// capturing each line into the string "line"			
			String line = buffReader.readLine();
			int i=0;
			while(line!=null) {				
// splitted the string and stored into staring array				
				String strList[] = line.split("\\s+");
// in while loop - creating new object every time till the loop runs and assigning the parameters to the object
// using string array				
				obj[i] = new Rocket(strList[0], strList[1], Double.parseDouble(strList[2]), 
						Double.parseDouble(strList[3]), Double.parseDouble(strList[4]), 
						Integer.parseInt(strList[5]));
				System.out.println(obj[i].toFormattedString());
				i++;
				line = buffReader.readLine();
			}
			buffReader.close();
			log.info("Rocket instances created successfully via reading rocket objets from file");
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found: \n");
			e.printStackTrace();
			log.severe("Exception Occurred: File Not Found");
		}
		catch(IOException e) {
			e.printStackTrace();
			log.severe("Exception Occurred: IO Exception");
		}
	}
}
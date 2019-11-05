// @author - Krutik Tatibandwale (NUID: 001316223)

// Rocket Program

package edu.neu.csye6200.launch;

import java.util.Date;

public class RocketTest {

// declaring array list for rocket objects
	
	static Rocket rocketList[];

// default constructor
	
	public RocketTest() {
		rocketList = new Rocket[5];
		loadRocketList();
	}

// list of 5 rocket objects
	
	public void loadRocketList() {
		rocketList[0] = new Rocket("Starship", "SpaceX", 50, 20, 30, 9000);
		rocketList[1] = new Rocket("Dragon2", "SpaceX", 2000, 300, 400, 60000);
		rocketList[2] = new Rocket("CST-100", "Boeing", 3000, 400, 200, 700000);
		rocketList[3] = new Rocket("Bionic", "BlueOrigin", 4000, 600, 300, 80000);
		rocketList[4] = new Rocket("Cygnus ", "Orbital", 5000, 600, 100, 90000);
	}

// method to print information of rocket
	
	public void printRocketList() {
		System.out.println("Name        Manufacture    Rocket Weight   Fuel Weight   Payload Weight    Thrust    Total Rocket Weight    Acceleration ");
		System.out.println("                               (kg)           (kg)            (kg)          (N)       (at Launch Pad)        (m/s2)      ");
		System.out.println("---------   -------------  -------------   -----------   --------------   -------   --------------------   --------------");
		for(Rocket rocket : rocketList) {
			System.out.println(rocket.toFormattedString());
		}
	}

// this method is used to to create instances of rocket and alter variables of it  
	
	public static void runRocket() {
		RocketTest rocketTest = new RocketTest();
		rocketTest.printRocketList();
		System.out.println("\n\n" + rocketList[0].getName() + " total weight at launch pad: " + rocketList[0].getTotalRocketWt() + "\n");
				
		rocketList[0].setRocketWeight(1500);
		System.out.println("\n\n\n\n Chaged Startship rocket weight using setter and thus total weight & thrust will also gets changed: \n\n" );
		rocketTest.printRocketList();
		
		rocketList[1].setFuelWeight(500);
		System.out.println("\n\n\n\n Chaged Dragon2 fuel weight using setter and thus total weight & thrust will also gets changed: \n\n" );
		rocketTest.printRocketList();
		
		rocketList[3].setPayloadWeight(400);
		System.out.println("\n\n\n\n Chaged Bionic payload weight using setter and thus total weight & thrust will also gets changed: \n\n" );
		rocketTest.printRocketList();
	}

// this method is used to instances of rocket scheduler and perform add/remove launch event operations
	
	public static void runScheduler() {		
//		LaunchScheduler scheduler = new LaunchScheduler();
		
		LaunchScheduler scheduler = LaunchScheduler.getInstance(); // static method called for Singleton Pattern

		scheduler.addLaunchEvent(new LaunchEvent(5, "STS-12",new Date(12,15,2019), new Rocket("StarWar", "Nintando", 1000, 200, 300, 50000)));
		scheduler.addLaunchEvent(new LaunchEvent(3, "ART-15",new Date(12,15,2019), new Rocket("Jupyter", "Solar", 1500, 300, 275, 20000)));
		scheduler.addLaunchEvent(new LaunchEvent(2, "NEWS-345",new Date(12,15,2019), new Rocket("Neptune", "Tesla", 2000, 350, 500, 30000)));
		scheduler.addLaunchEvent(new LaunchEvent(7, "AIR-01",new Date(12,15,2019), new Rocket("Mars", "Boeing", 2500, 100, 120, 10000)));
		scheduler.addLaunchEvent(new LaunchEvent(9, "BOND-007",new Date(12,15,2019), new Rocket("Saturn", "AirBus", 2750, 150, 160, 15000)));
		scheduler.addLaunchEvent(new LaunchEvent(6, "Arpanet",new Date(12,15,2019), new Rocket("Pluto", "SpaceX", 3000, 200, 180, 20000)));
		scheduler.addLaunchEvent(new LaunchEvent(4, "CAP-23",new Date(12,15,2019), new Rocket("Mercury", "Orbital", 1200, 410, 230, 20000)));
		scheduler.addLaunchEvent(new LaunchEvent(8, "MSN-786",new Date(12,15,2019), new Rocket("Venus", "Orbex", 1400, 475, 300, 15000)));
		scheduler.addLaunchEvent(new LaunchEvent(1, "BlackJack",new Date(12,15,2019), new Rocket("Uranus", "Spaceship", 1800, 450, 250, 10000)));
		scheduler.addLaunchEvent(new LaunchEvent(10, "Astro",new Date(12,15,2019), new Rocket("Moon", "Nevada", 2300, 350, 400, 300000)));

		scheduler.sortArrList(); // this method call will sort the arraylist based on Id's

		scheduler.getLaunchEventArray(0);
		System.out.println("\n");
		scheduler.getLaunchEventMap(3);
		System.out.println("\n");
		scheduler.printLaunchEvent();
		
		
		scheduler.removeLaunchEvent(1);
		System.out.println("\n");		
		scheduler.printLaunchEvent();	
	}
	
// this method is used to create stages of MultiStage Rocket and determine the stages present in given time 
	
	public static void runRocketStages() {
		MSRocket secondStage = new MSRocket("Dragon2", "SpaceX", 2000, 300, 400, 6000, null, null);
		MSRocket thirdStage = new MSRocket("CST-100", "Boeing", 3000, 400, 200, 7000, null, null);
		MSRocket bottomStage = new MSRocket("Starship", "SpaceX", 1000, 200, 300, 5000, secondStage, thirdStage);
		
		
		bottomStage.calHeight(10); // this method pass time as parameter to calculate height
		System.out.println("\n");
		bottomStage.calHeight(40);
		System.out.println("\n");
		bottomStage.calHeight(70);
		
	}

// this method is used to write back  the rocket instance into a text file and also read the same file to create another set of rocket 	
// instances using the earlier rocket information (variables)
	
	public static void runReadWrite() {

// here, the idea is to utilize the earlier created rocket object given in assignment 2a to write the same rocket info into the text file 
// I have not created new rocket object in RocketIO class, instead I am loading object rocket earlier create in RocketTest class 		
		
		RocketTest loadRocket = new RocketTest(); 

		RocketIO rio = new RocketIO();
		rio.save(rocketList, "Write-Output.txt"); // this write the data into the text file
		
//		rio.load("Write-Output.txt"); // this reads the data from the text file to create new rocket objects
	}
	
// this method will call simulation class to get the height of rocket (from ground) at given point of time	
	
	public static void runSimulation() {

		RocketTest obj = new RocketTest();
		RocketSimulation sim = new RocketSimulation(rocketList[0]);
		sim.calRocketHeigh(4); // pass rocket object and time (from launch)

	}

// main method
	
	public static void main(String[] args) {
		
//		runRocket();		
//		System.out.println("\n");
//		runScheduler();
//		System.out.println("\n");
//		runRocketStages();
//		System.out.println("\n");	
//		runReadWrite();
//		System.out.println("\n");	
//		runSimulation();
		
	}

}

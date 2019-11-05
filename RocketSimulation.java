package edu.neu.csye6200.launch;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RocketSimulation {
	private double time;
	private double fuelTime;
	Rocket obj;
	
	public static Logger log = Logger.getLogger(RocketSimulation.class.getName()); // instance of Logger - to log messages
	String logPath = "/Users/apple/Git Repository/Eclipse-Java/KrutikTatibandwale_001316225_Assignment4/src/edu/neu/csye6200/launch/"; // directory location for log file

// constructor to initialize rocket object and set relates variable and logger	
	
	public RocketSimulation(Rocket obj) {
		time = 0;
		fuelTime = obj.getFuelWeight() / obj.FUEL_BURN_RATE; //	calculate the total time in which the fuel gets empty 
		this.obj = obj;
		
		log.setUseParentHandlers(false); // using this - logs will print only on file and not on console 
		File logDirFile = new File(logPath); 
		
		try {
			if (!logDirFile.exists()) {
				logDirFile.mkdir();
			}
			Handler handler = new FileHandler(logPath + "rocketSimulation.log"); // create a handler to dump log messages into log file 
			handler.setFormatter(new SimpleFormatter());
			log.addHandler(handler);
		}
		catch(SecurityException | IOException e) {
			e.printStackTrace();
		}
		log.info("Rocket Simulaton constructor called...");

	}

// this method is used to calculate the height of rocket from the ground at given point of time
// maintaining a time counter which increments by 1 sec
	
	void calRocketHeigh(int t) {
		
		log.info("Initial Fuel Weight: " + obj.getFuelWeight());
		log.info("Total Fuel Exaust (in time): " + fuelTime);
		log.info("Launch Time: " + time);
		log.info("Initial Rocket Weight: " + obj.getTotalRocketWt());
		log.info("Initial Acceleration: " + obj.getAcceleration());
		log.info("Initial Velocity: " + obj.getVelocity());
		log.info("Initial Height of Rocket (from launch pad): " + obj.getHeight());

// this loop will run till the rocket fuel exhaust completely		
// below are some calculation to adjust/change rocket fuel, acceleration and velocity
// based on these variable height gets calculated		
		
		while(fuelTime!=0) {

			if(time!=t) {
				
				obj.setFuelWeight(obj.getFuelWeight() - obj.FUEL_BURN_RATE);
				log.info("Set Fuel Weight: " + obj.getFuelWeight());

				obj.setTotalRocketWt(obj.calStageWeight());
				log.info("Reduced Rocket Weight: " + obj.getTotalRocketWt());
				
				obj.setAcceleration(obj.calAcceleration());
				log.info("Increase Acceleration (due to weight decreament): " + obj.getAcceleration());

				obj.setAcceleration(obj.getAcceleration()- obj.GRAVITY);
				log.info("Upward Acceleration (minus gravititional force): " + obj.getAcceleration());
				
				obj.setVelocity(obj.getVelocity() + (obj.getAcceleration() * obj.DELTA_TIME));
				log.info("Cumulative Velocity: " + obj.getVelocity());

				obj.setHeight(obj.getHeight() + (obj.getVelocity() * obj.DELTA_TIME));
				log.info("Increased Height of Rocket (from launch pad): " + obj.getHeight());

				fuelTime = obj.getFuelWeight() / obj.FUEL_BURN_RATE;
				log.info("Remaining Fuel Exaust (in time): " + fuelTime);

				time += obj.DELTA_TIME;
				log.info("Counter Launch Time: " + time);

			}
			else {
				System.out.println("At time " + time + " sec from the launch the rocket will reach at height: " + obj.getHeight());
				return;
			}			
		}		
//		obj.DELTA_TIME = .05;

// this loop will start once the fuel over / engine stops - still the rocket will have velocity
// this loop will run till the loop velocity will become zero
// at the end on this loop rocket will reach the maximum altitude		
		
		while(obj.getVelocity()>0) {

			log.info("Cumulative Velocity: " + obj.getVelocity());
			log.info("Increased Height of Rocket (from launch pad): " + obj.getHeight());

			if(time!=t) {
				obj.setVelocity(obj.getVelocity() - obj.GRAVITY * obj.DELTA_TIME);
				obj.setHeight(obj.getHeight() + (obj.getVelocity() * obj.DELTA_TIME));
				time += obj.DELTA_TIME;				
			}
			else {
				System.out.println("At time " + time + " sec from the launch the rocket will reach at height: " + obj.getHeight());
				return;
			}
		}

// once the rocket reaches the maximum altitude (when velocity becomes zero), it will now starts falling back
// to the ground. this loop will run till rocket reach the ground
		
		while(obj.getHeight()!=0) {

			log.info("Cumulative Velocity: " + obj.getVelocity());
			log.info("Increased Height of Rocket (from launch pad): " + obj.getHeight());

			if(time!=t) {
				obj.setHeight(obj.getHeight()-(obj.GRAVITY * obj.DELTA_TIME));		
				time += obj.DELTA_TIME;				 
			}
			else {
				System.out.println("At time " + time + " sec from the launch the rocket will reach at height: " + obj.getHeight());
				return;
			}
		}
	}
}

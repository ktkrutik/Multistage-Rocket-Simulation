// @author - Krutik Tatibandwale (NUID: 001316223)

// Rocket Program


package edu.neu.csye6200.launch;

public class Rocket {

// declaring properties of rocket globally
	
	private String name;
	private String manufacture;
	private double rocketWeight;
	private double fuelWeight;
	private double payloadWeight;
	private double acceleration;
	private double totalRocketWt;
	private double force;
	private double height; // in meters
	private double velocity;
	static final double GRAVITY = 9.8; //(m/sec^2)
	static final double FUEL_BURN_RATE = 1; //(kg/sec)
	static double DELTA_TIME = 1; //(sec)
		
// parameterized constructor for rocket
	
	public Rocket(String name, String manufacture, double rocketWeight, double fuelWeight,
			double payloadWeight, int force) {
		this.name = name;
		this.manufacture = manufacture;
		this.rocketWeight = rocketWeight;
		this.fuelWeight = fuelWeight;
		this.payloadWeight = payloadWeight;
		this.force = force;
		this.height = 0;
		this.velocity = 0;
		this.totalRocketWt = this.rocketWeight + this.fuelWeight + this.payloadWeight;
		this.acceleration = this.force/this.totalRocketWt;
	
//		this.setTotalRocketWt(this.rocketWeight + this.fuelWeight + this.payloadWeight);
//		this.setAcceleration(this.force/this.getTotalRocketWt());
	}
	
// below are getter & setter for properties of rocket
	
	public void setName(String name) {
		this.name = name; 
	}
	public String getName() {
		return this.name; 
	}
	
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture; 
	}
	public String getManufacture() {
		return this.manufacture; 
	}
	
	public void setRocketWeight(double rocketWeight) {
		this.rocketWeight = rocketWeight; 
	}
	public double getRocketWeight() {
		return this.rocketWeight; 
	}
	
	public void setFuelWeight(double fuelWeight) {
		this.fuelWeight = fuelWeight; 
	}
	public double getFuelWeight() {
		return this.fuelWeight; 
	}
	
	public void setPayloadWeight(double payloadWeight) {
		this.payloadWeight = payloadWeight; 
	}
	public double getPayloadWeight() {
		return this.payloadWeight; 
	}
	
	public void setForce(double force) {
		this.force = force; 
	}
	public double getForce() {
		return this.force; 
	}

	public double getTotalRocketWt() {
		return totalRocketWt;
	}

	public void setTotalRocketWt(double totalRocketWt) {
		this.totalRocketWt = totalRocketWt;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

// method to calculate total weight of rocket
	
	public double calStageWeight() {
		setTotalRocketWt(this.rocketWeight + this.fuelWeight + this.payloadWeight);
		return getTotalRocketWt();
	}

//	method to calculate thrust for rocket (mass * acceleration)
	public double calAcceleration() {
		return this.force / this.getTotalRocketWt();
	}

// string formatting for better display	
	
	public String toFormattedString() {
		return String.format("%1$-10s %2$-14s %3$-16g %4$-13g %5$-17g %6$-10g %7$-20s %8$-20S", 
				name, manufacture, rocketWeight, fuelWeight, payloadWeight, force, calStageWeight(), calAcceleration());
	}
}

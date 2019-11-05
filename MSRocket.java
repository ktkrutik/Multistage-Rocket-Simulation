package edu.neu.csye6200.launch;

public class MSRocket extends Rocket{
	
// defined stages of rocket
	
	private MSRocket stageTwo;
	private MSRocket stageThree;
	final int FORCE = 100; // this is simply defined as rocket upward force 


// constructor from super class
	
	public MSRocket(String name, String manufacture, double rocketWeight, double fuelWeight, double payloadWeight,
			int thrust, MSRocket stageTwo, MSRocket stageThree) {
		super(name, manufacture, rocketWeight, fuelWeight, payloadWeight, thrust);
		this.stageTwo = stageTwo;
		this.stageThree = stageThree;
	}	
	
//	getter & setters for this class
	
	public MSRocket getStageTwo() {
		return stageTwo;
	}

	public void setStageTwo(MSRocket stageTwo) {
		this.stageTwo = stageTwo;
	}

	public MSRocket getStageThree() {
		return stageThree;
	}

	public void setStageThree(MSRocket stageThree) {
		this.stageThree = stageThree;
	}

// method to calculate the combined weight of the MultiStage Rocket at different height from ground
	
	public double calRocketCombinedWeight() {
		double baseWeight = calStageWeight();
		if(this.stageTwo!=null) baseWeight += this.stageTwo.calStageWeight();
		if(this.stageThree!=null) baseWeight += this.stageThree.calStageWeight();
		return baseWeight;
	}
	
// method to calculate the combined thrust of the MultiStage Rocket at different height from ground
	
	public double calRocketCombinedThrust() {
		double baseWeight = calStageWeight();
		double baseThrust = this.getForce();
//		double baseThrust = calAcceleration();
		if(this.stageTwo!=null) {
			baseWeight += this.stageTwo.calStageWeight();
			baseThrust += this.stageTwo.getForce();
//			baseThrust += this.stageTwo.calAcceleration();
		}			
		if(this.stageThree!=null) {
			baseWeight += this.stageThree.calStageWeight();
			baseThrust += this.stageThree.getForce();
//			baseThrust += this.stageThree.calAcceleration();
		}
		return baseThrust;
	}

// method to compute height of the MultiStage Rocket from ground 
// time is passed as parameter here to compute height
	
	public void calHeight(int time) {
		int height = ((FORCE * time)/10);
		trackStages(height, time); 
	}
	
// method to print the MultiStage Rocket information
	
	public void printStages(int stages, int time) {
		System.out.println("######################################################################################################################################################################");
		System.out.println();
		System.out.println("Its been '" + time + "' hours since the rocket launched from United States");
		System.out.println(stages + " Stages of Rocket available !!! \n");
		System.out.println("MultiStage Rocket Current Weight: " + calRocketCombinedWeight());
		System.out.println("MultiStage Rocket Current Thrust: " + calRocketCombinedThrust());
		System.out.println("\n");
		System.out.println("The " + stages + " defined stages of rocket are displayed below: \n");
		System.out.println(this.toNewFormattedString());
		System.out.println();
		System.out.println("######################################################################################################################################################################");
	}

// this method keeps track of number of stages w.r.t height

// when the height of MultiStage Rocket from ground is between 0-300 all stages are present
	public void trackStages(int ht, int time){
		
		if(ht>=0 && ht<= 300) {
			int stages = 3; 
			printStages(stages, time);
		}

// when the height of MultiStage Rocket from ground is between 300-600, we drop stage three. So stage one and two are present
		
		else if(ht>300 && ht<=600) {
			this.stageThree = null;
			int stages = 2; 
			printStages(stages, time);
		}

// when the height of MultiStage Rocket from ground is between 600-1000, we drop one more stage i.e. stage three. So only one stage is present
		
		else if(ht>600 && ht<=1000) {
			this.stageTwo = this.stageThree = null;
			int stages = 1; 
			printStages(stages, time);
		}
		
		else
			System.out.println("Invalid height");
	}
	
// string formatting for better display of MultiStage Rocket information	

	public String toNewFormattedString() {
		System.out.println("Name        Manufacture    Rocket Weight   Fuel Weight   Payload Weight   Thrust   Total Stage Weight    Acceleration   ");
		System.out.println("                               (kg)           (kg)            (kg)          (N)      (at Launch Pad)        (m/s2)      ");
		System.out.println("---------   -------------  -------------   -----------   --------------   ------   --------------------   --------------");
		
		if(this.stageThree==null && this.stageTwo!=null)  {
			return String.format("%1$s %2$s%3$-40s", this.toFormattedString(), "\n", stageTwo.toFormattedString());
		}
		if(this.stageTwo==null && this.stageThree==null) {
			return String.format("%1$-40s", this.toFormattedString());
		}
		return String.format("%1$s %2$s%3$-40s %4$s%5$-40s", this.toFormattedString(), "\n", stageTwo.toFormattedString(), 
				"\n", stageThree.toFormattedString());		
	}
	
}

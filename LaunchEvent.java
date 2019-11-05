// @author - Krutik Tatibandwale (NUID: 001316223)

// Launch Event

package edu.neu.csye6200.launch;

import java.util.Date;

//declaring properties of launch event 

public class LaunchEvent {
	
	private int id;
	private String missionName;
	private Date launchDate;
	private Rocket spaceShuttle;

// parameterized constructor for launch event
	
	public LaunchEvent(int id, String missionName, Date launchDate, Rocket spaceShuttle) {
		this.id = id;
		this.missionName = missionName;
		this.launchDate = launchDate;
		this.spaceShuttle = spaceShuttle;
	}

// below are getters & setters for launch event members
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Rocket getSpaceShuttle() {
		return spaceShuttle;
	}

	public void setSpaceShuttle(Rocket spaceShuttle) {
		this.spaceShuttle = spaceShuttle;
	}

// string formatting for better display	
	
	public String toFormattedString() {
		return String.format("%1$-11d %2$-14s %3$-15tF %4$-20s", 
				id, missionName, launchDate, spaceShuttle.toFormattedString());
	}

	
}

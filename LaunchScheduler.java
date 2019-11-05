// @author - Krutik Tatibandwale (NUID: 001316223)

// Launch Scheduler

package edu.neu.csye6200.launch;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

public class LaunchScheduler {

// creating array list and hash map to store instances of launch events 

	private ArrayList<LaunchEvent> leList = new ArrayList<LaunchEvent>();
	private HashMap<Integer, LaunchEvent> leMap = new HashMap<Integer, LaunchEvent>();
	private static LaunchScheduler instance = null; // Static variable defined for Singleton Pattern

	private static Logger log = Logger.getLogger(LaunchScheduler.class.getName()); // instance of Logger - to log messages
	String logPath = "/Users/apple/Git Repository/Eclipse-Java/KrutikTatibandwale_001316225_Assignment4/src/edu/neu/csye6200/launch/"; // directory location for log file

	private LaunchScheduler() { // private constructor defined for Singleton Pattern
		log.setUseParentHandlers(false); // using this - logs will print only on file and not on console 
		File logDirFile = new File(logPath); 
		
		try {
			if (!logDirFile.exists()) {
				logDirFile.mkdir();
			}
			Handler handler = new FileHandler(logPath + "launchScheduler.log"); // create a handler to dump log messages into log file 
//			Logger.getLogger("log").addHandler(handler);
			handler.setFormatter(new SimpleFormatter());
			log.addHandler(handler);
		}
		catch(SecurityException | IOException e) {
			e.printStackTrace();
		}
		log.info("LaunchScheduler constructor called...");		
	}

// Static method defined for Singleton Pattern - to initialize only one object
	
	public static LaunchScheduler getInstance() {
		if (instance == null) {
			instance = new LaunchScheduler();
		}
		log.info("Singleton design patter initiated...");
		return instance;
	}

// below are getters and setters for array list and hash map

	public ArrayList<LaunchEvent> getLeList() {
		return leList;
	}

	public void setLeList(ArrayList<LaunchEvent> leList) {
		this.leList = leList;
	}

	public HashMap<Integer, LaunchEvent> getLeMap() {
		return leMap;
	}

	public void setLeMap(HashMap<Integer, LaunchEvent> leMap) {
		this.leMap = leMap;
	}

// common method to add launch events in both array list and hash map to maintain synchronization

	public void addLaunchEvent(LaunchEvent addEvent) {
		leList.add(addEvent);
		leMap.put(addEvent.getId(), addEvent);
		log.info("added event into both array list and hash map having id: " + addEvent.getId());;
	}

// this method in defined to sort the arraylist based on Id's
// sorting is done based on Quick Sort	
// this method get only id's from leList and copy into new leId[]
// the sorting method is using leId[] as input and return back the sorted id's
// create a new array list and storing the event object into this based of sorted id's
// then copying the sorted array list into the old array list i.e. leList	
	
	public void sortArrList() {
		log.info("sort array list function called");
		System.out.println("Before Sorting: \n");
		printLaunchEvent();

		int i = 0;
		int leId[] = new int[this.leList.size()];
		int sortedLeId[] = new int[leId.length];
		ArrayList<LaunchEvent> sortedLeList = new ArrayList<LaunchEvent>();
		for (LaunchEvent id : this.leList) {
			leId[i] = id.getId();
			i++;
		}
//		System.out.println(Arrays.toString(leId));
		sortedLeId = qSort(leId);
//		System.out.println(Arrays.toString(sortedLeId));
		for (int j = 0; j < sortedLeId.length; j++) {
			int idToFind = sortedLeId[j];
			// this is storing events object into the new array list based on the sorted id's	
			sortedLeList.add(this.leList.stream().filter(x -> x.getId() == idToFind).findFirst().get());
		}
		this.leList = sortedLeList;

		System.out.println("After Sorting: \n");
		printLaunchEvent();
	}

// this method actually perform quick sort and send the result back to sortArrList for other manipulation & operations
	
	public int[] qSort(int arr[]) {
		int left = 0;
		int right = arr.length - 1;
		return qs(arr, left, right);
	}

	public int[] qs(int arr[], int left, int right) {
		int i = left;
		int j = right;
		int mid = arr[(left + right) / 2];

		do {
			while (arr[i] < mid && i < right)
				i++;
			while (mid < arr[j] && j > left)
				j--;

			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);

		if (left < j)
			qs(arr, left, j);
		if (i < right)
			qs(arr, i, right);

		return arr;
	}

// common method to remove launch events from both array list and hash map to maintain synchronization

	public void removeLaunchEvent(int removeEvent) {		
		int leId = leList.get(removeEvent).getId();
		log.info("removed event from both array list and hash map having id: " + leId);
		leList.remove(removeEvent);
		leMap.remove(leId);
	}

// method to retrieve and print a launch event instance from array list by array index

	public void getLaunchEventArray(int index) {

		System.out.println(
				"######################################################################################################################################################################");
		System.out.println();
		System.out.println("Launch Events in Array List for position " + index + ": \n");
		System.out.println(
				"Launch Id   Mission Name   Launch Date     Name       Manufacture    Rocket Weight    Fuel Weight   Payload Weight    Thrust   Total Rocket Weight   Acceleraton  ");
		System.out.println(
				"                                                                         (kg)            (kg)            (kg)           (N)      (at Launch Pad)        (m/s2)    ");
		System.out.println(
				"---------   ------------   -------------   --------   ------------   --------------   -----------   ----------------  -------   -------------------   ------------");
		System.out.println(getLeList().get(index).toFormattedString());
		System.out.println();
		System.out.println(
				"######################################################################################################################################################################");

	}

// method to retrieve and print a launch event instance from hash map by key

	public void getLaunchEventMap(int id) {

		System.out.println(
				"######################################################################################################################################################################");
		System.out.println();
		System.out.println("Launch Events in Hash Map for key " + id + ": \n");
		System.out.println(
				"Launch Id   Mission Name   Launch Date     Name       Manufacture    Rocket Weight    Fuel Weight   Payload Weight    Thrust   Total Rocket Weight   Acceleration  ");
		System.out.println(
				"                                                                         (kg)            (kg)            (kg)           (N)      (at Launch Pad)        (m/s2)     ");
		System.out.println(
				"---------   ------------   -------------   --------   ------------   --------------   -----------   ----------------  ------   -------------------   --------------");
		System.out.println(leMap.get(id).toFormattedString());
		System.out.println();
		System.out.println(
				"######################################################################################################################################################################");

	}

// common method to print entire launch events from both array list and hash map

	public void printLaunchEvent() {
		System.out.println(
				"######################################################################################################################################################################");
		System.out.println();
		System.out.println("Launch Events in Array List:\n");
		System.out.println(
				"Launch Id   Mission Name   Launch Date     Name       Manufacture    Rocket Weight    Fuel Weight   Payload Weight    Thrust   Total Rocket Weight   Acceleration  ");
		System.out.println(
				"                                                                         (kg)            (kg)            (kg)           (N)      (at Launch Pad)          (m/s2)   ");
		System.out.println(
				"---------   ------------   -------------   --------   ------------   --------------   -----------   ----------------  ------   -------------------   --------------");

		for (LaunchEvent newLE : leList) {
			System.out.println(newLE.toFormattedString());
		}
		System.out.println();
		System.out.println(
				"######################################################################################################################################################################");
		System.out.println();
		System.out.println("Launch Events in Hash Map:\n");
		System.out.println(
				"Launch Id   Mission Name   Launch Date     Name       Manufacture    Rocket Weight    Fuel Weight   Payload Weight    Thrust   Total Rocket Weight   Acceleration  ");
		System.out.println(
				"                                                                         (kg)            (kg)            (kg)           (N)      (at Launch Pad)         (m/s2)    ");
		System.out.println(
				"---------   ------------   -------------   --------   ------------   --------------   -----------   ----------------  ------   -------------------   --------------");
		for (Integer key : leMap.keySet()) {
//			System.out.println("key: " + key);
			System.out.println(leMap.get(key).toFormattedString());

//			System.out.println(leMap.get(key).toFormattedString());
		}
//		leMap.forEach((k,v) -> System.out.println(v.toFormattedString()));
		System.out.println();
		System.out.println(
				"######################################################################################################################################################################");

	}

}

package com.hotelautomation.model;

/**
 * POJO representing hotel map.
 * 
 * @author divakar.patil
 *
 */
public class Hotel {

	private int numberOfFloors;
	private int mainCorridorsPerFloor;
	private int subCorridorsPerFloor;

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public int getMainCorridorsPerFloor() {
		return mainCorridorsPerFloor;
	}

	public void setMainCorridorsPerFloor(int mainCorridorsPerFloor) {
		this.mainCorridorsPerFloor = mainCorridorsPerFloor;
	}

	public int getSubCorridorsPerFloor() {
		return subCorridorsPerFloor;
	}

	public void setSubCorridorsPerFloor(int subCorridorsPerFloor) {
		this.subCorridorsPerFloor = subCorridorsPerFloor;
	}
}

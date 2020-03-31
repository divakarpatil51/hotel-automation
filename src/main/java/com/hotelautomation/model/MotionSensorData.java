package com.hotelautomation.model;

public class MotionSensorData {

	private boolean movement;
	private int floorNumber;
	private int corridorNumber;
	private long noMotionTimeSpan;

	public long getNoMotionTimeSpan() {
		return noMotionTimeSpan;
	}

	public void setNoMotionTimeSpan(long noMotionTimeSpan) {
		this.noMotionTimeSpan = noMotionTimeSpan;
	}

	public boolean isMovementDetected() {
		return movement;
	}

	public void setMovement(boolean movement) {
		this.movement = movement;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getCorridorNumber() {
		return corridorNumber;
	}

	public void setCorridorNumber(int corridorNumber) {
		this.corridorNumber = corridorNumber;
	}

	@Override
	public String toString() {
		return "Movement=" + movement + "\nfloorNumber=" + floorNumber + "\ncorridorNumber=" + corridorNumber
				+ "\ntimeSpan=" + noMotionTimeSpan;
	}
}

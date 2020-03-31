package com.hotelautomation.controller;

import java.util.List;

import com.hotelautomation.floor.Floor;
import com.hotelautomation.model.MotionSensorData;

/**
 * This class controls the equipments present for corridors present on a floor.
 * 
 * @author divakar.patil
 *
 */
public class Controller {

	private List<Floor> floors;
	private static final long NO_MOTION_TIME_THRESHOLD = 60;

	public Controller(List<Floor> floors) {
		this.floors = floors;
	}

	/**
	 * Updates the status of equipments present on the floor as per motion sensor
	 * data.
	 * 
	 * @param motionSensorData - motion related data.
	 */
	public void updateFloorEquipmentStatus(MotionSensorData motionSensorData) {
		Floor floor = floors.get(motionSensorData.getFloorNumber() - 1);
		if (motionSensorData.isMovementDetected()) {
			floor.motionDetectedForCorridor(motionSensorData.getCorridorNumber());
		} else if (motionSensorData.getNoMotionTimeSpan() >= NO_MOTION_TIME_THRESHOLD) {
			floor.resetCorridorEquipmentState();
		}
	}

	/**
	 * Minimizes power consumption for the given floor.
	 * 
	 * @param floorNumber - floor for which power consumption is to be minimized
	 */
	public void minimizePowerConsumptionForFloor(int floorNumber) {
		Floor floor = floors.get(floorNumber - 1);
		floor.minimizePowerConsumption();
	}

}

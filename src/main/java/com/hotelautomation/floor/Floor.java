package com.hotelautomation.floor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hotelautomation.corridor.Corridor;

/**
 * This class deals with Floor related data.
 * 
 * @author divakar.patil
 *
 */
public class Floor {

	private int floorNumber;
	private List<Corridor> corridors;
	private List<Corridor> controllableCorridors;

	public List<Corridor> getCorridors() {
		if (corridors == null) {
			corridors = new ArrayList<>();
		}
		return corridors;
	}

	public void setCorridors(List<Corridor> corridors) {
		this.corridors = corridors;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	/**
	 * Returns list of corridors for which we can control electric equipments.
	 * 
	 * @return controllable corridors.
	 */
	private List<Corridor> getControllableCorridors() {
		if (controllableCorridors == null) {
			controllableCorridors = corridors.stream()
					.filter(corridor -> corridor.getCorridorType().areEquipmentsControllable())
					.collect(Collectors.toList());
		}
		return controllableCorridors;
	}

	/**
	 * Method to check whether power is over consumed on the floor.
	 * 
	 * @return true if power is over consumed.
	 */
	public boolean isOverPowerConsumed() {
		return getCurrentPowerConsumption() > getMaxAllowedPowerConsumption();
	}

	/**
	 * This method calculates the maximum allowed power consumption for the floor.
	 * 
	 * @return maximum allowed power consumption for the floor.
	 */
	public int getMaxAllowedPowerConsumption() {
		return corridors.stream().mapToInt(corridor -> corridor.getCorridorType().getMaxAllowedPowerConsumption())
				.sum();
	}

	/**
	 * Method to calculate the current power consumption on the floor.
	 * 
	 * @return current power consumption on the floor.
	 * 
	 */
	private int getCurrentPowerConsumption() {
		return corridors.stream().mapToInt(Corridor::getCurrentPowerConsumption).sum();
	}

	/**
	 * Ensures that power consumption is minimized if the power is over consumed.
	 */
	public void minimizePowerConsumption() {
		if (isOverPowerConsumed()) {
			getControllableCorridors().stream().filter(corridor -> !corridor.hasMovement())
					.forEach(Corridor::minimizePowerConsumption);
		}
	}

	/**
	 * Resets the equipment state to the beginning for the all the corridors.
	 * 
	 */
	public void resetCorridorEquipmentState() {
		getControllableCorridors().forEach(corridor -> {
			corridor.setHasMovement(false);
			corridor.resetEquipmentsState();
		});
	}

	/**
	 * Switches on the equipments for the given corridor number.
	 * 
	 * @param corridorNumber - corridor number in which movement is detected.
	 */
	public void motionDetectedForCorridor(int corridorNumber) {
		Corridor corridor = getControllableCorridors().stream()
				.filter(subCorridor -> subCorridor.getCorridorNumber() == corridorNumber).findFirst().get();
		corridor.setHasMovement(true);
		corridor.setEquipmentsState();
	}

}

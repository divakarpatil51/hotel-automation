package com.hotelautomation.corridor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hotelautomation.equipment.ElectricEquipment;

/**
 * This class deals with handling equipments state for a Corridor.
 * 
 * @author divakar.patil
 *
 */
public class Corridor {

	private int corridorNumber;
	private boolean hasMovement;
	private CorridorType corridorType;
	private List<ElectricEquipment> electricEquipments;

	public boolean hasMovement() {
		return hasMovement;
	}

	public void setHasMovement(boolean hasMovement) {
		this.hasMovement = hasMovement;
	}

	public List<ElectricEquipment> getElectricEquipments() {
		return electricEquipments;
	}

	public void setElectricEquipments(List<ElectricEquipment> electricEquipments) {
		this.electricEquipments = electricEquipments;
	}

	public int getCorridorNumber() {
		return corridorNumber;
	}

	public void setCorridorNumber(int corridorNumber) {
		this.corridorNumber = corridorNumber;
	}

	public CorridorType getCorridorType() {
		return corridorType;
	}

	public void setCorridorType(CorridorType corridorType) {
		this.corridorType = corridorType;
	}

	public int getCurrentPowerConsumption() {
		return electricEquipments.stream().filter(electricEquipment -> electricEquipment.isSwitchedOn())
				.mapToInt(electricEquipment -> electricEquipment.getElectricityConsumption()).sum();
	}

	/**
	 * Ensures that power consumption is minimized by switching off the equipments
	 * in the corridor.
	 */
	public void minimizePowerConsumption() {
		Optional<ElectricEquipment> equipment = electricEquipments.stream()
				.filter(electricEquipment -> electricEquipment.isControllable() && electricEquipment.isSwitchedOn())
				.findFirst();
		if (equipment.isPresent()) {
			equipment.get().switchOn(false);
		}
	}

	/**
	 * Switches on all the equipments for the corridor.
	 */
	public void setEquipmentsState() {
		electricEquipments.forEach(equipment -> equipment.switchOn(true));
	}

	/**
	 * Resets equipment status to their default state.
	 */
	public void resetEquipmentsState() {
		electricEquipments.forEach(ElectricEquipment::resetState);
	}

	@Override
	public String toString() {
		return corridorType + " CORRIDOR: " + corridorNumber + " " + Arrays.toString(electricEquipments.toArray());
	}

}

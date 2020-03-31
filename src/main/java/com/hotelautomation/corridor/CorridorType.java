package com.hotelautomation.corridor;

/**
 * Enumerator for corridor types available on a hotel floor.
 * 
 * @author divakar.patil
 *
 */
public enum CorridorType {

	MAIN(false, 15), SUB(true, 10);

	private boolean areEquipmentsControllable;
	private int maxAllowedPowerConsumption;

	CorridorType(boolean areEquipmentsControllable, int maxAllowedPowerConsumption) {
		this.areEquipmentsControllable = areEquipmentsControllable;
		this.maxAllowedPowerConsumption = maxAllowedPowerConsumption;
	}

	public boolean areEquipmentsControllable() {
		return areEquipmentsControllable;
	}

	public int getMaxAllowedPowerConsumption() {
		return maxAllowedPowerConsumption;
	}

}

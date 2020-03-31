package com.hotelautomation.equipment;

/**
 * Enumerator for Electric Equipments
 * 
 * @author divakar.patil
 *
 */
public enum EquipmentType {

	LIGHT(5), AC(10);

	private int unitsConsumed;

	EquipmentType(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public int getUnitsConsumed() {
		return unitsConsumed;
	}
}

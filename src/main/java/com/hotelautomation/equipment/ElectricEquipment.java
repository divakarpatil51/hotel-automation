package com.hotelautomation.equipment;

/**
 * This class represents the ElectricEquipment present in a corridor
 * 
 * @author divakar.patil
 *
 */
public class ElectricEquipment {

	private boolean isSwitchedOn;
	private boolean defaultState;
	private boolean isControllable;
	private EquipmentType electricEquipmentType;

	private ElectricEquipment(EquipmentType electricEquipmentType, boolean defaultState, boolean isControllable) {
		this.electricEquipmentType = electricEquipmentType;
		this.defaultState = defaultState;
		this.isSwitchedOn = defaultState;
		this.isControllable = isControllable;
	}

	public boolean isControllable() {
		return isControllable;
	}

	public EquipmentType getElectricEquipmentType() {
		return electricEquipmentType;
	}

	public boolean isSwitchedOn() {
		return isSwitchedOn;
	}

	public int getElectricityConsumption() {
		return electricEquipmentType.getUnitsConsumed();
	}
	
	public boolean isDefaultState() {
		return defaultState;
	}
	
	public void switchOn(boolean switchOn) {
		isSwitchedOn = switchOn;
	}

	public void resetState() {
		isSwitchedOn = defaultState;
	}

	@Override
	public String toString() {
		return electricEquipmentType.name() + ": " + (isSwitchedOn ? "ON" : "OFF");
	}

	public static ElectricEquipmentBuilder getBuilder() {
		return new ElectricEquipmentBuilder();
	}

	public static class ElectricEquipmentBuilder {
		private boolean defaultState;
		private boolean isControllable;
		private EquipmentType electricEquipmentType;

		public ElectricEquipmentBuilder withDefaultEquipmentState(boolean defaultState) {
			this.defaultState = defaultState;
			return this;
		}

		public ElectricEquipmentBuilder withEquipmentType(EquipmentType electricEquipmentType) {
			this.electricEquipmentType = electricEquipmentType;
			return this;
		}

		public ElectricEquipmentBuilder withIsControllable(boolean isControllable) {
			this.isControllable = isControllable;
			return this;
		}

		public ElectricEquipment build() {
			return new ElectricEquipment(electricEquipmentType, defaultState, isControllable);
		}
	}

}

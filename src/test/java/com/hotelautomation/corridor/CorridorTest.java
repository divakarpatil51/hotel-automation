package com.hotelautomation.corridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.hotelautomation.equipment.ElectricEquipment;
import com.hotelautomation.equipment.EquipmentType;
import com.hotelautomation.utility.DataManager;

public class CorridorTest {

	/**
	 * Test to verify default power consumption for main corridor.
	 */
	@Test
	public void testGetDefaultCurrentPowerConsumptionForMainCorridor() {
		List<Corridor> corridors = DataManager.createCorridorData(CorridorType.MAIN, 1);
		Corridor corridor = corridors.get(0);
		int expectedPowerConsumption = EquipmentType.AC.getUnitsConsumed() + EquipmentType.LIGHT.getUnitsConsumed();
		assertEquals(expectedPowerConsumption, corridor.getCurrentPowerConsumption());
	}
	
	/**
	 * Test to verify default power consumption for sub corridor.
	 */
	@Test
	public void testGetDefaultCurrentPowerConsumptionForSubCorridor() {
		List<Corridor> corridors = DataManager.createCorridorData(CorridorType.SUB, 1);
		Corridor corridor = corridors.get(0);
		int expectedPowerConsumption = EquipmentType.AC.getUnitsConsumed();
		assertEquals(expectedPowerConsumption, corridor.getCurrentPowerConsumption());
	}
	
	/**
	 * Test to verify power consumption for sub corridor when motion is detected.
	 */
	@Test
	public void testGetCurrentPowerConsumptionForSubCorridorForMotion() {
		List<Corridor> corridors = DataManager.createCorridorData(CorridorType.SUB, 1);
		Corridor corridor = corridors.get(0);
		corridor.getElectricEquipments().stream().forEach(equipment -> equipment.switchOn(true));
		
		int expectedPowerConsumption = EquipmentType.AC.getUnitsConsumed() + EquipmentType.LIGHT.getUnitsConsumed();
		assertEquals(expectedPowerConsumption, corridor.getCurrentPowerConsumption());
	}
	
	/**
	 * Test to verify power consumption for sub corridor when motion is detected.
	 */
	@Test
	public void testMinimizePowerConsumption() {
		List<Corridor> corridors = DataManager.createCorridorData(CorridorType.SUB, 1);
		Corridor corridor = corridors.get(0);
		corridor.getElectricEquipments().stream().forEach(equipment -> equipment.switchOn(true));
		corridor.minimizePowerConsumption();

		ElectricEquipment light = corridor.getElectricEquipments().stream().filter(equipment -> equipment.getElectricEquipmentType() == EquipmentType.LIGHT).findFirst().get();
		ElectricEquipment ac = corridor.getElectricEquipments().stream().filter(equipment -> equipment.getElectricEquipmentType() == EquipmentType.AC).findFirst().get();
		assertFalse(light.isSwitchedOn());
		assertTrue(ac.isSwitchedOn());
	}
	
	/**
	 * Test to verify power consumption for sub corridor when motion is detected.
	 */
	@Test
	public void testSetEquipmentState() {
		List<Corridor> corridors = DataManager.createCorridorData(CorridorType.SUB, 1);
		Corridor corridor = corridors.get(0);
		corridor.setEquipmentsState();
		
		assertTrue(corridor.getElectricEquipments().stream().allMatch(equipment -> equipment.isSwitchedOn()));
	}
	
	/**
	 * Test to verify power consumption for sub corridor when motion is detected.
	 */
	@Test
	public void testResetEquipmentState() {
		List<Corridor> corridors = DataManager.createCorridorData(CorridorType.SUB, 1);
		Corridor corridor = corridors.get(0);
		corridor.resetEquipmentsState();
		
		assertTrue(corridor.getElectricEquipments().stream().allMatch(equipment -> equipment.isSwitchedOn() == equipment.isDefaultState()));
	}

}

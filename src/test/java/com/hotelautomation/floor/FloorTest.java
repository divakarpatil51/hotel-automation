package com.hotelautomation.floor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.hotelautomation.corridor.Corridor;
import com.hotelautomation.corridor.CorridorType;
import com.hotelautomation.utility.DataManager;

public class FloorTest {

	@Test
	public void testPowerConsumptionUnderControl() {
		Floor floor = createFloorData(false);

		assertFalse(floor.isOverPowerConsumed());
	}

	@Test
	public void testOverPowerConsumed() {
		Floor floor = createFloorData(true);
		assertTrue(floor.isOverPowerConsumed());
	}

	@Test
	public void testMaxAllowedPowerConsumptionForMainCorridor() {
		Floor floor = createFloorData(false);

		int expectedMaxAllowedPowerConsumption = CorridorType.MAIN.getMaxAllowedPowerConsumption()
				+ CorridorType.SUB.getMaxAllowedPowerConsumption() * 2;
		assertEquals(expectedMaxAllowedPowerConsumption, floor.getMaxAllowedPowerConsumption());
	}

	@Test
	public void testMinimizePowerConsumption() {
		Floor floor = createFloorData(true);
		floor.minimizePowerConsumption();

		assertFalse(floor.isOverPowerConsumed());
	}
	
	private Floor createFloorData(boolean setEquipmentState) {
		List<Corridor> mainCorridor = DataManager.createCorridorData(CorridorType.MAIN, 1);
		List<Corridor> subCorridors = DataManager.createCorridorData(CorridorType.SUB, 2);
		subCorridors.stream().filter(subCorridor -> setEquipmentState)
				.forEach(subCorridor -> subCorridor.setEquipmentsState());
		Floor floor = new Floor();
		floor.setFloorNumber(1);
		floor.getCorridors().addAll(mainCorridor);
		floor.getCorridors().addAll(subCorridors);
		return floor;
	}
}

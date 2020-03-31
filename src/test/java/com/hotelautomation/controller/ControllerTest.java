package com.hotelautomation.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hotelautomation.corridor.Corridor;
import com.hotelautomation.equipment.ElectricEquipment;
import com.hotelautomation.equipment.EquipmentType;
import com.hotelautomation.floor.Floor;
import com.hotelautomation.model.Hotel;
import com.hotelautomation.model.MotionSensorData;
import com.hotelautomation.utility.DataManager;

public class ControllerTest {

	private static List<Floor> floors;

	@BeforeClass
	public static void setUp() {
		Hotel hotel = new Hotel();
		hotel.setMainCorridorsPerFloor(1);
		hotel.setSubCorridorsPerFloor(2);
		hotel.setNumberOfFloors(2);
		floors = DataManager.createFloorData(hotel);
	}

	/**
	 * Test to verify that lights are switched on in a corridor when movement is
	 * detected.
	 */
	@Test
	public void testUpdateFloorEquipmentStatusWithMovement() {
		MotionSensorData motionSensorData = createMotionSensorData(1, 1, true, 0);

		Controller controller = new Controller(floors);
		controller.updateFloorEquipmentStatus(motionSensorData);

		ElectricEquipment light = getControllableCorridorData(motionSensorData);
		assertTrue(light.isSwitchedOn());
	}

	/**
	 * Test to verify that lights are switched off in a corridor when movement is
	 * not detected.
	 */
	@Test
	public void testUpdateFloorEquipmentStatusWithNoMovement() {
		MotionSensorData motionSensorData = createMotionSensorData(1, 1, false, 0);

		Controller controller = new Controller(floors);
		controller.updateFloorEquipmentStatus(motionSensorData);

		ElectricEquipment light = getControllableCorridorData(motionSensorData);
		assertFalse(light.isSwitchedOn());
	}

	private ElectricEquipment getControllableCorridorData(MotionSensorData motionSensorData) {
		Floor floor = floors.get(motionSensorData.getFloorNumber() - 1);
		Corridor controllableCorridor = floor.getCorridors().stream()
				.filter(corridor -> corridor.getCorridorType().areEquipmentsControllable()
						&& corridor.getCorridorNumber() == motionSensorData.getCorridorNumber())
				.findFirst().get();
		ElectricEquipment light = controllableCorridor.getElectricEquipments().stream()
				.filter(equipment -> equipment.getElectricEquipmentType() == EquipmentType.LIGHT).findFirst().get();
		return light;
	}

	/**
	 * Test to verify that power usage is minimized when not motion is detected on
	 * the floor.
	 */
	@Test
	public void testMinimizePowerConsumption() {
		MotionSensorData motionSensorData = createMotionSensorData(1, 1, false, 60);

		Controller controller = new Controller(floors);
		controller.updateFloorEquipmentStatus(motionSensorData);
		controller.minimizePowerConsumptionForFloor(motionSensorData.getFloorNumber());

		Floor floor = floors.get(motionSensorData.getFloorNumber() - 1);
		Corridor controllableCorridor = floor.getCorridors().stream()
				.filter(corridor -> corridor.getCorridorType().areEquipmentsControllable()
						&& corridor.getCorridorNumber() == motionSensorData.getCorridorNumber())
				.findFirst().get();
		assertTrue(controllableCorridor.getCurrentPowerConsumption() < floor.getMaxAllowedPowerConsumption());
	}

	private MotionSensorData createMotionSensorData(int corridorNum, int floorNum, boolean isMovementDetected,
			long noMotionTimeSpan) {
		MotionSensorData motionSensorData = new MotionSensorData();
		motionSensorData.setCorridorNumber(corridorNum);
		motionSensorData.setFloorNumber(floorNum);
		motionSensorData.setMovement(isMovementDetected);
		motionSensorData.setNoMotionTimeSpan(noMotionTimeSpan);
		return motionSensorData;
	}
}

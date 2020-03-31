package com.hotelautomation;

import java.io.IOException;
import java.util.List;

import com.hotelautomation.controller.Controller;
import com.hotelautomation.floor.Floor;
import com.hotelautomation.model.MotionSensorData;
import com.hotelautomation.utility.DataManager;
import com.hotelautomation.utility.InputReader;
import com.hotelautomation.utility.OutputWriter;

/**
 * Class to test the {@code Controller} class. Output is generated in file:
 * output.txt.
 * 
 * @author divakar.patil
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		List<Floor> floors = DataManager.createFloorData(InputReader.getHotelData());
		Controller controller = new Controller(floors);

		for (MotionSensorData motionSensorData : InputReader.getMotionSensorData()) {
			controller.updateFloorEquipmentStatus(motionSensorData);
			controller.minimizePowerConsumptionForFloor(motionSensorData.getFloorNumber());
			OutputWriter.writeFloorData(motionSensorData, floors);
		}
	}
}

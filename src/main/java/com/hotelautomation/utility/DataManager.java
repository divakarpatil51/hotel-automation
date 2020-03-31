package com.hotelautomation.utility;

import java.util.ArrayList;
import java.util.List;

import com.hotelautomation.corridor.Corridor;
import com.hotelautomation.corridor.CorridorType;
import com.hotelautomation.equipment.ElectricEquipment;
import com.hotelautomation.equipment.EquipmentType;
import com.hotelautomation.floor.Floor;
import com.hotelautomation.model.Hotel;

public class DataManager {
	
	public static List<Floor> createFloorData(Hotel hotel) {
		List<Floor> floors = new ArrayList<>(hotel.getNumberOfFloors());

		for (int floorNumber = 1; floorNumber <= hotel.getNumberOfFloors(); floorNumber++) {
			Floor floor = new Floor();
			floor.setFloorNumber(floorNumber);
			floor.getCorridors().addAll(createCorridorData(CorridorType.MAIN, hotel.getMainCorridorsPerFloor()));
			floor.getCorridors().addAll(createCorridorData(CorridorType.SUB, hotel.getSubCorridorsPerFloor()));
			floors.add(floor);
		}
		return floors;
	}

	public static List<Corridor> createCorridorData(CorridorType corridorType, int corridorsPerFloor) {
		List<Corridor> corridors = new ArrayList<>();
		for (int corridorNumber = 1; corridorNumber <= corridorsPerFloor; corridorNumber++) {
			Corridor corridor = new Corridor();
			corridor.setElectricEquipments(createElectricEquipmentData(corridorType));
			corridor.setCorridorNumber(corridorNumber);
			corridor.setCorridorType(corridorType);
			corridors.add(corridor);
		}
		return corridors;
	}

	public static List<ElectricEquipment> createElectricEquipmentData(CorridorType corridorType) {
		List<ElectricEquipment> electricEquipments = new ArrayList<>();

		ElectricEquipment light = ElectricEquipment.getBuilder().withEquipmentType(EquipmentType.LIGHT)
				.withDefaultEquipmentState(corridorType == CorridorType.MAIN)
				.withIsControllable(corridorType == CorridorType.SUB).build();
		electricEquipments.add(light);

		ElectricEquipment airConditioner = ElectricEquipment.getBuilder().withEquipmentType(EquipmentType.AC)
				.withDefaultEquipmentState(true).withIsControllable(corridorType == CorridorType.SUB).build();
		electricEquipments.add(airConditioner);

		return electricEquipments;
	}

}

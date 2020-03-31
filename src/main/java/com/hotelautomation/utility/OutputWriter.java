package com.hotelautomation.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.hotelautomation.floor.Floor;
import com.hotelautomation.model.MotionSensorData;

public final class OutputWriter {
	
	private static BufferedWriter writer;
	
	static {
		 try {
			writer = new BufferedWriter(new FileWriter(new File("output.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFloorData(MotionSensorData motionSensorData, List<Floor> floors) {
		try {
			writer.write("--------------------Input---------------------");
			writer.newLine();
			writer.write(motionSensorData.toString());
			writer.newLine();
			writer.write("--------------------Output---------------------");
			writer.newLine();
			for (Floor floor : floors) {
				writer.write("Floor: " + floor.getFloorNumber());
				writer.newLine();
				floor.getCorridors().forEach(corridor -> {
					try {
						writer.write(corridor.toString());
						writer.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				writer.newLine();
				if (floor.isOverPowerConsumed()) {
					writer.write("*****************************************************************");
					writer.newLine();
					writer.write("***************Power consumption cannot be reduced***************");
					writer.newLine();
					writer.write("*****************************************************************");
					writer.newLine();
				}else {
					writer.write("***************Power consumption under control***************");
					writer.newLine();
				}
			}
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

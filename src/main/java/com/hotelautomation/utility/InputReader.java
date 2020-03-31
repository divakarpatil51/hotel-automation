package com.hotelautomation.utility;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelautomation.model.Hotel;
import com.hotelautomation.model.MotionSensorData;

public final class InputReader {

	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

	public static Hotel getHotelData() {

		try {
			return OBJECTMAPPER.readValue(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("hoteldata.json"), Hotel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<MotionSensorData> getMotionSensorData() {
		try {
			return OBJECTMAPPER
					.readValue(
							Thread.currentThread().getContextClassLoader()
									.getResourceAsStream("motionsensordata.json"),
							new TypeReference<List<MotionSensorData>>() {
							});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

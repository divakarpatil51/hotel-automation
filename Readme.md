
# Hotel Automation Controller:

To run the application, run ControllerTester.java class as Java application. 
The input files are:

    1. hoteldata.json: File representing hotel data.
    2. motionsensordata.json: File representing motion sensor data.

The output file is:

    output.json: This file contains motion sensor data and its corresponding output.

Class hierarchy:
1. **Controller.java:** This class controls the electric equipments and minimize the power consumption.
2. **Floor.java**: This class deals with corridors and its data. It calculates the total consumption and acts accordingly to minimize the power consumption.
5. **Corridor.java**: This class deals with the equipments present in a corridor. It toggles the state of electric equipments depending on the movements detected in the corridor.
7. **ElectricEquipment.java**: This class represents the state of an electric equipment.
8. **Hotel.java**: This class holds the hotel data such as number of floors, corridors per floor.
9. **MotionSensorData.java**: This class represents the motion sensor data.
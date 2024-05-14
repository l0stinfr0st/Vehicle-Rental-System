package VehicleRentalSystem_13;

public class SUV_13 extends Car_13 {

    public String typeOfWheel;

    public SUV_13(String color, int seatingCapacity, int numOfDoors, int numberOfTires, int plateNumber, double dailyFee, String typeOfWheel) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
        this.typeOfWheel = typeOfWheel;

    }

    public SUV_13(String color, int seatingCapacity, int numOfDoors, String typeOfWheel, int numberOfTires, int plateNumber, double dailyFee) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
        this.typeOfWheel = typeOfWheel;
    }

    @Override
    public String toString() {
        return "SUV | Type Of Wheel: " + typeOfWheel + super.toString();
    }

}

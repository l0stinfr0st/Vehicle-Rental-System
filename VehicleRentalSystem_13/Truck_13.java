
package VehicleRentalSystem_13;

public class Truck_13 extends Vehicle_13 {

    public int loadingCapacity;

    public Truck_13(int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(numberOfTires, plateNumber, dailyFee);
        this.loadingCapacity = loadingCapacity;
    }
    @Override
    public String toString() {
        return " | Loading Capacity: " + loadingCapacity + super.toString();
    }

}


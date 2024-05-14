
package VehicleRentalSystem_13;

public abstract class Car_13 extends Vehicle_13 implements rentable_13 {

    public String color;
    public int seatingCapacity;
    public int numOfDoors;
    

    public Car_13(String color, int seatingCapacity, int numOfDoors, int numberOfTires, int plateNumber, double dailyFee) {
        super(numberOfTires, plateNumber, dailyFee);
        this.color = color;
        this.numOfDoors = numOfDoors;
        this.seatingCapacity = seatingCapacity;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    public void setCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }
    @Override
    public String toString() {
        return " | Color: " + color + " | Number Of Doors: " + numOfDoors + " | Seating Capacity: " + seatingCapacity + super.toString();
    }

}

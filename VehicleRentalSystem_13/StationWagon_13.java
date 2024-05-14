package VehicleRentalSystem_13;

public class StationWagon_13 extends Car_13 implements remotelyDropable_13, remotelyDelivered_13, loadable_13 {

    public int loadingCapacity;
    public int currentload = 0;

    public StationWagon_13(String color, int seatingCapacity, int numOfDoors, int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
        this.loadingCapacity = loadingCapacity;
    }

    @Override
    public void deliverMe() {
        System.out.println("Delivered to " + this.getDeliverLocation());
    }

    @Override
    public void dropMe() {
        System.out.println("Vehicle dropped off at : " + this.getDropLocation());
        System.out.println("Total Price: " + this.getDailyFee());
    }

    @Override
    public void loadMe(int wantedLoad) throws OverWeightException_13 {
        if (wantedLoad <= loadingCapacity) {
            System.out.println("loaded");
            currentload = wantedLoad;
        } else {
            throw new OverWeightException_13("Too much load");
        }
    }
    @Override
    public String toString() {
        return "Station Wagon | Loading Capacity: " + loadingCapacity + super.toString();
    }

}


package VehicleRentalSystem_13;

public class SmallTrucks_13 extends Truck_13 implements remotelyDropable_13, remotelyDelivered_13, loadable_13 {

    public int currentLoad = 0;

    public SmallTrucks_13(int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(loadingCapacity, numberOfTires, plateNumber, dailyFee);
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
            currentLoad = wantedLoad;
        } else {
            throw new OverWeightException_13("Overweight");
        }
    }
    @Override
    public String toString() {
        return "Small Truck | Current Load: "+ currentLoad + super.toString();
    }
}


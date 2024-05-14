package VehicleRentalSystem_13;

public class TransportTrucks_13 extends Truck_13 implements loadable_13 {

    public boolean abroad;
    public int currentload = 0;

    public TransportTrucks_13(int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(loadingCapacity, numberOfTires, plateNumber, dailyFee);
    }

    public TransportTrucks_13(int loadingCapacity, boolean abroad, int numberOfTires, int plateNumber, double dailyFee) {
        super(loadingCapacity, numberOfTires, plateNumber, dailyFee);
        this.abroad = abroad;
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
        return "Transport Truck | Current Load: " + currentload + super.toString();
    }
}

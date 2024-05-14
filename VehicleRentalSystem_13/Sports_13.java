package VehicleRentalSystem_13;

import java.util.Date;

public class Sports_13 extends Car_13 implements bookable_13, remotelyDropable_13, remotelyDelivered_13 {

    private boolean isBooked;
    public int horsepower;
    private Date bookStart;
    private Date bookEnd;
    private Date bookingDate;

    public Sports_13(String color, int seatingCapacity, int numOfDoors, int horsepower, int numberOfTires, int plateNumber, double dailyFee) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
        this.horsepower = horsepower;
    }

    @Override
    public void bookMe(Date bookStart, Date bookEnd, Customer_13 c) throws SorryWeDontHaveThatOneException_13 {
        if (isBooked) {
            throw new SorryWeDontHaveThatOneException_13("We dont have an available model to book");
        }
        this.bookStart = bookStart;
        this.bookEnd = bookEnd;
        isBooked = true;
        this.bookingDate = new Date();
        this.setCustomer(c);
    }

    @Override
    public void dropMe() {
        System.out.println("Vehicle dropped off at : " + this.getDropLocation());
        System.out.println("Total Price: " + this.getDailyFee());
    }

    @Override
    public void cancelMe() throws NoCancellationYouMustPayException_13 {
        if (!isBooked) {
            System.out.println("Vehicle is not booked");
        } else {
            if ((new Date().getTime() > this.getStartDate().getTime())) {
                throw new NoCancellationYouMustPayException_13("You can't cancel after rental start date");
            }
            bookStart = null;
            bookEnd = null;
            this.setCustomer(null);
        }
    }

    @Override
    public void deliverMe() {
        System.out.println("Delivered to " + this.getDeliverLocation());
    }

    @Override
    public Date getBookStart() {
        return bookStart;
    }

    @Override
    public Date getBookEnd() {
        return bookEnd;
    }

    @Override
    public boolean getIsBooked() {
        return isBooked;
    }

    @Override
    public Date getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return "Sports Car | " + "Horse Power: " + horsepower + super.toString();
    }

}

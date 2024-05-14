package VehicleRentalSystem_13;

import java.util.Date;

public interface rentable_13{
    void rentMe(Date starting, Date ending, Customer_13 c) throws SorryWeDontHaveThatOneException_13;
}

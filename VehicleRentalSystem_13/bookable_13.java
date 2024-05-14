
package VehicleRentalSystem_13;

import java.util.Date;

public interface bookable_13 {
    void bookMe(Date bookStart, Date bookEnd, Customer_13 c) throws SorryWeDontHaveThatOneException_13;
    void cancelMe() throws NoCancellationYouMustPayException_13;
    Date getBookStart();
    Date getBookEnd();
    boolean getIsBooked();
    Date getBookingDate();
    
    
}

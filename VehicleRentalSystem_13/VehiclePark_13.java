package VehicleRentalSystem_13;

import java.io.*;
import java.util.*;

public class VehiclePark_13 {

    private Admin_13 a = new Admin_13();
    private ArrayList vehicleList = new ArrayList<Vehicle_13>();
    private File vehicles;
    private ArrayList rentedVehicleList = new ArrayList<Vehicle_13>();
    private File rentedVehicles;
    private ArrayList bookedVehicleList = new ArrayList<Vehicle_13>();
    private File bookedVehicles;
    private ArrayList registeredCustomerList = new ArrayList<Customer_13>();
    private File registeredCustomers;
    private ArrayList adminList = new ArrayList<Admin_13>();
    private File admins;
    private File dailyReport;

    public VehiclePark_13(File vehicles, File rentedVehicles, File bookedVehicles, File registeredCustomers, File admins, File dailyReport) {
        this.vehicles = vehicles;
        this.rentedVehicles = rentedVehicles;
        this.bookedVehicles = bookedVehicles;
        this.registeredCustomers = registeredCustomers;
        this.dailyReport = dailyReport;
        this.admins = admins;
    }

    public void displayVehicles() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList v = getCurrentFileIntoArray(vehicles);
        for (int i = 0; i < v.size(); i++) {
            System.out.println(((Vehicle_13) v.get(i)).toString());
        }
    }

    public void addVehicle(Vehicle_13 veh) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (vehicles.length() != 0) {
            this.vehicleList = getCurrentFileIntoArray(vehicles);
        }
        vehicleList.add(veh);
        getCurrentArrayIntoFile(vehicleList, vehicles);
    }

    public void removeVehicle(int plateNumber) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle_13 veh = getVehicle(plateNumber);
        if (vehicles.length() != 0) {
            this.vehicleList = getCurrentFileIntoArray(vehicles);
        }
        for (int i = 0; i < vehicleList.size(); i++) {
            if (((Vehicle_13) vehicleList.get(i)).getPlateNumber() == veh.getPlateNumber()) {
                vehicleList.remove(i);
            }
        }
        getCurrentArrayIntoFile(vehicleList, vehicles);
    }

    public void displayAvailableVehicles(Date date1, Date date2) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Vehicle_13> v = getCurrentFileIntoArray(vehicles);
        for (Vehicle_13 vehicle : v) {
            if (!vehicle.getBeingUsed()) {
                if (v instanceof bookable_13) {
                    bookable_13 v1 = (bookable_13) v;
                    if (!v1.getIsBooked()) {
                        System.out.println((v1.toString()));
                    } else if (date1.getTime() < v1.getBookStart().getTime() && date2.getTime() < v1.getBookStart().getTime()) {
                        System.out.println(v1.toString());
                    } else if (date1.getTime() > v1.getBookEnd().getTime() && date2.getTime() > v1.getBookEnd().getTime()) {
                        System.out.println(v1.toString());
                    }
                } else {
                    System.out.println(vehicle.toString());
                }
            } else if (date1.getTime() < vehicle.getStartDate().getTime() && date2.getTime() < vehicle.getStartDate().getTime()) {
                System.out.println(vehicle.toString());
            } else if (date1.getTime() > vehicle.getEndDate().getTime() && date2.getTime() > vehicle.getEndDate().getTime()) {
                System.out.println(vehicle.toString());
            }
        }
    }

    public void displayAvailableVehicles(Date date1, Date date2, Class<?> vehicleType) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList v = getCurrentFileIntoArray(vehicles);
        for (Object vehicle : v) { //checking renting date
            if (vehicleType.isInstance(vehicle)) {
                Vehicle_13 veh = (Vehicle_13) vehicle;
                if (!veh.getBeingUsed()) {
                    if (veh instanceof bookable_13) {
                        bookable_13 v1 = (bookable_13) veh;
                        if (!v1.getIsBooked()) {
                            System.out.println((v1.toString()));
                        } else if (date1.getTime() < v1.getBookStart().getTime() && date2.getTime() < v1.getBookStart().getTime()) {
                            System.out.println(v1.toString());
                        } else if (date1.getTime() > v1.getBookEnd().getTime() && date2.getTime() > v1.getBookEnd().getTime()) {
                            System.out.println(v1.toString());
                        }
                    } else {
                        System.out.println((veh.toString()));
                    }
                } else if (date1.getTime() < veh.getStartDate().getTime() && date2.getTime() < veh.getStartDate().getTime()) {
                    System.out.println(veh.toString());
                } else if (date1.getTime() > veh.getEndDate().getTime() && date2.getTime() > veh.getEndDate().getTime()) {
                    System.out.println(veh.toString());
                }
            }
        }

    }

    public void bookVehicle(int numberPlate, Date bookStart, Date bookEnd, Customer_13 c) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle_13 book = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (book == null) {
            System.out.println("That vehicle doesnt exist");
        } else if (book instanceof bookable_13) {
            try {
                removeVehicle(book.getPlateNumber());
                ((bookable_13) book).bookMe(bookStart, bookEnd, c);
                vehicleList.add(book);
                getCurrentArrayIntoFile(vehicleList, vehicles);
                if (bookedVehicles.length() != 0) {
                    this.bookedVehicleList = getCurrentFileIntoArray(bookedVehicles);
                }
                bookedVehicleList.add(book);
                getCurrentArrayIntoFile(bookedVehicleList, bookedVehicles);
            } catch (SorryWeDontHaveThatOneException_13 ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That vehicle cannot be booked");
        }
    }

    public void cancelBook(int numberPlate) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle_13 book = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (book == null) {
            System.out.println("That vehicle doesnt exist");
        } else if (book instanceof bookable_13 && ((bookable_13) book).getIsBooked()) {
            try {
                removeVehicle(book.getPlateNumber());
                ((bookable_13) book).cancelMe();
                vehicleList.add(book);
                getCurrentArrayIntoFile(vehicleList, vehicles);
                if (bookedVehicles.length() != 0) {
                    this.bookedVehicleList = getCurrentFileIntoArray(bookedVehicles);
                }
                bookedVehicleList = getCurrentFileIntoArray(bookedVehicles);
                for (int i = 0; i < bookedVehicleList.size(); i++) {
                    if (((Vehicle_13) bookedVehicleList.get(i)).getPlateNumber() == book.getPlateNumber()) {
                        bookedVehicleList.remove(i);
                    }
                }
                getCurrentArrayIntoFile(bookedVehicleList, bookedVehicles);
            } catch (NoCancellationYouMustPayException_13 ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That vehicle wasnt booked");
        }
    }

    public void rentVehicle(int numberPlate, Date start, Date end, Customer_13 c) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle_13 rent = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (rent == null) {
            System.out.println("That vehicle doesnt exist");
        } else {
            try {
                removeVehicle(rent.getPlateNumber());
                rent.rentMe(start, end, c);
                vehicleList.add(rent);
                getCurrentArrayIntoFile(vehicleList, vehicles);
                if (rentedVehicles.length() != 0) {
                    this.rentedVehicleList = getCurrentFileIntoArray(rentedVehicles);
                }
                rentedVehicleList.add(rent);
                getCurrentArrayIntoFile(rentedVehicleList, rentedVehicles);
            } catch (SorryWeDontHaveThatOneException_13 ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void dropVehicle(int numberPlate) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle_13 dropVehicle = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (dropVehicle == null) {
            System.out.println("that vehicle doesnt exist");
        } else if (dropVehicle instanceof remotelyDropable_13 && (((Vehicle_13) dropVehicle).getDropLocation() != null)) {
            removeVehicle(dropVehicle.getPlateNumber());
            ((remotelyDropable_13) dropVehicle).dropMe();
            vehicleList.add(dropVehicle);
            getCurrentArrayIntoFile(vehicleList, vehicles);
        } else {
            System.out.println("That vehicle cannot be remotely dropable");
        }
    }

    public void load(int numberPlate, int amount) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle_13 load = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (load == null) {
            System.out.println("That vehicle doesnt exist");
        } else if (load instanceof loadable_13) {
            try {
                removeVehicle(load.getPlateNumber());
                ((loadable_13) load).loadMe(amount);
                vehicleList.add(load);
                getCurrentArrayIntoFile(vehicleList, vehicles);
            } catch (OverWeightException_13 ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That vehicle cannot be dropped");
        }
    }

    public File dailyReport() throws FileNotFoundException, IOException, ClassNotFoundException {
        PrintWriter report = new PrintWriter(dailyReport);
        report.println("Daily Report: ");
        report.println();
        if (rentedVehicles.length() != 0) {
            ArrayList rented = getCurrentFileIntoArray(rentedVehicles);
            report.println("List of Vehicles rented  today:");
            for (Object vehicle : rented) {
                Vehicle_13 v = (Vehicle_13) vehicle;
                if (v.getRentingDate().getTime() > (new Date().getTime() - 8.64 * Math.pow(10, 7)) && v.getRentingDate().getTime() < new Date().getTime()) {
                    report.println();
                    report.println(v.toString());
                    report.println(v.getCustomer().toString());
                }
            }
        }
        report.println();
        if (bookedVehicles.length() != 0) {
            ArrayList booked = getCurrentFileIntoArray(bookedVehicles);
            report.println("List of Vehicles booked today:");
            for (Object vehicle : booked) {
                Vehicle_13 v = (Vehicle_13) vehicle;
                if (((bookable_13) v).getBookingDate().getTime() > (new Date().getTime() - 8.64 * Math.pow(10, 7)) && ((bookable_13) v).getBookingDate().getTime() < new Date().getTime()) {
                    report.println();
                    report.println(v.toString());
                }
            }
        }
        report.flush();
        report.close();
        return dailyReport;
    }

    public Vehicle_13 getVehicle(int numberPlate) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList v = getCurrentFileIntoArray(vehicles);
        for (int i = 0; i < v.size(); i++) {
            if (((Vehicle_13) v.get(i)).getPlateNumber() == numberPlate) {
                return (Vehicle_13) v.get(i);
            }
        }
        return null;
    }

    public ArrayList getCurrentFileIntoArray(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream(f);
        ObjectInputStream ob = new ObjectInputStream(input);
        ArrayList aaa = (ArrayList) ob.readObject();
        ob.close();
        return aaa;
    }

    public void addCustomer(Customer_13 customer) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (registeredCustomers.length() != 0) {
            this.registeredCustomerList = getCurrentFileIntoArray(registeredCustomers);
        }
        registeredCustomerList.add(customer);
        getCurrentArrayIntoFile(registeredCustomerList, registeredCustomers);
    }

    public Customer_13 getCustomer(String email, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
        registeredCustomerList = getCurrentFileIntoArray(registeredCustomers);
        for (int i = 0; i < registeredCustomerList.size(); i++) {
            if (((Customer_13) registeredCustomerList.get(i)).getEmail().equals(email) && ((Customer_13) registeredCustomerList.get(i)).getPassword().equals(password)) {
                return (Customer_13) registeredCustomerList.get(i);
            }
        }
        return null;
    }

    public void addAdmin(Admin_13 newAdmin) throws FileNotFoundException, IOException, ClassNotFoundException {

        if (admins.length() != 0) {
            this.adminList = getCurrentFileIntoArray(admins);
        }
        adminList.add(newAdmin);
        getCurrentArrayIntoFile(adminList, admins);
    }

    public boolean checkAdmin(String email, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
        if ((a.getEmail().equals(email) && a.getPassword().equals(password))) {
            return a.getEmail().equals(email) && a.getPassword().equals(password);
        }
        if (admins.length() == 0) {
            return false;
        }
        adminList = getCurrentFileIntoArray(admins);
        for (int i = 0; i < adminList.size(); i++) {
            if (((Admin_13) adminList.get(i)).getEmail().equals(email) && ((Admin_13) adminList.get(i)).getPassword().equals(password)) {
                return true;
            }
        }
        System.out.println("Invalid credentials");
        System.out.println("");
        return false;
    }

    public void getCurrentArrayIntoFile(ArrayList a, File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileOutputStream o = new FileOutputStream(f);
        ObjectOutputStream ob = new ObjectOutputStream(o);
        ob.writeObject(a);
        ob.close();
    }

    public Customer_13 changePassword(String name, int age, String email) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        registeredCustomerList = getCurrentFileIntoArray(registeredCustomers);
        for (int i = 0; i < registeredCustomerList.size(); i++) {
            if (((Customer_13) registeredCustomerList.get(i)).getEmail().equals(email)) {
                if (((Customer_13) registeredCustomerList.get(i)).getName().equals(name) && ((Customer_13) registeredCustomerList.get(i)).getAge() == age) {
                    System.out.print("Enter your new password: ");
                    String password = input.next();
                    System.out.println("");
                    ((Customer_13) registeredCustomerList.get(i)).setPassword(password);
                    getCurrentArrayIntoFile(registeredCustomerList, registeredCustomers);
                    return (Customer_13) registeredCustomerList.get(i);
                } else {
                    System.out.println("Wrong information");
                }
            }
        }
        System.out.println("Email doesnt exist");
        return null;
    }

}

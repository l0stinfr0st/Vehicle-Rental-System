package VehicleRentalSystem_13;

import java.io.Serializable;

public class Admin_13 implements Serializable {
    private String name = "Admin";
    private String email = "Admin@gmail.com";
    private String password = "BunnyInHeadLight123";
    
    
    public Admin_13(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;                                                     
    }

    public Admin_13() {
        
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    
    
}

  
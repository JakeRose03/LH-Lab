package io.littlehorse.quickstart.Model;

public class Driver {

    public Long driverid;
    public String name;
    public String location;

    public Driver(){}

    public Driver(Long driverid, String name, String location){
        this.driverid = driverid;
        this.name = name;
        this.location = location;

    }

    public Long getDriverid() {
        return driverid;
    }

    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

package Hospitals;

import Util.Address;

public class Hospital {
    private int totalCapacity;
    protected String name;
    Address address;

    public Hospital(int totalCapacity, String name, Address address) {
        this.totalCapacity = totalCapacity;
        this.name = name;
        this.address = address;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

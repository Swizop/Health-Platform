package Hospitals;

import People.Doctor;
import Util.Address;

import  java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HospitalSection extends Hospital {
    private String section;
    private int sectionCapacity;
    private ArrayList<Doctor> doctors;

    public HospitalSection(int totalCapacity, String name, Address address,
                           String section, int sectionCapacity) {
        super(totalCapacity, name, address);
        this.section = section;
        this.sectionCapacity = sectionCapacity;
        this.doctors = new ArrayList<Doctor>();
    }

    public HospitalSection(int totalCapacity, String name, Address address,
                           String section, int sectionCapacity, ArrayList<Doctor> doctors) {
        this(totalCapacity, name, address, section, sectionCapacity);
        this.doctors = new ArrayList<Doctor>(doctors);
    }

    public void addDoctor(Doctor doc)
    {
        this.doctors.add(doc);
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSectionCapacity() {
        return sectionCapacity;
    }

    public void setSectionCapacity(int sectionCapacity) {
        this.sectionCapacity = sectionCapacity;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = new ArrayList<Doctor>(doctors);
    }

    @Override
    public String toString() {
        String docString = "";
        int i = 1;
        for(Doctor d : doctors)
        {
            docString += i + d.toString() + '\n';
            i += 1;
        }

        return "HospitalSection: " +
                "Hospital name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", sectionCapacity=" + sectionCapacity +
                ", doctors:\n" +  docString;
    }
}

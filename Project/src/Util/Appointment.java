package Util;

import Hospitals.HospitalSection;
import People.Doctor;
import People.Patient;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class Appointment {
    LocalDateTime localDateTime;
    Doctor doctor;
    Patient patient;
    HospitalSection hospitalSection;

    public Appointment(LocalDateTime localDateTime, Doctor doctor, Patient patient, HospitalSection hospitalSection) {
        this.localDateTime = localDateTime;
        this.doctor = doctor;
        this.patient = patient;
        this.hospitalSection = hospitalSection;
    }

    @Override
    public String toString() {
        return "Appointment: " +
                "\nWhen: " + localDateTime +
                "\nWho: " + doctor +
                "\nWith: " + patient +
                "\nAt: " + hospitalSection.getName() + " " + hospitalSection.getSection() +
                '\n';
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public HospitalSection getHospitalSection() {
        return hospitalSection;
    }

    public void setHospitalSection(HospitalSection hospitalSection) {
        this.hospitalSection = hospitalSection;
    }
}

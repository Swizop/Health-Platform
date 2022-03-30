package People;

import java.time.LocalDate;

public class Doctor extends Human implements  Comparable<Doctor>{

    public Doctor(int age, String firstName, String lastName, int startYear) {
        super(age, firstName, lastName, startYear);
    }

    public Doctor(int age, String firstName, String lastName, String email, String phoneNumber, int startYear) {
        super(age, firstName, lastName, email, phoneNumber, startYear);
    }

    @Override
    public String toString() {
        return "Dr. "+ firstName + " " + lastName +
                ", age=" + age +
                ", years of experience: " + (LocalDate.now().getYear() - startYear);
    }

//    @Override
//    public void setStartYear(int startYear) {
//        this.startYear = startYear;
//    }

    @Override
    public int compareTo(Doctor o) {
        if(startYear > o.startYear)
            return -1;
        if(startYear < o.startYear)
            return 1;
        return 0;
    }
}

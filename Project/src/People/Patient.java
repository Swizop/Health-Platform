package People;

public class Patient extends Human {

    public Patient(int age, String firstName, String lastName, int startYear) {
        super(age, firstName, lastName, startYear);
    }

    public Patient(int age, String firstName, String lastName, String email, String phoneNumber, int startYear) {
        super(age, firstName, lastName, email, phoneNumber, startYear);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public void setStartYear(int startYear) throws VerifyError {
        super.setStartYear(startYear);
        if(startYear < 2016)        // platform was made in 2016, here StartYear is when user joined
            throw new VerifyError("Invalid year");
    }
}

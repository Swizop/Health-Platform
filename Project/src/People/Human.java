package People;

import java.time.LocalDate;

public class Human {
    protected int age;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected int startYear;


    public Human(int age, String firstName, String lastName, int startYear) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        setStartYear(startYear);
    }

    public Human(int age, String firstName, String lastName, String email, String phoneNumber, int startYear) {
        this(age, firstName, lastName, startYear);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) throws VerifyError
    {
        if(startYear > LocalDate.now().getYear() || startYear < 1940)
            throw new VerifyError("Invalid year");

        this.startYear = startYear;
    }
}


import Hospitals.HospitalSection;
import People.Doctor;
import People.HealthMinister;
import People.Human;
import People.Patient;
import Util.Address;
import Util.Appointment;
import Util.Message;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.sql.*;

public class Service {
    private TreeSet<Doctor> doctors = new TreeSet<>();
    private HashMap<Integer, Address> addressHashMap = new HashMap<Integer, Address>();
    private List<HospitalSection> hospitalSections = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private HealthMinister healthMinister = HealthMinister.getHealthMinister();

    public void createDoctor(Scanner sc)
    {
        System.out.println("Age:");
        int age = sc.nextInt(), year;
        sc.nextLine();
        System.out.println("First name:");
        String fn, ln;
        fn = sc.nextLine();
        System.out.println("Last name:");
        ln = sc.nextLine();
        System.out.println("Year of starting activity:");
        year = sc.nextInt();
        sc.nextLine();

        doctors.add(new Doctor(age, fn, ln, year));
    }

    public void createDoctor(Scanner sc, Connection connection) throws SQLException {
        System.out.println("Age:");
        int age = sc.nextInt(), year;
        sc.nextLine();
        System.out.println("First name:");
        String fn, ln;
        fn = sc.nextLine();
        System.out.println("Last name:");
        ln = sc.nextLine();
        System.out.println("Year of starting activity:");
        year = sc.nextInt();
        sc.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO doctors (age, firstName, lastName, year) VALUES (?, ?, ?, ?)");
        preparedStatement.setInt(1, age);
        preparedStatement.setString(2, fn);
        preparedStatement.setString(3, ln);
        preparedStatement.setInt(4, year);
        preparedStatement.executeUpdate();
        doctors.add(new Doctor(age, fn, ln, year));
    }

    public void createNewPatient(Scanner sc, Connection connection) throws SQLException
    {
        String firstName, lastName, email, phoneNumber;
        int age;
        System.out.println("Enter age");
        age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter first name");
        firstName = sc.nextLine();
        System.out.println("Enter last name");
        lastName = sc.nextLine();
        System.out.println("Enter email");
        email = sc.nextLine();
        System.out.println("Enter phone number");
        phoneNumber = sc.nextLine();

        Patient p = new Patient(age, firstName, lastName, email, phoneNumber, LocalDate.now().getYear());
        patients.add(p);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO patients (age, firstName, lastName, email, phone) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, age);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, phoneNumber);
        preparedStatement.executeUpdate();
    }

    public void deleteDoctor(Scanner sc, Connection connection) throws SQLException
    {
        System.out.println("Choose a doctor index (from 1 to " + ((Integer)(doctors.size())).toString() + ")");
        int docIndex = sc.nextInt();
        List<Doctor> list = new ArrayList<>(doctors);
        Doctor doc = list.get(docIndex - 1);
        doctors.remove(doc);
        for(HospitalSection h : hospitalSections)
            h.removeDoctor(doc);

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM doctors WHERE age = ? AND firstName = ? AND lastName = ?");
        preparedStatement.setInt(1, doc.getAge());
        preparedStatement.setString(2, doc.getFirstName());
        preparedStatement.setString(3, doc.getLastName());
        preparedStatement.executeUpdate();
        System.out.println("Succes!");
    }

    public void deletePatient(Scanner sc, Connection connection) throws SQLException
    {
        System.out.println("Please confirm the patient id (nr. from 1 to " + ((Integer)(patients.size())).toString() + ")");
        int patIndex = sc.nextInt();
        Patient p = patients.get(patIndex - 1);
        patients.remove(p);

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM patients WHERE email = ?");
        preparedStatement.setString(1, p.getEmail());
        preparedStatement.executeUpdate();
        System.out.println("Succes!");
    }

    public void addDoctors(List<String[]> data)
    {
        Doctor d;
        for(String[] arr : data) {
            d = new Doctor(Integer.parseInt(arr[0]), arr[1], arr[2], Integer.parseInt(arr[3]));
            doctors.add(d);
        }
    }
    public void addClients(List<String[]> data)
    {
        Patient p;
        for(String[] arr : data) {
            p = new Patient(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], LocalDate.now().getYear());
            patients.add(p);
        }
    }
    public void addAddresses(List<String[]> data)
    {
        Address a;
        for(String[] arr : data) {
            a = new Address(arr[1], arr[2], Integer.parseInt(arr[3]), arr[4]);
            addressHashMap.put(Integer.parseInt(arr[0]), a);
        }
    }
    public void addSections(List<String[]> data)
    {
        HospitalSection hs;
        for(String[] arr : data) {
            hs = new HospitalSection(Integer.parseInt(arr[0]), arr[1], addressHashMap.get(Integer.parseInt(arr[2])), arr[3], Integer.parseInt(arr[4]));
            hospitalSections.add(hs);
        }
    }
    public Doctor getMostExperienced()
    {
        return doctors.first();
    }


    public void sendMinisterMessage(Scanner sc)
    {
        System.out.println("Write your message below. Press enter to send");
        String content = sc.nextLine();
        healthMinister.newMessage(new Message(doctors.first(), content));
    }

    public void showMessages()
    {
        List<Message> messages = healthMinister.getInbox();
        if(messages.size() == 0)
            System.out.println("No complaints so far. Good job!");
        else {
            System.out.println(messages.size() + " messages:");
            int i = 1;
            for (Message m : messages) {
                System.out.println(i + ". " + m.getContent());
            }
        }
    }

    public void showSections()
    {
        int i = 1;
        for(HospitalSection hs : hospitalSections)
        {
            System.out.println(i + ".");
            System.out.println(hs);
            i += 1;
        }
    }

    public void assignDoctor(Scanner sc)
    {
        System.out.println("Choose a doctor index (from 1 to " + ((Integer)(doctors.size())).toString() + ")");
        int docIndex = sc.nextInt();
        List<Doctor> list = new ArrayList<>(doctors);
        System.out.println("Choose index for hospital section (up to " + ((Integer)(hospitalSections.size())).toString() + ")");
        int hsIndex = sc.nextInt();
        hospitalSections.get(hsIndex - 1).addDoctor(list.get(docIndex - 1));
        System.out.println("Succes!");
    }

    public void showAllDoctors()
    {
        Integer i = 1;
        for (Doctor d : doctors) {
            System.out.println(i.toString() + '.');
            System.out.println(d);
            i += 1;
        }
    }

    public void showAllPatients()
    {
        Integer i = 1;
        for (Patient p : patients) {
            System.out.println(i.toString() + '.');
            System.out.println(p);
            i += 1;
        }
    }

    public void createNewPatient(Scanner sc)
    {
        String firstName, lastName, email, phoneNumber;
        int age;
        System.out.println("Enter age");
        age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter first name");
        firstName = sc.nextLine();
        System.out.println("Enter last name");
        lastName = sc.nextLine();
        System.out.println("Enter email");
        email = sc.nextLine();
        System.out.println("Enter phone number");
        phoneNumber = sc.nextLine();

        Patient p = new Patient(age, firstName, lastName, email, phoneNumber, LocalDate.now().getYear());
        patients.add(p);
    }

    public void makeAppointment(Scanner sc)
    {
        System.out.println("Choose index for hospital section (from 1 to " + ((Integer)(hospitalSections.size())).toString() + ")");
        int hsIndex = sc.nextInt();
        Random rand = new Random();
        sc.nextLine();
        HospitalSection hs = hospitalSections.get(hsIndex - 1);
        ArrayList<Doctor> hsDoctors = hs.getDoctors();
        String date, time;
        LocalDateTime dateTime;
        System.out.println("Write your preferred date in the format: YYYY-MM-DD");
        date = sc.nextLine();
        System.out.println("Write your preferred time in the format: HH:MM");
        time = sc.nextLine();
        dateTime = LocalDateTime.parse(date + "T" + time + ":00");
        System.out.println("Please confirm your patient id (nr. from 1 to " + ((Integer)(patients.size())).toString() + ")");
        hsIndex = sc.nextInt();
        Appointment a = new Appointment(dateTime, hsDoctors.get(rand.nextInt(hsDoctors.size())), patients.get(hsIndex - 1), hs);
        System.out.println(a);
    }
}

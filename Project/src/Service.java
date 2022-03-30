
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

public class Service {
    private TreeSet<Doctor> doctors = new TreeSet<>();
    private List<HospitalSection> hospitalSections = new ArrayList<>() {{
        add(new HospitalSection(500, "Regina Maria", new Address("Bucharest", "Main Street", 4, "0131"), "Neurology", 100));
        add(new HospitalSection(300, "City Hospital", new Address("Bucharest", "Second Street", 2, "1561"), "Infectious Diseases", 40));
    }};
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
        System.out.println("Choose index for hospital section (up to " + ((Integer)(hospitalSections.size())).toString() + ")");
        int hsIndex = sc.nextInt();
        sc.nextLine();
        HospitalSection hs = hospitalSections.get(hsIndex - 1);
        String date, time;
        LocalDateTime dateTime;
        System.out.println("Write your preferred date in the format: YYYY-MM-DD");
        date = sc.nextLine();
        System.out.println("Write your preferred time in the format: HH:MM");
        time = sc.nextLine();
        dateTime = LocalDateTime.parse(date + "T" + time + ":00");
        Appointment a = new Appointment(dateTime, hs.getDoctors().get(0), patients.get(0), hs);
        System.out.println(a);
    }
}

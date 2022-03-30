
import Hospitals.HospitalSection;
import People.Doctor;
import People.HealthMinister;
import People.Human;
import Util.Address;
import Util.Message;

import javax.print.Doc;
import java.util.*;

public class Service {
    private TreeSet<Doctor> doctors = new TreeSet<>();
    private List<HospitalSection> hospitalSections = new ArrayList<>() {{
        add(new HospitalSection(500, "Regina Maria", new Address("Bucharest", "Main Street", 4, "0131"), "Neurology", 100));
        add(new HospitalSection(300, "City Hospital", new Address("Bucharest", "Second Street", 2, "1561"), "Infectious Diseases", 40));
    }};
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
}

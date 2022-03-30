package People;

import Util.Message;

import java.util.ArrayList;
import java.util.List;

public class HealthMinister extends Human {

    private static HealthMinister healthMinister;
    private static List<Message> inbox;

    private HealthMinister(int age, String firstName, String lastName, int startYear) {
        super(age, firstName, lastName, startYear);
        inbox = new ArrayList<>();
    }

    private HealthMinister() {
        this(57, "John", "Doe", 2018);
    }

    public static HealthMinister getHealthMinister()
    {
        if(healthMinister == null)
            healthMinister = new HealthMinister();
        return healthMinister;
    }

    public void newMessage(Message m)
    {
        inbox.add(m);
    }

    public static List<Message> getInbox() {
        return inbox;
    }

    //    @Override
//    public void setStartYear(int startYear) {
//        this.startYear = startYear;
//    }
}

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Service service = new Service();
        String currentUser = "doctor";
        String[] userTypes = {"doctor", "patient", "minister"};
        int ok;

        while (true)
        {
            System.out.println("Logged in as " + currentUser);
            System.out.println("0 - Stop; 1 - Create doctor; 2 - Get details of most experienced doctor; 3 - User specific actions; 4 - Switch user; 5 - Get all sections from hospitals");
            input = sc.nextLine();
            switch (input.trim()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    service.createDoctor(sc);
                }
                case "2" -> {
                    System.out.println(service.getMostExperienced());
                }
                case "3" -> {
                    ok = 1;
                    while (ok == 1)
                    {
                        System.out.println("User Specific Actions: ");
                        if(currentUser.compareTo("doctor") == 0)
                        {
                            System.out.println("a - Go back; b - Raise issue to minister");
                            input = sc.nextLine();
                            switch (input.trim())
                            {
                                case "a" -> {
                                    ok = 0;
                                }
                                case "b" -> {
                                    service.sendMinisterMessage(sc);
                                    ok = 0;
                                }
                            }
                        }
                        else
                        {
                            if (currentUser.compareTo("minister") == 0) {
                                System.out.println("a - Go back; b - View reported issues");
                                input = sc.nextLine();
                                switch (input.trim()) {
                                    case "a" -> {
                                        ok = 0;
                                    }
                                    case "b" -> {
                                        service.showMessages();
                                    }
                                }
                            }
                            else { break; }
                        }
                    }
                }
                case "4" -> {
                    System.out.println("Choose (write) one of the following: " + Arrays.deepToString(userTypes));
                    input = sc.nextLine().trim();
                    if (Arrays.asList(userTypes).contains(input))
                        currentUser = input;
                    else
                        System.out.println("Invalid user type");
                }
                case "5" -> {
                    service.showSections();
                }
            }
        }
    }
}

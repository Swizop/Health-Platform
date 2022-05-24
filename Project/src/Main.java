import Generic.ReadService;
import Generic.WriteService;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Service service = new Service();
        ReadService readService = ReadService.getReadService();
        WriteService writeService = WriteService.getWriteService();
        String currentUser = "doctor";
        String[] userTypes = {"doctor", "patient", "minister"};
        int ok;

//        // read csv files
//        service.addDoctors(readService.getData("input/doctors.csv"));
//        service.addClients(readService.getData("input/patients.csv"));
//        service.addAddresses(readService.getData("input/addresses.csv"));
//        service.addSections(readService.getData("input/sections.csv"));


        // GET DB CONNECTION
        String connectionString = "jdbc:mysql://localhost:3306/newdb";
        String username = "root";
        String password = "INSERT_PASSWORD!!!!";
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            Statement statement = connection.createStatement();

            // read data from db
            service.addDoctors(readService.getDataDB(statement, "doctors"));
            service.addClients(readService.getDataDB(statement, "patients"));
            service.addAddresses(readService.getDataDB(statement, "addresses"));
            service.addSections(readService.getDataDB(statement, "sections"));

            while (true)
            {
                System.out.println("Logged in as " + currentUser);
                System.out.println("0 - Stop; 1 - Create doctor; 2 - Get details of most experienced doctor; 3 - User specific actions; 4 - Switch user;\n5 - Get all sections from hospitals; 6 - Get all doctors; 7 - Create client profile; 8 - Show registered patients");
                input = sc.nextLine();
                switch (input.trim()) {
                    case "0" -> {
                        statement.close();
                        connection.close();
                        return;
                    }
                    case "1" -> {
                        service.createDoctor(sc, connection);
                        writeService.logData("Doctor created");
                    }
                    case "2" -> {
                        System.out.println(service.getMostExperienced());
                        writeService.logData("Most experienced doctor queried");
                    }
                    case "3" -> {
                        ok = 1;
                        while (ok == 1) {
                            System.out.println("User Specific Actions: ");
                            if (currentUser.compareTo("doctor") == 0) {
                                System.out.println("a - Go back; b - Raise issue to minister");
                                input = sc.nextLine();
                                switch (input.trim()) {
                                    case "a" -> {
                                        ok = 0;
                                    }
                                    case "b" -> {
                                        service.sendMinisterMessage(sc);
                                        ok = 0;
                                        writeService.logData("Issue raised to minister by doc");
                                    }
                                }
                            } else if (currentUser.compareTo("minister") == 0) {
                                System.out.println("a - Go back; b - View reported issues; c - Assign doctor to hospital section; d - Delete doctor; e - Delete patient");
                                input = sc.nextLine();
                                switch (input.trim()) {
                                    case "a" -> {
                                        ok = 0;
                                    }
                                    case "b" -> {
                                        service.showMessages();
                                        writeService.logData("Minister opened messages");
                                    }
                                    case "c" -> {
                                        service.assignDoctor(sc);
                                        writeService.logData("Minister assigned doctor to hospital");
                                    }
                                    case "d" -> {
                                        service.deleteDoctor(sc, connection);
                                        writeService.logData("Minister deleted doctor");
                                    }
                                    case "e" -> {
                                        service.deletePatient(sc, connection);
                                        writeService.logData("Minister deleted patient");
                                    }
                                }
                            } else if (currentUser.compareTo("patient") == 0) {
                                System.out.println("a - Go back; b - Make appointment;");
                                input = sc.nextLine();
                                switch (input.trim()) {
                                    case "a" -> {
                                        ok = 0;
                                    }
                                    case "b" -> {
                                        service.makeAppointment(sc);
                                        writeService.logData("Appointment made");
                                    }
                                }
                            }
                            else{
                                    break;
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
                        writeService.logData("Hospital sections queried");
                    }
                    case "6" -> {
                        service.showAllDoctors();
                        writeService.logData("Doctors queried");
                    }
                    case "7" -> {
                        // service.createNewPatient(sc);
                        service.createNewPatient(sc, connection);
                        writeService.logData("New patient added");
                    }
                    case "8" -> {
                        service.showAllPatients();
                        writeService.logData("Patients queried");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

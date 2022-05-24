package Generic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.sql.*;

public class ReadService {
    private static ReadService readService;
    private ReadService() {};

    public static ReadService getReadService() {
        if(readService == null)
            readService = new ReadService();
        return readService;
    }

    public List<String[]> getData(String name) {
        List<String[]> list = new ArrayList<>();
        try(var in = new BufferedReader(new FileReader(name))) {
            String line = "";
            while((line = in.readLine()) != null) {
                String[] fields = line.split(",");
                list.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> getDataDB(Statement statement, String query) {
        List<String[]> list = new ArrayList<>();
        try {
            if(query == "doctors") {
                ResultSet rs = statement.executeQuery("SELECT age, firstName, lastName, year FROM doctors");
                while(rs.next())
                {
                    String[] fields = {rs.getString("age"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("year")};
                    list.add(fields);
                }
            }
            else if (query == "patients") {
                ResultSet rs = statement.executeQuery("SELECT age, firstName, lastName, email, phone FROM patients");
                while(rs.next())
                {
                    String[] fields = {rs.getString("age"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("phone")};
                    list.add(fields);
                }
            }
            else if (query == "addresses")
            {
                ResultSet rs = statement.executeQuery("SELECT id, city, street, nr, postalCode FROM addresses");
                while(rs.next())
                {
                    String[] fields = {rs.getString("id"), rs.getString("city"), rs.getString("street"), rs.getString("nr"), rs.getString("postalCode")};
                    list.add(fields);
                }
            }
            else if (query == "sections")
            {
                ResultSet rs = statement.executeQuery("SELECT totalCapacity, name, addressId, section, sectionCapacity FROM sections");
                while(rs.next())
                {
                    String[] fields = {rs.getString("totalCapacity"), rs.getString("name"), rs.getString("addressId"), rs.getString("section"), rs.getString("sectionCapacity")};
                    list.add(fields);
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

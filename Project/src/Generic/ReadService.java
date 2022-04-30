package Generic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
}

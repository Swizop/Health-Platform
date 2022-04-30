package Generic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteService {
    private static WriteService writeService;
    private WriteService() {};
    public static WriteService getWriteService() {
        if(writeService == null)
            writeService = new WriteService();
        return writeService;
    }
    public static void logData(String str, String fileName) {
        try (var out = new BufferedWriter(new FileWriter(fileName))) {
            out.write(str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logData(String str) {
        try (var out = new BufferedWriter(new FileWriter("output/log.csv", true))) {
            out.write(str + "," + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

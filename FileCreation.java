package java_test_1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileCreation {

    
	public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> loopFunction(0), 0, 1, TimeUnit.SECONDS);
   
       
    }

    public static void loopFunction(int iterations) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentTime = new Date();
        String formattedTime = dateFormat.format(currentTime);

        System.out.println("Time : " + formattedTime);

        File file = new File("data.txt");
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create new file.");
                }
            }

          
            readFile(file);

            FileWriter writer = new FileWriter(file, true);
            writer.append(formattedTime).append(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

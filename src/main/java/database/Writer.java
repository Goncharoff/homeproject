package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Writer {
  private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
  private Long midnight = LocalDateTime.now().until(LocalDate.now().plusDays(1).atStartOfDay(), ChronoUnit.MINUTES);
  private static final String INFO_FILE_PATH= "src/main/resources/InMemoryDatabase.txt";

  public Writer() {
   // executorService.scheduleAtFixedRate(this::sendFileSomewhereFarFarAway, midnight, TimeUnit.DAYS.toMinutes(1), TimeUnit.MINUTES);
  }

  public void writeToDatabase(String info) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(INFO_FILE_PATH, true))) {
      writer.append("\n");
      writer.write(info);
      writer.close();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }


  private void sendFileSomewhereFarFarAway() {
    //don't have address =(
  }
}

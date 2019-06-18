package turnstile;

import card.CardChecker;
import database.Writer;
import domain.ClientCard;
import domain.TurnstilePossibleState;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sensor.Sensor;
import sensor.SensorNotificationListener;

public class Turnstile {
  private static final int FIVE_SECONDS_IN_MS = 5000;
  private TurnstileState turnstileState = new StopTurnistileState();
  private Writer writer = new Writer();

  private CardChecker cardChecker;
  private ClientCard clientCard;
  private List<Sensor> sensors;

  public Turnstile() {
    initSensors();
  }

  public void passPassanger() {

    if (cardChecker.isCardValid()) {
      clientCard.getClientMoney();
      writer.writeToDatabase("Card with id " + clientCard.getId() + " was passed in timestamp = " + new Date().getTime());
      openGate();
    }

  }


  private void openGate() {
    try {
      turnstileState.next(this);
      Thread.sleep(FIVE_SECONDS_IN_MS);
    } catch (InterruptedException ex) {
      System.out.println("Error during processing opening gates;");
    } finally {
      turnstileState.previous(this);
    }
  }

  void setTurnstileState(TurnstileState turnstileState) {
    this.turnstileState = turnstileState;
  }

  public void setClientCard(ClientCard clientCard) {
    this.clientCard = clientCard;
    cardChecker = new CardChecker(clientCard);
  }

  public TurnstilePossibleState getTurnstileState() {
    return turnstileState.getTurnsileState();
  }

  private void initSensors() {
    Sensor sensor = new Sensor(11, 10);
    Sensor sensor1 = new Sensor(100, 100);
    Sensor sensor2 = new Sensor(1, 2);

    sensor.getSensorEventManager().subscribe("Passing", new SensorNotificationListener(sensor, this));
    sensor1.getSensorEventManager().subscribe("Passing", new SensorNotificationListener(sensor1, this));
    sensor2.getSensorEventManager().subscribe("Passing", new SensorNotificationListener(sensor2, this));

    sensors = new ArrayList<>();
    sensors.add(sensor);
    sensors.add(sensor1);
    sensors.add(sensor2);
  }


}

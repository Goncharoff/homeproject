package turnstile;

import card.CardChecker;
import database.Writer;
import domain.ClientCard;
import domain.TurnstilePossibleState;
import sensor.Sensor;
import sensor.SensorNotificationListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Turnstile {
    private static final int FIVE_SECONDS_IN_MS = 5000;
    public static final String ERROR_INTERRUPTING_TIMER_MESSAGE = "Error during processing opening gates";
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
            writer.writeToDatabase(clientCard.getId(), new Date().getTime());
            openGate();
        }

    }


    private void openGate() {
        try {
            turnstileState.next(this);
            Thread.sleep(FIVE_SECONDS_IN_MS);
        } catch (InterruptedException ex) {
            System.out.println(ERROR_INTERRUPTING_TIMER_MESSAGE);
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
        String event = "Passing";

        Sensor sensor = new Sensor(11, 10);
        Sensor sensor1 = new Sensor(100, 100);
        Sensor sensor2 = new Sensor(1, 2);

        sensor.getSensorEventManager().subscribe(event, new SensorNotificationListener(sensor, this));
        sensor1.getSensorEventManager().subscribe(event, new SensorNotificationListener(sensor1, this));
        sensor2.getSensorEventManager().subscribe(event, new SensorNotificationListener(sensor2, this));

        sensors = new ArrayList<>();
        sensors.add(sensor);
        sensors.add(sensor1);
        sensors.add(sensor2);
    }

    private void checkSensors() {
        for (Sensor s : sensors) {
            s.someonePassing();
        }
    }
}

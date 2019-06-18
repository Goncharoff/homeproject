package sensor;

import turnstile.Turnstile;
import domain.TurnstilePossibleState;

public class SensorNotificationListener implements SensorListener {
  private Sensor sensor;
  private Turnstile turnstile;

  public SensorNotificationListener(Sensor sensor, Turnstile turnstile) {
    this.sensor = sensor;
    this.turnstile = turnstile;
  }

  @Override
  public void sensorListener() {
    if (turnstile.getTurnstileState() == TurnstilePossibleState.CLOSE) {
      System.out.println("Close gate and sound ~nOt aLLowEd~!");
    }
  }
}

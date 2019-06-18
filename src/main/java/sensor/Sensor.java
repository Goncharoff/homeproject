package sensor;

public class Sensor {
  private int xCoordinate;
  private int yCoordinate;
  private SensorEventManager sensorEventManager;

  public Sensor(int xCoordinate, int yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.sensorEventManager = new SensorEventManager("Passing");
  }

  public void someonePassing() {
    sensorEventManager.notify("Someone trying to pass");
  }

  public SensorEventManager getSensorEventManager() {
    return sensorEventManager;
  }
}

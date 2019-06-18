package sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorEventManager {
  private Map<String, List<SensorListener>> listeners = new HashMap<>();

  SensorEventManager(String... operations) {
    for (String operation : operations) {
      this.listeners.put(operation, new ArrayList<>());
    }
  }

  public void subscribe(String eventType, SensorListener sensorListener) {
    List<SensorListener> clients = listeners.get(eventType);
    clients.add(sensorListener);
  }

  public void unsubscribe(String eventType, SensorListener sensorListener) {
    List<SensorListener> clients = listeners.get(eventType);
    clients.remove(sensorListener);
  }

  void notify(String eventType) {
    List<SensorListener> clients = listeners.get(eventType);

    for (SensorListener observer : clients) {
      observer.sensorListener();
    }

  }
}

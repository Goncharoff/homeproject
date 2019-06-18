package turnstile;

import domain.TurnstilePossibleState;

public class StopTurnistileState implements TurnstileState {

  public void next(Turnstile turnstile) {
    turnstile.setTurnstileState(new OpenState());
  }

  public void previous(Turnstile turnstile) {
    System.out.println("Previous state is not reachable;");
  }

  @Override
  public TurnstilePossibleState getTurnsileState() {
    return TurnstilePossibleState.CLOSE;
  }
}

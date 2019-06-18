package turnstile;

import domain.TurnstilePossibleState;

public class OpenState implements TurnstileState {

  public void next(Turnstile turnstile) {
    System.out.println("Last state");
  }

  public void previous(Turnstile turnstile) {
    turnstile.setTurnstileState(new StopTurnistileState());
  }

  @Override
  public TurnstilePossibleState getTurnsileState() {
    return TurnstilePossibleState.OPEN;
  }

}

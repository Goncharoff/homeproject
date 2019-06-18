import domain.CardStatus;
import domain.ClientCard;
import turnstile.Turnstile;
import java.math.BigDecimal;
import java.util.Date;

public class Application {

  public static void main(String[] args) {
    //invalid card -- reason blocked
    ClientCard clientCard = new ClientCard(1, new BigDecimal("1234"), new Date().getTime() + 500000, CardStatus.BLOCKED);
    //invalid card -- reason outdated
    ClientCard clientCard1 = new ClientCard(2, new BigDecimal("1234"), new Date().getTime() - 500000, CardStatus.WORKING);
    //invalid card -- reason not enough money;
    ClientCard clientCard2 = new ClientCard(3, new BigDecimal("2"), new Date().getTime() + 500000, CardStatus.WORKING);

    //valid card
    ClientCard clientCard3 = new ClientCard(4, new BigDecimal("100"), new Date().getTime() + 500000, CardStatus.WORKING);

    Turnstile turnstile = new Turnstile();
    turnstile.setClientCard(clientCard);
    turnstile.passPassanger();

    turnstile.setClientCard(clientCard1);
    turnstile.passPassanger();

    turnstile.setClientCard(clientCard2);
    turnstile.passPassanger();

    turnstile.setClientCard(clientCard3);
    turnstile.passPassanger();
  }
}

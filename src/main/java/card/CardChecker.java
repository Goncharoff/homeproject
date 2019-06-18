package card;

import domain.CardStatus;
import domain.ClientCard;
import java.util.Date;
import utils.Constant;

public class CardChecker {

  private ClientCard clientCard;

  public CardChecker(ClientCard clientCard) {
    this.clientCard = clientCard;
  }


  private boolean isCardHasProperDate() {

    if (clientCard.getTimestampOfEnd() < new Date().getTime()) {
      System.out.println("Card is outdated");
      return false;
    }

    return true;
  }

  private boolean isCardWorking() {

    if (clientCard.getCardStatus() == CardStatus.BLOCKED) {
      System.out.println("Sorry, card is blocked");
      return false;
    }

    return true;
  }

  private boolean isCardHasEnoughMoney() {
    if (clientCard.getSum().compareTo(Constant.COST) < 0) {
      System.out.println("Not enough money");
      return false;
    }

    return true;
  }


  public boolean isCardValid() {
    return isCardHasEnoughMoney() && isCardHasProperDate() && isCardWorking();
  }

}

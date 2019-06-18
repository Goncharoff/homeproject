package domain;

import java.math.BigDecimal;
import utils.Constant;

public class ClientCard {

  private int id;
  private BigDecimal sum;
  private long timestampOfEnd;
  private CardStatus cardStatus;

  public ClientCard(int id, BigDecimal sum, long timestampOfEnd, CardStatus cardStatus) {
    this.id = id;
    this.sum = sum;
    this.timestampOfEnd = timestampOfEnd;
    this.cardStatus = cardStatus;
  }

  public BigDecimal getSum() {
    return sum;
  }

  public long getTimestampOfEnd() {
    return timestampOfEnd;
  }

  public CardStatus getCardStatus() {
    return cardStatus;
  }

  public void getClientMoney() {
    sum = sum.subtract(Constant.COST);
  }

  public int getId() {
    return id;
  }
}

package main.model.card.debit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashbackDebitCard extends DebitCard {

    private static final BigDecimal CASHBACK_RATE = new BigDecimal("0.05");
    private static final BigDecimal CASHBACK_THRESHOLD = new BigDecimal("5000.00");

    private BigDecimal cashback;

    public CashbackDebitCard(BigDecimal balance) {
        super(balance);
        this.cashback = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (super.pay(amount)) {
            if (amount.compareTo(CASHBACK_THRESHOLD) > 0) {
                BigDecimal cashbackEarned = amount.multiply(CASHBACK_RATE).setScale(2, RoundingMode.HALF_UP);
                cashback = cashback.add(cashbackEarned);
                System.out.println("You earned " + cashbackEarned + " cashback.");
            }
            return true;
        }
        return false;
    }

    @Override
    public String getAvailableFunds() {
        return super.getAvailableFunds() + "\nCashback: " + cashback;
    }
}

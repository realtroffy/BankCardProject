package main.model.card.credit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BonusCreditCard extends CreditCard {

    private static final BigDecimal SAVINGS_RATE = new BigDecimal("0.00005");

    private BigDecimal savings;

    public BonusCreditCard(BigDecimal balance, BigDecimal creditLimit) {
        super(balance, creditLimit);
        this.savings = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void topUp(BigDecimal amount) {
        super.topUp(amount);
        BigDecimal savingsEarned = amount.multiply(SAVINGS_RATE).setScale(2, RoundingMode.HALF_UP);
        savings = savings.add(savingsEarned);
        System.out.println("You earned " + savingsEarned + " in savings.");
    }

    @Override
    public String getAvailableFunds() {
        return super.getAvailableFunds() + "\nSavings: " + savings;
    }
}

package main.model.card;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BankCard {
    protected BigDecimal balance;

    private static final String AMOUNT_GREATER_THAN_ZERO_MESSAGE = "AMOUNT_GREATER_THAN_ZERO";
    protected static final BigDecimal ZERO = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public BankCard(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

    public abstract boolean pay(BigDecimal amount);
    public abstract void topUp(BigDecimal amount);
    public abstract String getAvailableFunds();

    protected void validateAmount(BigDecimal amount) {
        if (amount.compareTo(ZERO) <= 0) {
            throw new IllegalArgumentException(AMOUNT_GREATER_THAN_ZERO_MESSAGE);
        }
    }
}
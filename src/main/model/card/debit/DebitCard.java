package main.model.card.debit;

import main.model.card.BankCard;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DebitCard extends BankCard {
    private static final String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient funds.";

    public DebitCard(BigDecimal balance) {
        super(balance);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        validateAmount(amount);
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_UP);
            return true;
        } else {
            System.out.println(INSUFFICIENT_FUNDS_MESSAGE);
            return false;
        }
    }

    @Override
    public void topUp(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getAvailableFunds() {
        return "Balance: " + balance;
    }
}


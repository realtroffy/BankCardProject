package main.model.card.credit;

import main.model.card.BankCard;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCard extends BankCard {
    private static final String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient funds and credit limit.";

    private final BigDecimal creditLimit;
    private BigDecimal creditBalance;

    public CreditCard(BigDecimal balance, BigDecimal creditLimit) {
        super(balance);
        this.creditLimit = creditLimit.setScale(2, RoundingMode.HALF_UP);
        this.creditBalance = creditLimit.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        validateAmount(amount);
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_UP);
            return true;
        } else if (balance.add(creditBalance).compareTo(amount) >= 0) {
            BigDecimal remainingAmount = amount.subtract(balance);
            balance = ZERO;
            creditBalance = creditBalance.subtract(remainingAmount).setScale(2, RoundingMode.HALF_UP);
            return true;
        } else {
            System.out.println(INSUFFICIENT_FUNDS_MESSAGE);
            return false;
        }
    }

    @Override
    public void topUp(BigDecimal amount) {
        validateAmount(amount);
        BigDecimal remainingCredit = creditLimit.subtract(creditBalance);
        if (amount.compareTo(remainingCredit) >= 0) {
            creditBalance = creditLimit;
            balance = balance.add(amount.subtract(remainingCredit)).setScale(2, RoundingMode.HALF_UP);
        } else {
            creditBalance = creditBalance.add(amount).setScale(2, RoundingMode.HALF_UP);
        }
    }

    @Override
    public String getAvailableFunds() {
        return "Own funds: " + balance + "\nCredit funds: " + creditBalance;
    }
}

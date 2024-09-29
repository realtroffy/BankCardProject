package main.model.card.credit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BonusCashbackCreditCard extends CreditCard {

    private static final BigDecimal BONUS_RATE = new BigDecimal("0.01");
    private static final BigDecimal CASHBACK_RATE = new BigDecimal("0.05");
    private static final BigDecimal CASHBACK_THRESHOLD = new BigDecimal("5000.00");

    private BigDecimal bonusPoints;
    private BigDecimal cashback;

    public BonusCashbackCreditCard(BigDecimal balance, BigDecimal creditLimit) {
        super(balance, creditLimit);
        this.bonusPoints = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.cashback = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (super.pay(amount)) {
            BigDecimal pointsEarned = amount.multiply(BONUS_RATE).setScale(2, RoundingMode.HALF_UP);
            bonusPoints = bonusPoints.add(pointsEarned);
            if (amount.compareTo(CASHBACK_THRESHOLD) > 0) {
                BigDecimal cashbackEarned = amount.multiply(CASHBACK_RATE).setScale(2, RoundingMode.HALF_UP);
                cashback = cashback.add(cashbackEarned);
                System.out.println("You earned " + cashbackEarned + " cashback.");
            }

            System.out.println("You earned " + pointsEarned + " bonus points.");
            return true;
        }
        return false;
    }

    @Override
    public String getAvailableFunds() {
        return super.getAvailableFunds() + "\nBonus points: " + bonusPoints + "\nCashback: " + cashback;
    }
}

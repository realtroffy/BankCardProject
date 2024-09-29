package main.model.card.debit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BonusDebitCard extends DebitCard {

    private static final BigDecimal BONUS_RATE = new BigDecimal("0.01");

    private BigDecimal bonusPoints;

    public BonusDebitCard(BigDecimal balance) {
        super(balance);
        this.bonusPoints = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        if (super.pay(amount)) {
            BigDecimal pointsEarned = amount.multiply(BONUS_RATE).setScale(2, RoundingMode.HALF_UP);
            bonusPoints = bonusPoints.add(pointsEarned);
            System.out.println("You earned " + pointsEarned + " bonus points.");
            return true;
        }
        return false;
    }

    @Override
    public String getAvailableFunds() {
        return super.getAvailableFunds() + "\nBonus points: " + bonusPoints;
    }
}

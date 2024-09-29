import main.model.card.credit.CreditCard;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        CreditCard creditCard = new CreditCard(BigDecimal.ZERO, new BigDecimal("10000.00"));
        System.out.println(creditCard.getAvailableFunds());

        System.out.println("*************************");

        creditCard.topUp(new BigDecimal("5000.00"));
        System.out.println(creditCard.getAvailableFunds());

        System.out.println("*************************");

        creditCard.pay(new BigDecimal("5000.00"));
        System.out.println(creditCard.getAvailableFunds());

        System.out.println("*************************");

        creditCard.pay(new BigDecimal("3000.00"));
        System.out.println(creditCard.getAvailableFunds());

        System.out.println("*************************");

        creditCard.topUp(new BigDecimal("2000.00"));
        System.out.println(creditCard.getAvailableFunds());

        System.out.println("*************************");

        creditCard.topUp(new BigDecimal("2000.00"));
        System.out.println(creditCard.getAvailableFunds());
    }
}
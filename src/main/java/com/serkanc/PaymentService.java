package com.serkanc;

public class PaymentService {

    private final UserValidator userValidator;
    private final PaymentProcessor paymentProcessor;

    public PaymentService(UserValidator userValidator, PaymentProcessor paymentProcessor) {
        this.userValidator = userValidator;
        this.paymentProcessor = paymentProcessor;
    }

    public void processPayment(User user, PaymentDetails paymentDetails) {
        if ( ! userValidator.isValid(user)) {
            throw new IllegalArgumentException("");
        }

        paymentProcessor.processPayment(paymentDetails);
    }
}

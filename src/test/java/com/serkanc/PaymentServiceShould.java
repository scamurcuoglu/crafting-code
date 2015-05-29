package com.serkanc;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PaymentServiceShould {

    private UserValidator userValidator;
    private PaymentProcessor paymentProcessor;
    private PaymentService paymentService;
    private User SOME_USER;
    private PaymentDetails paymentDetails;

    @Before
    public void setUp() throws Exception {
        userValidator = mock(UserValidator.class);
        paymentProcessor = mock(PaymentProcessor.class);

        paymentService = new PaymentService(userValidator, paymentProcessor);

        SOME_USER = new User();
        paymentDetails = new PaymentDetails();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_an_exception_when_the_user_is_invalid() throws Exception {

        when(userValidator.isValid(SOME_USER)).thenReturn(false);

        paymentService.processPayment(SOME_USER, new PaymentDetails());
    }

    @Test
    public void call_payment_processor_when_user_is_valid() throws Exception {

        when(userValidator.isValid(SOME_USER)).thenReturn(true);

        paymentService.processPayment(SOME_USER, paymentDetails);

        verify(userValidator).isValid(SOME_USER);
        verify(paymentProcessor).processPayment(paymentDetails);
    }
}

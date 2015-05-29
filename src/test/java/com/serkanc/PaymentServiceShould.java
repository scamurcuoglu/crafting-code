package com.serkanc;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceShould {

    private UserValidator userValidator;
    private PaymentProcessor paymentProcessor;
    private PaymentService paymentService;
    private User ANY_USER;

    @Before
    public void setUp() throws Exception {
        userValidator = mock(UserValidator.class);
        paymentProcessor = mock(PaymentProcessor.class);

        paymentService = new PaymentService(userValidator, paymentProcessor);

        ANY_USER = new User();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_an_exception_when_the_user_is_invalid() throws Exception {

        when(userValidator.isValid(any(User.class))).thenReturn(false);

        paymentService.processPayment(ANY_USER, new PaymentDetails());
    }

    @Test
    public void call_payment_processor_when_user_is_valid() throws Exception {

        when(userValidator.isValid(any(User.class))).thenReturn(true);

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentService.processPayment(ANY_USER, paymentDetails);

        verify(paymentProcessor).processPayment(paymentDetails);
    }
}

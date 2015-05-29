package com.serkanc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PaymentServiceShould {

    private UserValidator userValidator;
    private PaymentProcessor paymentProcessor;
    private PaymentService paymentService;
    private User VALID_USER = new User();
    private User INVALID_USER = new User();
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Before
    public void setUp() throws Exception {
        userValidator = mock(UserValidator.class);
        paymentProcessor = mock(PaymentProcessor.class);

        paymentService = new PaymentService(userValidator, paymentProcessor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_an_exception_when_the_user_is_invalid() throws Exception {

        given(userValidator.isValid(INVALID_USER)).willReturn(false);

        paymentService.processPayment(INVALID_USER, new PaymentDetails());
    }

    @Test
    public void
    not_invoke_payment_processor_when_user_is_invalid() {

        given(userValidator.isValid(INVALID_USER)).willReturn(false);

        try {
            paymentService.processPayment(INVALID_USER, paymentDetails);
            fail("should have thrown an exception");
        }
        catch (Exception e) {
            verifyZeroInteractions(paymentProcessor);
        }
    }

    @Test
    public void call_payment_processor_when_user_is_valid() throws Exception {

        when(userValidator.isValid(VALID_USER)).thenReturn(true);

        paymentService.processPayment(VALID_USER, paymentDetails);

        verify(userValidator).isValid(VALID_USER);
        verify(paymentProcessor).processPayment(paymentDetails);
    }
}

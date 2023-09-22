package com.payment.sefa.enumeration;

public enum PaymentMethod {
    
    BOLETO,
    CREDITO,
    DEBITO,
    PIX;

    private PaymentMethod (){}
    public static PaymentMethod getPaymentMethod(String key) {
        for (  PaymentMethod paymentMethod: PaymentMethod.values()) {
            if (paymentMethod.toString().equals(key)) {
                return paymentMethod;
            }
        }
        return null;
    }
}

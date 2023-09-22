package com.payment.sefa.model;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.payment.sefa.enumeration.PaymentMethod;
import com.payment.sefa.enumeration.ProcessingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AuditTable(value = "")
@NoArgsConstructor
@Entity(name = "payment")
@EqualsAndHashCode(callSuper = false)
@AuditOverride(forClass = Auditable.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PaymentModel extends Auditable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_payment", nullable = false, unique = true)
    private Integer id;

    @Column(name = "debito_code", nullable = false, unique = false)
    private Integer debitCode;

    @Column(name = "cpf_cnpj", nullable = false, unique = false, length = 14)
    private String cpfCnpj;

    @Column(name = "number_card", nullable = false, unique = false, length = 16)
    private String number_card;

    @Column(name = "amount", nullable = false, unique = false)
    private String amout;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, unique = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "processing_status", nullable = false, unique = false)
    private ProcessingStatus processingStatus;

    public PaymentModel(Integer id, Integer debitCode, String cpfCnpj, String number_card, String amout,
            PaymentMethod paymentMethod, ProcessingStatus processingStatus) {
        this.id = id;
        this.debitCode = debitCode;
        this.cpfCnpj = cpfCnpj;
        this.number_card = number_card;
        this.amout = amout;
        this.paymentMethod = paymentMethod;
        this.processingStatus = processingStatus;
    }
}

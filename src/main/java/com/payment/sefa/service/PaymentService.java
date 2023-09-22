package com.payment.sefa.service;

import java.util.Optional;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.payment.sefa.Repository.PaymentRepository;
import com.payment.sefa.enumeration.ProcessingStatus;
import com.payment.sefa.model.PaymentModel;

@Service

public class PaymentService {
    
    final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentModel createPayment(PaymentModel payment) {
        // Implementação da lógica para criar um pagamento e salvar no banco de dados
        return paymentRepository.save(payment);
    }

    public Optional<PaymentModel> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public PaymentModel updatePaymentStatus(Long id, ProcessingStatus newStatus) {
        Optional<PaymentModel> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            PaymentModel payment = optionalPayment.get();
            
            if (payment.getProcessingStatus() == ProcessingStatus.PENDENTE) {
                if (newStatus == ProcessingStatus.SUCESSO || newStatus == ProcessingStatus.FALHA) {
                    payment.setProcessingStatus(newStatus);
                    return paymentRepository.save(payment);
                } else {
                    throw new IllegalArgumentException("O status de pagamento só pode ser alterado para PROCESSADO_COM_SUCESSO ou PROCESSADO_COM_FALHA quando o pagamento estiver PENDENTE.");
                }
            } else if (payment.getProcessingStatus() == ProcessingStatus.FALHA) {
                if (newStatus == ProcessingStatus.PENDENTE) {
                    payment.setProcessingStatus(newStatus);
                    return paymentRepository.save(payment);
                } else {
                    throw new IllegalArgumentException("O status de pagamento só pode ser alterado para PENDENTE quando o pagamento estiver PROCESSADO_COM_FALHA.");
                }
            } else {
                throw new IllegalArgumentException("O pagamento está PROCESSADO_COM_SUCESSO e não pode ter seu status alterado.");
            }
        } else {
            throw new IllegalArgumentException("Pagamento não encontrado.");
        }
    }

    public Iterable<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

     public Page<PaymentModel> filterPayments(Integer debitCode, String cpfCnpj, ProcessingStatus processingStatus, Pageable pageable) {
        if (debitCode != null) {
            return paymentRepository.findByDebitCode(debitCode, pageable, PaymentModel.class);
        } else if (cpfCnpj != null) {
            return paymentRepository.findByCpfCnpj(cpfCnpj, pageable, PaymentModel.class);    
        } else if (processingStatus != null) {
            return paymentRepository.findByProcessingStatus(processingStatus, pageable, PaymentModel.class);
        } else {
            return paymentRepository.findAll(pageable, PaymentModel.class);
        }
    }

    public void deletePayment(Long id) {
        Optional<PaymentModel> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            PaymentModel payment = optionalPayment.get();
            if (payment.getProcessingStatus() == ProcessingStatus.PENDENTE) {
                paymentRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("O pagamento só pode ser excluído se estiver PENDENTE.");
            }
        } else {
            throw new IllegalArgumentException("Pagamento não encontrado.");
        }
    }

    public Page<PaymentModel> filterPayments(Integer debitCode, String cpfCnpj, ProcessingStatus processingStatus) {
        return null;
    }}

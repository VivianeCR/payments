package com.payment.sefa.Repository;

import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payment.sefa.enumeration.ProcessingStatus;
import com.payment.sefa.model.PaymentModel;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentModel, Integer> {
    
    <T> Page<T> findAll(Pageable pageable, Class<T> type);
    <T> Page<T> findByDebitCode(Integer debitCode, Pageable pageable, Class<T> type);
    <T> Page<T> findByCpfCnpj(String cpfCnpj, Pageable pageable, Class<T> type);
    
    PaymentModel save(PaymentModel paymentModel);
    Page<PaymentModel> findByProcessingStatus(ProcessingStatus processingStatus, Pageable pageable,
            Class<PaymentModel> class1);
    Optional<PaymentModel> findById(Long id);
    void deleteById(Long id);
}
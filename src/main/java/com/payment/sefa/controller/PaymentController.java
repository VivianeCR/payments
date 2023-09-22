package com.payment.sefa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.payment.sefa.enumeration.ProcessingStatus;
import com.payment.sefa.model.PaymentModel;
import com.payment.sefa.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/payments")
public class PaymentController {

     private final PaymentService paymentService;

   
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentModel createPayment(@RequestBody PaymentModel paymentModel) {
        return paymentService.createPayment(paymentModel);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<PaymentModel> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}/status")
    public PaymentModel updateProcessingStatus(@PathVariable Long id, @RequestBody ProcessingStatus newStatus) {
        return paymentService.updatePaymentStatus(id, newStatus);
    }

    @GetMapping
     @ResponseStatus(code = HttpStatus.OK)
    public List<PaymentModel> getAllPayments() {
        return (List<PaymentModel>) paymentService.getAllPayments();
    }

    @GetMapping(value = "/filter")
     @ResponseStatus(code = HttpStatus.OK)
    public Page<PaymentModel> filterPayments(@RequestParam(required = false) Integer debitCode,
                                        @RequestParam(required = false) String cpfCnpj,
                                        @RequestParam(required = false) ProcessingStatus processingStatus) {
        return paymentService.filterPayments(debitCode, cpfCnpj, processingStatus);
    }

    @DeleteMapping(value = "/{id}")
     @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}

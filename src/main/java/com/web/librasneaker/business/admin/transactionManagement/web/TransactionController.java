package com.web.librasneaker.business.admin.transactionManagement.web;

import com.web.librasneaker.business.admin.transactionManagement.service.TransactionService;
import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.dto.transactionManagement.ListTransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/transactionManagement")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transaction/{id}")
    public ResponseEntity<List<ListTransactionDTO>> getTransaction(@PathVariable("id") String id) {
        List<ListTransactionDTO> transaction = transactionService.getTransaction(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

}

package com.web.librasneaker.business.admin.transactionManagement.impl;

import com.web.librasneaker.business.admin.transactionManagement.service.TransactionService;
import com.web.librasneaker.dto.billHistoryManagement.BillHistoryResponse;
import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.dto.transactionManagement.ListTransactionDTO;
import com.web.librasneaker.dto.transactionManagement.TransactionResponse;
import com.web.librasneaker.repository.TransactionRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Valid
public class TransactionServiceImpl  implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ListTransactionDTO> getTransaction(String id) {
        // Fetch the list of responses from the repository
        List<TransactionResponse> listResponse = transactionRepository.getTransaction(id);

        List<ListTransactionDTO> result = listResponse.stream()
                .map(response -> modelMapper.map(response, ListTransactionDTO.class))
                .toList();

        return result;
    }
}

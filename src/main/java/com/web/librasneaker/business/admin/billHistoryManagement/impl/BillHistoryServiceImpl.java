package com.web.librasneaker.business.admin.billHistoryManagement.impl;

import com.web.librasneaker.business.admin.billHistoryManagement.service.BillHistoryService;
import com.web.librasneaker.dto.billHistoryManagement.BillHistoryResponse;
import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.repository.BillHistoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class BillHistoryServiceImpl implements BillHistoryService {

    private final BillHistoryRepository billHistoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ListBillHistoryDTO> getBillHistory(String billId) {
        // Fetch the list of responses from the repository
        List<BillHistoryResponse> listResponse = billHistoryRepository.getBillHistory(billId);

        List<ListBillHistoryDTO> result = listResponse.stream()
                .map(response -> modelMapper.map(response, ListBillHistoryDTO.class))
                .toList();

        return result;
    }

}

package com.web.librasneaker.business.admin.billManagement.impl;

import com.web.librasneaker.business.admin.billManagement.service.BillManagementService;
import com.web.librasneaker.dto.billManagement.BillResponse;
import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class BillManagementServiceImpl implements BillManagementService {

    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ListBillDTO> getBillResponse(FindBillDTO req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());

        Page<BillResponse> pageResponse = billRepository.getBillResponse(pageable, req);

        // Map
        Page<ListBillDTO> pageDTO = pageResponse.map(billResponse ->
                modelMapper.map(billResponse, ListBillDTO.class)
        );

        return pageDTO;
    }
}

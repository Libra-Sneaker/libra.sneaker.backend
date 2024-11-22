package com.web.librasneaker.business.admin.billManagement.impl;

import com.web.librasneaker.business.admin.billManagement.service.BillManagementService;
import com.web.librasneaker.dto.billManagement.BillResponse;
import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.entity.BillEntity;
import com.web.librasneaker.entity.BillHistoryEntity;
import com.web.librasneaker.entity.SizeEntity;
import com.web.librasneaker.repository.BillHistoryRepository;
import com.web.librasneaker.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class BillManagementServiceImpl implements BillManagementService {

    private final BillHistoryRepository billHistoryRepository;
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;


    // Hàm sinh mã hóa đơn
    private String generateBillCode(String maxCode) {
        if (maxCode == null) {
            return "HD001"; // Mã đầu tiên
        }
        // Lấy số từ mã hiện tại và tăng lên 1
        int nextNumber = Integer.parseInt(maxCode.substring(2)) + 1;
        return String.format("HD%03d", nextNumber); // Định dạng HD001, HD002, ...
    }

    @Override
    public String createBill(BillEntity bill) {
        // Lấy mã hóa đơn lớn nhất hiện tại
        String maxCode = billRepository.findMaxBillCode();

        // Tạo mã hóa đơn mới
        String newCode = generateBillCode(maxCode);
        bill.setCode(newCode);
        bill.setStatus(0);
        bill.setDeleteFlag(0);

        // Lưu hóa đơn vào cơ sở dữ liệu
        BillEntity savedBill = billRepository.save(bill);

        // Tạo bản ghi BillHistoryEntity
        BillHistoryEntity billHistory = new BillHistoryEntity();
        billHistory.setBillId(savedBill.getId()); // Lấy ID của hóa đơn vừa lưu
        billHistory.setStatus(0); // Trạng thái = 0
        billHistory.setNote("Tạo đơn hàng thành công");

        // Lưu lịch sử hóa đơn vào cơ sở dữ liệu
        billHistoryRepository.save(billHistory);

        // Trả về thông báo thành công cùng mã hóa đơn
        return "Tạo hóa đơn thành công với mã: " + savedBill.getCode();
    }

    @Override
    public Page<ListBillDTO> getBillResponse(FindBillDTO req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());

        System.out.println(req);
        Page<BillResponse> pageResponse = billRepository.getBillResponse(pageable, req);

        // Map
        Page<ListBillDTO> pageDTO = pageResponse.map(billResponse ->
                modelMapper.map(billResponse, ListBillDTO.class)
        );

        return pageDTO;
    }

    @Override
    public Optional<ListBillDTO> getInfoBill(String id) {
        return billRepository.getInfoBill(id) // Sử dụng query tùy chỉnh
                .map(billResponse -> modelMapper.map(billResponse, ListBillDTO.class));
    }

    @Override
    public List<BillEntity> getBillWithStatus(Integer status,Integer deleteFlag) {
        return billRepository.findByStatusAndDeleteFlag(status, deleteFlag);
    }

    @Override
    public List<BillEntity> getBillAvailable(Integer status, Integer deleteFlag) {
        return billRepository.findByStatusAndDeleteFlag(status, deleteFlag);

    }

    @Override
    public String updateDeleteFlag(String id) {
        // Tìm hóa đơn theo ID
        Optional<BillEntity> existingBill = billRepository.findBillById(id);

        // Kiểm tra nếu hóa đơn không tồn tại
        if (!existingBill.isPresent()) {
            throw new RuntimeException("Hóa đơn không tồn tại");
        }

        // Lấy hóa đơn và cập nhật deleteFlag thành 1
        BillEntity billEntity = existingBill.get();
        billEntity.setDeleteFlag(1);

        // Lưu thay đổi vào cơ sở dữ liệu
        billRepository.save(billEntity);

        return "Cập nhật cờ deleteFlag thành công!";
    }

}

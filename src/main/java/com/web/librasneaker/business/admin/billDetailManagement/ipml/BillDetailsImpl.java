package com.web.librasneaker.business.admin.billDetailManagement.ipml;

import com.web.librasneaker.business.admin.billDetailManagement.service.BillDetailService;
import com.web.librasneaker.dto.billDetailManagement.BillDetailResponse;
import com.web.librasneaker.dto.billDetailManagement.CreateBillDetailDTO;
import com.web.librasneaker.dto.billDetailManagement.ListBillDetailDTO;
import com.web.librasneaker.dto.billHistoryManagement.BillHistoryResponse;
import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.entity.BillDetailEntity;
import com.web.librasneaker.entity.BillEntity;
import com.web.librasneaker.repository.BillDetailRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class BillDetailsImpl implements BillDetailService {
    private final ModelMapper modelMapper;
    private final BillDetailRepository billDetailRepository;

    @Override
    public List<ListBillDetailDTO> getBillDetails(String id, Integer deleteFlag) {
        // Fetch the list of responses from the repository
        List<BillDetailResponse> listResponse = billDetailRepository.getBillDetail(id, deleteFlag);

        List<ListBillDetailDTO> result = listResponse.stream()
                .map(response -> modelMapper.map(response, ListBillDetailDTO.class))
                .toList();

        return result;
    }

    @Override
    public String createBillDetail(CreateBillDetailDTO request) {

        BillDetailEntity billDetails = new BillDetailEntity();
        billDetails.setBillId(request.getBillId());
        billDetails.setProductDetailId(request.getProductDetailId());
        billDetails.setQuantity(request.getQuantity());
        billDetails.setPrice(request.getPrice());
        billDetails.setDeleteFlag(0);

        billDetailRepository.save(billDetails);

        return "Tạo hóa đơn chi tiết thành công!";
    }

    @Override
    public String updateDeleteFlag(String id) {
        // Tìm hóa đơn theo ID
        Optional<BillDetailEntity> existingBillDetails = billDetailRepository.findById(id);

        // Kiểm tra nếu hóa đơn không tồn tại
        if (!existingBillDetails.isPresent()) {
            throw new RuntimeException("Hóa đơn không tồn tại");
        }

        // Lấy hóa đơn và cập nhật deleteFlag thành 1
        BillDetailEntity billDetailsEntity = existingBillDetails.get();
        billDetailsEntity.setDeleteFlag(1);

        // Lưu thay đổi vào cơ sở dữ liệu
        billDetailRepository.save(billDetailsEntity);

        return "Cập nhật cờ deleteFlag thành công!";
    }
}

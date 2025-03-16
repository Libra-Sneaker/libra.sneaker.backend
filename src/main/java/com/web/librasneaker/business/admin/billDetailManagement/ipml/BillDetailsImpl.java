package com.web.librasneaker.business.admin.billDetailManagement.ipml;

import com.web.librasneaker.business.admin.billDetailManagement.service.BillDetailService;
import com.web.librasneaker.dto.billDetailManagement.BillDetailResponse;
import com.web.librasneaker.dto.billDetailManagement.CreateBillDetailDTO;
import com.web.librasneaker.dto.billDetailManagement.ListBillDetailDTO;
import com.web.librasneaker.dto.billHistoryManagement.BillHistoryResponse;
import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.entity.BillDetailEntity;
import com.web.librasneaker.entity.BillEntity;
import com.web.librasneaker.entity.ProductDetailEntity;
import com.web.librasneaker.repository.BillDetailRepository;
import com.web.librasneaker.repository.ProductDetailRepository;
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
    private final ProductDetailRepository productDetailRepository;

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

        // Tìm ProductDetailEntity để kiểm tra số lượng trong kho
        Optional<ProductDetailEntity> productDetailOpt = productDetailRepository.findById(request.getProductDetailId());

        if (!productDetailOpt.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại!");
        }

        ProductDetailEntity productDetail = productDetailOpt.get();

        // Tìm BillDetailEntity theo billId và productDetailId
        Optional<BillDetailEntity> existingBillDetailOpt = billDetailRepository.findByBillIdAndProductDetailId(
                request.getBillId(), request.getProductDetailId());

        if (existingBillDetailOpt.isPresent()) {
            // Nếu BillDetailEntity đã tồn tại, cộng dồn số lượng
            BillDetailEntity existingBillDetail = existingBillDetailOpt.get();
            int totalQuantity = existingBillDetail.getQuantity() + request.getQuantity();

            // Kiểm tra
            if (productDetail.getQuantity() < 0) {
                throw new RuntimeException("Số lượng sản phẩm trong kho không đủ!");
            }

            // Cập nhật số lượng trong BillDetailEntity
            existingBillDetail.setQuantity(totalQuantity);
            billDetailRepository.save(existingBillDetail);

            // Cập nhật tồn kho (số lượng trong kho phải giảm đi theo số lượng mới)
            productDetail.setQuantity(productDetail.getQuantity() - request.getQuantity());
            productDetailRepository.save(productDetail);

            return "Cập nhật hóa đơn chi tiết thành công!";
        } else {
            // Nếu BillDetailEntity chưa tồn tại, tạo mới
            if (request.getQuantity() > productDetail.getQuantity()) {
                throw new RuntimeException("Số lượng sản phẩm trong kho không đủ!");
            }

            BillDetailEntity newBillDetail = new BillDetailEntity();
            newBillDetail.setBillId(request.getBillId());
            newBillDetail.setProductDetailId(request.getProductDetailId());
            newBillDetail.setQuantity(request.getQuantity());
            newBillDetail.setPrice(request.getPrice());
            newBillDetail.setDeleteFlag(0);

            billDetailRepository.save(newBillDetail);

            // Cập nhật tồn kho
            productDetail.setQuantity(productDetail.getQuantity() - request.getQuantity());
            productDetailRepository.save(productDetail);

            // Cập nhật total_amount trong bill
//            Double totalAmount = billDetailRepository.findByBillId(request.getBillId()).stream()
//                    .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
//                    .sum();
//            bill.setTotalAmount(totalAmount);
//            billRepository.save(bill);

            return "Tạo hóa đơn chi tiết thành công!";
        }
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
        int quantityToRestore = billDetailsEntity.getQuantity();  // Lấy số lượng của BillDetail để cộng lại vào kho

        // Tìm ProductDetailEntity liên quan
        Optional<ProductDetailEntity> productDetailOpt = productDetailRepository.findById(billDetailsEntity.getProductDetailId());
        if (!productDetailOpt.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }

        ProductDetailEntity productDetail = productDetailOpt.get();

        // Cập nhật lại số lượng trong kho
        productDetail.setQuantity(productDetail.getQuantity() + quantityToRestore);
        productDetailRepository.save(productDetail);

        // Cập nhật cờ deleteFlag của BillDetail
        billDetailsEntity.setDeleteFlag(1);
        billDetailRepository.save(billDetailsEntity);

        return "Cập nhật cờ deleteFlag và phục hồi tồn kho thành công!";
    }

}

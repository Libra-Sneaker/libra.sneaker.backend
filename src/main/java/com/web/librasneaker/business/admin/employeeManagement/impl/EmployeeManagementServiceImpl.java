package com.web.librasneaker.business.admin.employeeManagement.impl;

import com.web.librasneaker.business.admin.employeeManagement.service.EmployeeManagementService;
import com.web.librasneaker.config.mailconfig.EmailSender;
import com.web.librasneaker.dto.employeeManagement.CreateEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.EmployeeResponse;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.UpdateEmployeeDTO;
import com.web.librasneaker.entity.EmployeeEntity;
import com.web.librasneaker.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final EmailSender emailSender;

    @Override
    public String createEmployee(CreateEmployeeDTO request) {

        // Kiểm tra email đã tồn tại
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email này đã tồn tại!");
        }

        // Lấy mã code cao nhất hiện có
        String maxCode = employeeRepository.findMaxCode();
        String newCode = generateNewCode(maxCode);

        // Tạo mật khẩu ngẫu nhiên 8 ký tự
        String generatedPassword = generateRandomPassword(8);

        // Tạo EmployeeEntity mới với mã code tự tăng
        EmployeeEntity employee = new EmployeeEntity();
        employee.setCode(newCode);
        employee.setName(request.getName());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setAddress(request.getAddress());
        employee.setPhone(request.getPhoneNumber());
        employee.setEmail(request.getEmail());
        employee.setSex(request.getSex());
        employee.setPassword(generatedPassword);
        employee.setAvatar(request.getAvatar());
        employee.setRole(request.getRole());
        employee.setDeleteFlag(0);

        // Lưu employee vào database
        employeeRepository.save(employee);

        Runnable emailTask = () -> {
            String htmlBody = "<p>Mật khẩu của bạn là:</p><br/><div style=\"text-align: center; font-weight: bold; font-size: 25px;\"><strong>" + generatedPassword + "</strong></div>";
            emailSender.sendEmail(new String[]{request.getEmail()}, "[LIBRA-SNEAKER] Thông báo mật khẩu", "Thông báo mật khẩu sau khi đăng ký tài khoản", htmlBody);
        };
        new Thread(emailTask).start();

        return "Employee created with code: " + newCode;
    }

    // Phương thức tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword(int length) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";
        String allowedChars = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }

    @Override
    public String updateEmployee(UpdateEmployeeDTO request) {
        // Tìm kiếm nhân viên theo mã nhân viên (code)
        Optional<EmployeeEntity> existingEmployeeOpt = employeeRepository.findByCode(request.getCode());

        if (!existingEmployeeOpt.isPresent()) {
            return "Không tìm thấy nhân viên với mã: " + request.getCode();
        }

        Optional<EmployeeEntity> findEmail = employeeRepository.findByEmailAndIdNot(request.getEmail(),request.getId());
        if (findEmail.isPresent()) {
            return "Email này đã tồn tại với nhân viên khác!";
        }

        // Lấy đối tượng EmployeeEntity cần cập nhật
        EmployeeEntity existingEmployee = existingEmployeeOpt.get();

        // Cập nhật các thông tin nhân viên
        existingEmployee.setName(request.getName());
        existingEmployee.setDateOfBirth(request.getDateOfBirth());
        existingEmployee.setAddress(request.getAddress());
        existingEmployee.setPhone(request.getPhoneNumber());
        existingEmployee.setEmail(request.getEmail());
        existingEmployee.setSex(request.getSex());
        existingEmployee.setDeleteFlag(request.getDeleteFlag());
        existingEmployee.setAvatar(request.getAvatar());
        existingEmployee.setRole(request.getRole());

        // Lưu lại thông tin đã cập nhật vào cơ sở dữ liệu
        employeeRepository.save(existingEmployee);

        return "Employee updated successfully with code: " + request.getCode();
    }

    @Override
    public Page<ListEmployeeDTO> searchEmployees(FindEmployeeDTO req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());

        Page<EmployeeResponse> pageResponse = employeeRepository.getEmployeeResponse(pageable, req);

        Page<ListEmployeeDTO> pageDTO = pageResponse.map(employeeResponse ->
                modelMapper.map(employeeResponse, ListEmployeeDTO.class)
        );

        return pageDTO;
    }

    @Override
    public Page<ListEmployeeDTO> searchEmployeesAllInOne(FindEmployeeDTO req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());

        Page<EmployeeResponse> pageResponse = employeeRepository.searchEmployee(pageable, req);

        Page<ListEmployeeDTO> pageDTO = pageResponse.map(employeeResponse ->
                modelMapper.map(employeeResponse, ListEmployeeDTO.class)
        );

        return pageDTO;
    }

    @Override
    public String updateStatusEmployee(String id, Integer deleteFlag) {
        Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(id);

        if (!existingEmployee.isPresent()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        EmployeeEntity employee = existingEmployee.get();
        employee.setDeleteFlag(deleteFlag);
        employeeRepository.save(employee);

        return "Cập nhật trạng thái nhân viên thành công!";
    }


    // gen code employee auto
    private String generateNewCode(String maxCode) {
        // Nếu maxCode là null, bắt đầu từ "EMP001"
        if (maxCode == null) {
            return "EMP001";
        }

        // Lấy phần số từ maxCode và tăng nó lên 1
        int currentNumber = Integer.parseInt(maxCode.substring(3));
        int newNumber = currentNumber + 1;

        // Định dạng mã mới với "EMP" + số tự tăng, có đủ 3 chữ số
        return String.format("EMP%03d", newNumber);
    }
}

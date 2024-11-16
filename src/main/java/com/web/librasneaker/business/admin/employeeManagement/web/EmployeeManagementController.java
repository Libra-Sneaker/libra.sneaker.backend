package com.web.librasneaker.business.admin.employeeManagement.web;

import com.web.librasneaker.business.admin.employeeManagement.service.EmployeeManagementService;
import com.web.librasneaker.dto.colorManagement.CreateColorDTO;
import com.web.librasneaker.dto.employeeManagement.CreateEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.UpdateEmployeeDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/employeeManagement")
public class EmployeeManagementController {
    private final EmployeeManagementService employeeManagementService;

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody CreateEmployeeDTO request) {
        try {
            String message = employeeManagementService.createEmployee(request);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody UpdateEmployeeDTO request) {
        String responseMessage = employeeManagementService.updateEmployee(request);
        return ResponseEntity.ok().body(responseMessage);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ListEmployeeDTO>> searchEmployees(FindEmployeeDTO request) {
        Page<ListEmployeeDTO> response = employeeManagementService.searchEmployees(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateStatusEmployee (@RequestParam String id, @RequestParam Integer deleteFlag) {
        return ResponseEntity.ok().body(employeeManagementService.updateStatusEmployee(id, deleteFlag));
    }



}

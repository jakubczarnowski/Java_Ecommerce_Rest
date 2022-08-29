package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.address.AddressUpdateDto;
import com.example.ecommerce.model.DeliveryAddress;
import com.example.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@PreAuthorize("isAuthenticated()")
@Validated
@RequestMapping("/address/")
public class AddressController {
    AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<DeliveryAddress> addAddress(@Valid @RequestBody DeliveryAddress address){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(service.addDeliveryAddress(address, username), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Set<DeliveryAddress>> getAddresses(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(service.getAddresses(username), HttpStatus.OK);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.deleteAddressById(id, username);
        return new ResponseEntity<>(new ApiResponse(true,"Address deleted"),HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<DeliveryAddress> updateAddress(@PathVariable Integer id, @Valid @RequestBody AddressUpdateDto address) {
        return new ResponseEntity<>(service.updateAddress(id, address), HttpStatus.OK);
    }



}

package com.aagam.address.controller;

import com.aagam.address.dto.AddressRequest;
import com.aagam.address.dto.AddressResponse;
import com.aagam.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public AddressResponse addAddress(
            @RequestBody AddressRequest request,
            Authentication authentication) {

        String phone = authentication.getName();
       // System.out.println("phONEnUMBER"+phone);
        return addressService.addAddress(request, phone);
    }

    @GetMapping
    public List<AddressResponse> getAddresses(Authentication authentication) {

        String phone = authentication.getName();


                List<AddressResponse> addr=addressService.getUserAddresses(phone);
        System.out.println(addr.toString());
        return  addr;
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(
            @PathVariable Long id,
            Authentication authentication) {

        String phone = authentication.getName();

        addressService.deleteAddress(id, phone);
    }
}
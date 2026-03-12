package com.aagam.address.service;

import com.aagam.address.dto.AddressRequest;
import com.aagam.address.dto.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse addAddress(AddressRequest request, String phone);

    List<AddressResponse> getUserAddresses(String phone);

    void deleteAddress(Long addressId, String phone);
}
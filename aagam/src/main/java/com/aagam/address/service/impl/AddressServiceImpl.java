package com.aagam.address.service.impl;

import com.aagam.address.dto.AddressRequest;
import com.aagam.address.dto.AddressResponse;
import com.aagam.address.entity.Address;
import com.aagam.address.mapper.AddressMapper;
import com.aagam.address.repository.AddressRepository;
import com.aagam.address.service.AddressService;
import com.aagam.user.entity.User;
import com.aagam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public AddressResponse addAddress(AddressRequest request, String phone) {

        User user = userRepository.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = AddressMapper.toEntity(request);
        address.setUser(user);

        Address saved = addressRepository.save(address);

        return AddressMapper.toResponse(saved);
    }

    @Override
    public List<AddressResponse> getUserAddresses(String phone) {

        User user = userRepository.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return addressRepository.findByUserId(user.getId())
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteAddress(Long addressId, String phone) {

        addressRepository.deleteById(addressId);
    }
}
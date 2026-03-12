package com.aagam.address.mapper;

import com.aagam.address.dto.AddressRequest;
import com.aagam.address.dto.AddressResponse;
import com.aagam.address.entity.Address;

public class AddressMapper {

    public static Address toEntity(AddressRequest dto) {

        return Address.builder()
                .houseNumber(dto.houseNumber())
                .street(dto.street())
                .landmark(dto.landmark())
                .city(dto.city())
                .state(dto.state())
                .pincode(dto.pincode())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();
    }

    public static AddressResponse toResponse(Address address) {

        return new AddressResponse(
                address.getId(),
                address.getHouseNumber(),
                address.getStreet(),
                address.getLandmark(),
                address.getCity(),
                address.getState(),
                address.getPincode(),
                address.getLatitude(),
                address.getLongitude()
        );
    }
}
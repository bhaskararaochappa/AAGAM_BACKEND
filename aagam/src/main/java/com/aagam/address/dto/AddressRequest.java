package com.aagam.address.dto;

public record AddressRequest(

        String houseNumber,
        String street,
        String landmark,
        String city,
        String state,
        String pincode,
        Double latitude,
        Double longitude
) {}
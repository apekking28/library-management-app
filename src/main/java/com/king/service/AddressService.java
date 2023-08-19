package com.king.service;

import com.king.entity.Address;
import com.king.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        Address newAddress =  Address.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .build();


        return addressRepository.save(newAddress);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alamat dengan id : " + id + " tidak ditemukan"));
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address updateAddress(Long id, Address address) {
        Address findAddress = getAddressById(id);

        Address updateAddress = Address.builder()
                .id(findAddress.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .build();

        return addressRepository.save(updateAddress);
    }

    public void deleteAddress(Long id) {
        Address findAddress = getAddressById(id);
        addressRepository.deleteById(id);
    }
}

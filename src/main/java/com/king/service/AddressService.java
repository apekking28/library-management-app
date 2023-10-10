package com.king.service;

import com.king.entity.Address;
import com.king.exception.NotFoundException;
import com.king.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        try {
            Address newAddress = Address.builder()
                    .street(address.getStreet())
                    .city(address.getCity())
                    .zipCode(address.getZipCode())
                    .build();

            log.info("Add new address");
            return addressRepository.save(newAddress);
        } catch (Exception ex) {
            log.error("An error occurred while add new address with error : {}", ex.getMessage());
            throw new RuntimeException("Failed to add new address");
        }
    }

    public Address getAddressById(Long id) {
        try {
            Address address = addressRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Address with id : " + id + " not found"));

            log.info("Address with ID {} found", id);
            return address;
        } catch (NotFoundException ex) {
            log.error("Error: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("An error occurred while fetching address with ID {}: {}", id, ex.getMessage());
            throw new RuntimeException("Failed to retrieve address by ID", ex);
        }
    }


    public List<Address> getAllAddresses() {
        try {
            log.info("Get all Address");
            return addressRepository.findAll();
        } catch (Exception ex) {
            log.error("An error occurred while get all address with error : {}", ex.getMessage());
            throw new RuntimeException("Failed to get all address");
        }
    }

    public Address updateAddress(Long id, Address address) {
        try {
            Address findAddress = getAddressById(id);

            Address updateAddress = Address.builder()
                    .id(findAddress.getId())
                    .street(address.getStreet())
                    .city(address.getCity())
                    .zipCode(address.getZipCode())
                    .build();

            log.info("Update address with ID {}", id);
            return addressRepository.save(updateAddress);
        } catch (Exception ex) {
            log.error("An error occurred while update address with ID {}", id);
            throw new RuntimeException("Failed to update address by ID", ex);
        }
    }

    public String deleteAddress(Long id) {
        try {
            Address findAddress = getAddressById(id);
            addressRepository.deleteById(id);

            log.info("Delete address with ID {}", id);
            return "Successfully delete address with id : " + id;
        } catch (Exception ex) {
            log.error("An error occurred while delete address with ID {}: {}", id, ex.getMessage());
            throw new RuntimeException("Failed to delete address by ID", ex);
        }
    }
}

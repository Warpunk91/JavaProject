package co.com.ps.c24a.service;

import co.com.ps.c24a.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Optional<Address> getAddressById(Long Id);
    Address saveAddress(Address address);
    Address updateAddress(Long Id, Address address);
    void deleteAddress (Long Id);
    List<Address> getAddressAll();
}

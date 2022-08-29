package com.example.ecommerce.service;

import com.example.ecommerce.Utils.AddressMapper;
import com.example.ecommerce.dto.address.AddressUpdateDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.DeliveryAddress;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AddressService {
    AddressRepository addressRepository;
    UserRepository userRepository;
    AddressMapper mapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository, AddressMapper mapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    public Set<DeliveryAddress> getAddresses(String username){
        return userRepository.findByUsername(username).get().getAddresses();
    }
    public void deleteAddressById(Integer id, String username){
        Optional<User> user = userRepository.findByUsername(username);
        Optional<DeliveryAddress> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw new NotFoundException("Address not found");
        }
        user.get().deleteAdress(address.get());
        addressRepository.deleteById(id);
    }
    public DeliveryAddress addDeliveryAddress(DeliveryAddress address, String username){
        DeliveryAddress address1 = addressRepository.save(address); // has its id
        Optional<User> user = userRepository.findByUsername(username);
        user.get().addAdress(address1);
        userRepository.save(user.get());
        return address1;
    }
    public DeliveryAddress updateAddress(Integer id, AddressUpdateDto address){
        Optional<DeliveryAddress> oldAddress = addressRepository.findById(id);
        if(oldAddress.isEmpty()){
            throw new NotFoundException("Address not found");
        }
        mapper.updateAddressFromDto(address, oldAddress.get());
        return addressRepository.save(oldAddress.get());
    }
}

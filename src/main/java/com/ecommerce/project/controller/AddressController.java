package com.ecommerce.project.controller;

import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.payload.AddressResponse;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO){

        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.addAddress(addressDTO, user);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @GetMapping("/addresses/user/address")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){

        User user = authUtil.loggedInUser();
        List<AddressDTO> addressDTOList =  addressService.getUserAddresses(user);
        return new ResponseEntity<List<AddressDTO>>(addressDTOList, HttpStatus.FOUND);
    }

    @GetMapping("/addresses/{userId}/address")
    public ResponseEntity<List<AddressDTO>> getAddressByUserId(@Valid @PathVariable Long userId){
        List<AddressDTO> addressDTOList = addressService.getAddressByUserId(userId);
        return new ResponseEntity<List<AddressDTO>>(addressDTOList, HttpStatus.FOUND);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@Valid @PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddressById(addressId);
        return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.FOUND);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDTO,
                                                    @PathVariable Long addressId){
        AddressDTO updatedAddressDTO = addressService.updateAddress(addressId, addressDTO);
        return new ResponseEntity<>(updatedAddressDTO, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> deleteAddress(@Valid @PathVariable Long addressId){
        AddressDTO deletedAddress = addressService.deleteAddress(addressId);
        return new ResponseEntity<AddressDTO>(deletedAddress, HttpStatus.OK);
    }
}

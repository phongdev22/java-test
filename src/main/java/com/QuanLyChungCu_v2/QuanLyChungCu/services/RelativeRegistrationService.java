package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.RelativeRegistrationDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.RelativeRegistration;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RelativeRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RelativeRegistrationService {

    @Autowired
    private RelativeRegistrationRepository repository;

    public List<RelativeRegistration> findAll() {
        return repository.findAll();
    }

    public Optional<RelativeRegistration> findById(Integer id) {
        return repository.findById(id);
    }

    public RelativeRegistration save(RelativeRegistrationDTO registration) {
        RelativeRegistration newRegistration = new RelativeRegistration();

        if (registration.getId() != null && repository.existsById(registration.getId())) {
            newRegistration = repository.findById(registration.getId()).get();
            newRegistration.setUserId(registration.getUserId());
            newRegistration.setRelativeName(registration.getRelativeName());
            newRegistration.setRelativePhone(registration.getRelativePhone());
            newRegistration.setRelationship(registration.getRelationship());
            newRegistration.setVehicleRegistrationNumber(registration.getVehicleRegistrationNumber());
            newRegistration.setRegistrationDate(registration.getRegistrationDate());
            newRegistration.setExpiryDate(registration.getExpiryDate());
        } else {
            newRegistration.setUserId(registration.getUserId());
            newRegistration.setRelativeName(registration.getRelativeName());
            newRegistration.setRelativePhone(registration.getRelativePhone());
            newRegistration.setRelationship(registration.getRelationship());
            newRegistration.setVehicleRegistrationNumber(registration.getVehicleRegistrationNumber());
            newRegistration.setRegistrationDate(new Date());
            newRegistration.setExpiryDate(registration.getExpiryDate());
            newRegistration.setCreatedAt(new Date());
            newRegistration.setUpdatedAt(new Date());
        }

        return repository.save(newRegistration);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

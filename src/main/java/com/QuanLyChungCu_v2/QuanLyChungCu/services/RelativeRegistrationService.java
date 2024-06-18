package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.RelativeRegistration;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RelativeRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public RelativeRegistration save(RelativeRegistration registration) {
        return repository.save(registration);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

package com.example.project.repository;

import com.example.project.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Override
    Page<RoleEntity> findAll(Pageable pageable);

    List<RoleEntity> findByIdIsNot(Long id);

}
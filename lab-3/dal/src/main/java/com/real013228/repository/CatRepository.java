package com.real013228.repository;

import com.real013228.entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long>, JpaSpecificationExecutor<CatEntity> {
    List<CatEntity> findAllByBreed(String breed);
}

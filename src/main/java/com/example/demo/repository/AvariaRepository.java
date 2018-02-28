package com.example.demo.repository;

import com.example.demo.model.Avaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvariaRepository extends JpaRepository<Avaria,Long>{
}

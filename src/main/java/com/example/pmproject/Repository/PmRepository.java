package com.example.pmproject.Repository;

import com.example.pmproject.Entity.Pm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PmRepository extends JpaRepository<Pm, Long> {
}

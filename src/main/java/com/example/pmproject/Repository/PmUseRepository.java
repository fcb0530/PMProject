package com.example.pmproject.Repository;

import com.example.pmproject.Entity.Pm;
import com.example.pmproject.Entity.PmUse;
import com.example.pmproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PmUseRepository extends JpaRepository<PmUse, Long> {

    @Query("select u From PmUse u Where u.pm.pmId = :pmId")
    List<Pm> findByPmId(@Param("pmId") Long pmId);

    @Query("select u from PmUse u where u.member.name = :memberName")
    List<Pm> findByMemberName(@Param("memberName") String memberName);
}

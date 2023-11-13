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
    List<PmUse> findByPmId(@Param("pmId") Long pmId);

    @Query("select u from PmUse u where u.member.name = :memberName")
    List<PmUse> findByMemberName(@Param("memberName") String memberName);

    @Query("SELECT p FROM PmUse p WHERE " +
            "(p.startLocation LIKE %:keyword% AND p.finishLocation IS NULL) OR " +
            "(p.finishLocation LIKE %:keyword% AND p.finishLocation IS NOT NULL)")
    List<PmUse> searchByLocation(@Param("keyword") String keyword);
}

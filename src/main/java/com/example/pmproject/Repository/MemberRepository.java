package com.example.pmproject.Repository;

import com.example.pmproject.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByName(String name);
}

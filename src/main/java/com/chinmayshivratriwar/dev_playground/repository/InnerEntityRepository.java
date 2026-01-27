package com.chinmayshivratriwar.dev_playground.repository;

import com.chinmayshivratriwar.dev_playground.entity.InnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InnerEntityRepository extends JpaRepository<InnerEntity, Long> {
}

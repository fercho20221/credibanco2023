package com.backend.credibanco.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.credibanco.Entity.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    CardEntity findByCardId(Long cardId);

    Optional<CardEntity> findById(Long Id);

    void deleteBycardId(Long cardId);

}

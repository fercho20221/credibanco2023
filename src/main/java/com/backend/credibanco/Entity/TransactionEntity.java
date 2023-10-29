package com.backend.credibanco.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@AllArgsConstructor
@Table(name = "transaction_entity")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "card_id_transaction")
    private Long cardId;
    private Integer transactionId;
    private Long balance;
    private Long price;
    private String transactionState;
    private LocalDateTime transactionTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private CardEntity cardEntity;

    public boolean isPresent() {
        return false;
    }
}

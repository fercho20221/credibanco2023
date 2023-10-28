package com.backend.credibanco.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@Table(name = "card_entity")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "card_id")
    private Long cardId;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private boolean enroll;
    private String currency;
    private Long balance;
    
    @JsonManagedReference  /// Esto es para evitar la referencia circular en Json de las transacciones
    @OneToMany(mappedBy = "cardEntity", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

}

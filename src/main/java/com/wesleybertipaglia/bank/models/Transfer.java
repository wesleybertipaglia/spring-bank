package com.wesleybertipaglia.bank.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_source_id", nullable = false)
    private Account accountSource;

    @ManyToOne
    @JoinColumn(name = "account_destination_id", nullable = false)
    private Account accountDestination;

    private float value;

    private TransactionType type;

    private TransactionStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Transfer() {
    }

    public Transfer(UUID id, float value, TransactionType type, TransactionStatus status) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.status = status;
    }

    public Transfer(Account accountSource, Account accountDestination, float value, TransactionType type,
            TransactionStatus status) {
        this.accountSource = accountSource;
        this.accountDestination = accountDestination;
        this.value = value;
        this.type = type;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(Account accountSource) {
        this.accountSource = accountSource;
    }

    public Account getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(Account accountDestination) {
        this.accountDestination = accountDestination;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}

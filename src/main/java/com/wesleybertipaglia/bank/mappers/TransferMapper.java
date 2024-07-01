package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.TransferDTO;
import com.wesleybertipaglia.bank.enums.TransactionType;
import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.models.Transfer;

public class TransferMapper {

    public static TransferDTO convertToDTO(Transfer transfer) {
        if (transfer == null) {
            return null;
        } else if (transfer.getId() == null) {
            throw new IllegalArgumentException("Transfer ID is required");
        } else if (transfer.getAccountSource() == null) {
            throw new IllegalArgumentException("Transfer account source is required");
        } else if (transfer.getAccountDestination() == null) {
            throw new IllegalArgumentException("Transfer account destination is required");
        } else if (transfer.getValue() == 0) {
            throw new IllegalArgumentException("Transfer value is required");
        }

        return new TransferDTO(transfer.getId(), transfer.getAccountSource().getId(),
                transfer.getAccountDestination().getId(), transfer.getValue(), transfer.getStatus().toString());
    }

    public static Transfer convertToEntity(TransferDTO transferDTO) {
        if (transferDTO == null) {
            return null;
        } else if (transferDTO.getId() == null) {
            throw new IllegalArgumentException("Transfer ID is required");
        } else if (transferDTO.getAccountSourceId() == null) {
            throw new IllegalArgumentException("Transfer account source is required");
        } else if (transferDTO.getAccountDestinationId() == null) {
            throw new IllegalArgumentException("Transfer account destination is required");
        } else if (transferDTO.getValue() == 0) {
            throw new IllegalArgumentException("Transfer value is required");
        }

        return new Transfer(transferDTO.getId(), transferDTO.getValue(), TransactionType.valueOf(transferDTO.getType()),
                TransactionStatus.valueOf(transferDTO.getStatus()));
    }

}

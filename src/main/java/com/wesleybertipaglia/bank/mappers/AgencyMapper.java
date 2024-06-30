package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.AgencyDTO;
import com.wesleybertipaglia.bank.models.Agency;

public class AgencyMapper {

    public static AgencyDTO convertToDTO(Agency agency) {
        if (agency == null) {
            return null;
        } else if (agency.getId() == null) {
            throw new IllegalArgumentException("Agency ID is required");
        } else if (agency.getBank() == null) {
            throw new IllegalArgumentException("Agency bank is required");
        } else if (agency.getNumber() == 0) {
            throw new IllegalArgumentException("Agency number is required");
        } else if (agency.getAddress() == null) {
            throw new IllegalArgumentException("Agency address is required");
        }

        return new AgencyDTO(agency.getId(), agency.getBank().getId(), agency.getNumber(), agency.getAddress());
    }

    public static Agency convertToEntity(AgencyDTO dto) {
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            throw new IllegalArgumentException("Agency ID is required");
        } else if (dto.getBankId() == null) {
            throw new IllegalArgumentException("Agency bank is required");
        } else if (dto.getNumber() == 0) {
            throw new IllegalArgumentException("Agency number is required");
        } else if (dto.getAddress() == null) {
            throw new IllegalArgumentException("Agency address is required");
        }

        Agency agency = new Agency();
        agency.setId(dto.getId());
        agency.setNumber(dto.getNumber());
        agency.setAddress(dto.getAddress());
        return agency;
    }

}

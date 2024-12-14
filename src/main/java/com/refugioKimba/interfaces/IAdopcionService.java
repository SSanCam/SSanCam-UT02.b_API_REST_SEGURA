package com.refugioKimba.interfaces;

import com.refugioKimba.dto.AdopcionDTO;
import com.refugioKimba.exception.GeneralException;

import java.util.List;

public interface IAdopcionService {
    AdopcionDTO insert(AdopcionDTO dto) throws GeneralException;
    AdopcionDTO getById(Long id) throws GeneralException;
    List<AdopcionDTO> getAll();
    AdopcionDTO modify(Long id, AdopcionDTO dto) throws GeneralException;
    void delete(Long id) throws GeneralException;
}

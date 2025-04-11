package com.VetFinal.dto.request;


import com.VetFinal. entity.Doctor ;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudFechaDisponible {
    private LocalDate fechaDisponible;
    private Doctor doctor;
}
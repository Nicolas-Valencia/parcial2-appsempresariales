package com.VetFinal.dto.response;



import com.VetFinal.entity.Animal;
import com.VetFinal.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaCita {
    private Long id;
    private LocalDateTime fechaCita;
    private Doctor doctor;
    private Animal animal;
}
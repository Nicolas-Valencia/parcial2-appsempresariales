package com.VetFinal.core.mapper;



import com.VetFinal.dto.request.SolicitudAnimal;
import com.VetFinal.dto.response.RespuestaAnimal;
import com.VetFinal.entity.Animal;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal comoEntidad(SolicitudAnimal solicitudAnimal);
    RespuestaAnimal comoSalida(Animal animal);
    List<RespuestaAnimal> comoSalida(List<Animal> listaAnimales);
    void actualizar(@MappingTarget Animal entidad, SolicitudAnimal solicitudAnimal);
}

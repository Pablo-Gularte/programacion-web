package ar.edu.centro8.daw.tp4dawgularte.dto;

import ar.edu.centro8.daw.tp4dawgularte.models.Auto;

public class AutoMapper {
    public static Auto toEntity(AutoRequestDTO dto) {
        Auto auto = new Auto();
        auto.setMarca(dto.getMarca());
        auto.setPrecio(dto.getPrecio());
        return auto;
    }

    public static AutoResponseDTO toResponseDTO(Auto auto) {
        return new AutoResponseDTO(auto.getMarca(), auto.getPrecio());
    }

    public static void updateEntity(Auto auto, AutoRequestDTO dto) {
        auto.setMarca(dto.getMarca());
        auto.setPrecio(dto.getPrecio());
    }
}

package ar.edu.centro8.daw.tp3dawgularte.dto;

import ar.edu.centro8.daw.tp3dawgularte.model.Auto;

public class AutoMapper {
    public static Auto toEntity(AutoRequestDTO dto) {
        Auto auto = new Auto();
        auto.setId(dto.getId());
        auto.setMarca(dto.getMarca());
        auto.setModelo(dto.getModelo());
        auto.setColor(dto.getColor());
        auto.setPatente(dto.getPatente());
        auto.setPrecio(dto.getPrecio());
        return auto;
    }

    public static AutoResponsetDTO toResponseDto(Auto auto) {
        return new AutoResponsetDTO(
            auto.getId(),
            auto.getMarca(),
            auto.getModelo(),
            auto.getPrecio(),
            auto.getColor(),
            auto.getPatente()
            );
    }

    public static void updateEntity(Auto auto, AutoRequestDTO dto) {
        auto.setMarca(dto.getMarca());
        auto.setModelo(dto.getModelo());
        auto.setPrecio(dto.getPrecio());
        auto.setColor(dto.getColor());
        auto.setPatente(dto.getPatente());
    }
}

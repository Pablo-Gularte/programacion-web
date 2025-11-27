package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Boletin;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IBoletinRepository;

@Service
public class BoletinService {

    @Autowired
    IBoletinRepository boletinRepo;

    public List<Boletin> obtenerTodosLosBoletines() {
        return boletinRepo.findAll();
    }

    public Boletin obtenerBoletinPorId(Long id) {
        return boletinRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró ningún boletín con ID " + id));
    }

    public Boletin crearBoletin(Boletin nuevoBoletin) {
        return boletinRepo.save(nuevoBoletin);
    }

    public Boletin modificarBoletin(Long id, Boletin boletin) {
        Boletin boletinExistente = obtenerBoletinPorId(id);
        
        // Actualizo los campos del boletín existente con los valores del boletín proporcionado
        if (boletin.getAnio() != null) boletinExistente.setAnio(boletin.getAnio());
        if (boletin.getBimestre() != null) boletinExistente.setBimestre(boletin.getBimestre());
        if (boletin.getNota() != null) boletinExistente.setNota(boletin.getNota());
        if (boletin.getAsignatura() != null) boletinExistente.setAsignatura(boletin.getAsignatura());
        
        return boletinRepo.save(boletinExistente);
    }

    public void eliminarBoletin(Long id) {
        Boletin boletinExistente = obtenerBoletinPorId(id);
        boletinRepo.delete(boletinExistente);
    }
}

package ar.edu.centro8.daw.tp1.ejercicio3.restaurante.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tp1.ejercicio3.restaurante.model.Menu;

@Service
public class MenuService {
    private List<Menu> platos;

    public MenuService() {
        this.platos = new ArrayList<>();
        platos.add(new Menu(1, "Parrillada para dos", 32000, "Tira de asado, vacío, achuras y porción de fritas."));
        platos.add(new Menu(2, "Filete de Salmón", 37000, "Salmón a la parrilla con puré de papas y espárragos."));
        platos.add(new Menu(3, "Milanesa napolitana", 29000, "Milanesa de ternera con salsa de tomate, muzzarella y puré de papas."));
        platos.add(new Menu(4, "Ravioles con estofado", 28000, "Ravioles rellenos de ricota con salsa tipo estofado y queso rallado"));
        platos.add(new Menu(5, "Tiramisú", 17000, "Postre italiano a base de café y queso mascarpone."));
    }

    public Menu obtenerPlato(int id) {
        for (Menu plato : platos) {
            if ( plato.getId() == id) return plato;
        }
        return null;
    }
}

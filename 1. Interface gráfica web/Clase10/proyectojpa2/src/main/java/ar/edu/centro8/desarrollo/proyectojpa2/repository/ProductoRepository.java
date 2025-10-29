package ar.edu.centro8.desarrollo.proyectojpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

import ar.edu.centro8.desarrollo.proyectojpa2.model.ProductoModel;

public interface ProductoRepository extends JpaRepository<ProductoModel,Integer> {

}

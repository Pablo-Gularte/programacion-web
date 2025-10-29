package ar.edu.centro8.daw.tpn7igw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.daw.tpn7igw.model.Auto;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
    
}

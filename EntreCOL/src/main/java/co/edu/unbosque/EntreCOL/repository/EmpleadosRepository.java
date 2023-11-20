package co.edu.unbosque.EntreCOL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.EntreCOL.model.Empleados;

public interface EmpleadosRepository extends CrudRepository<Empleados, Integer>{
	
	public Optional<Empleados> findByCodigo(Integer codigo);
	public List<Empleados> findAll();

}

package co.edu.unbosque.EntreCOL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.EntreCOL.model.NominaEmpleado;

public interface NominaEmpleadosRepository extends CrudRepository<NominaEmpleado, Integer>{
	
	public Optional<NominaEmpleado> findById(Integer id);
	
	public List<NominaEmpleado> findAll();
	
}

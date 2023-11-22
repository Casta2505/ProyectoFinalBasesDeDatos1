package co.edu.unbosque.EntreCOL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.EntreCOL.model.Vacaciones;

public interface VacacionesRepository extends CrudRepository<Vacaciones,Integer>{
	
	public Optional<Vacaciones> findById(Integer id);
	
	public List<Vacaciones> findAll();

}

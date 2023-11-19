package co.edu.unbosque.EntreCOL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.EntreCOL.model.Libros;

public interface LibrosRepository extends CrudRepository<Libros, Integer>{
	
	public Optional<Libros> findById(Integer idlibro);
	
	public List<Libros> findAll();
	
}

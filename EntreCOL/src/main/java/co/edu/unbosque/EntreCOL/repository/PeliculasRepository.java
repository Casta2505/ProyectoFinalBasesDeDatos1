package co.edu.unbosque.EntreCOL.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.EntreCOL.model.Peliculas;
import java.util.List;
import java.util.Optional;


public interface PeliculasRepository extends CrudRepository<Peliculas, Integer>{
	
	public Optional<Peliculas> findByIdPeliculas(Integer idPeliculas);
	
	public List<Peliculas> findAll();

}

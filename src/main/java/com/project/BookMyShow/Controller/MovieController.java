package com.project.BookMyShow.Controller;

import com.project.BookMyShow.DTO.MovieDTO;
import com.project.BookMyShow.DTO.ShowDTO;
import com.project.BookMyShow.Service.MovieService;
import com.project.BookMyShow.Service.MovieServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
	
	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieServiceImpl movieService) {
		this.movieService = movieService;		
	}

	@PostMapping("admin/movie")
	public ResponseEntity<MovieDTO> createNewMovie(@Valid @RequestBody MovieDTO movieDTO) {
		MovieDTO savedMovie = movieService.addNewMovie(movieDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
	}

	@GetMapping("/home/movies/city/{cityId}")
	public ResponseEntity<List<MovieDTO>> getAllMoviesFromCity(@PathVariable("cityId") Long cityId){
		List<MovieDTO> movies = movieService.getAllMoviesFromCity(cityId);
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}


	@GetMapping("public/movies")
	public ResponseEntity<List<MovieDTO>> getAllMovies() {
		List<MovieDTO> MoviesDTO = movieService.getAllMovies();
		return ResponseEntity.status(HttpStatus.CREATED).body(MoviesDTO);
	}
	



	
//	@PostMapping("test/admin/theatre")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<Theatre> createNewTheatre(@Valid @RequestBody TheatreDTO theatredto) {
//		Theatre savedTheatre= movieService.createTheatre(theatredto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedTheatre);
//	}
	
//	@PostMapping("admin/show")
//	public ResponseEntity<Show> createNewShow(@Valid @RequestBody ShowDTO showDTO) {
//		Show savedshow= movieService.createShow(showDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedshow);
//	}

}

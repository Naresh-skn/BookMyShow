package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.MovieDTO;
import com.project.BookMyShow.Entity.City;
import com.project.BookMyShow.Entity.Movie;
import com.project.BookMyShow.Entity.Show;
import com.project.BookMyShow.Entity.Theatre;
import com.project.BookMyShow.Repository.CityRepository;
import com.project.BookMyShow.Repository.MovieRepository;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.exception.GenException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

	
	private final TheatreRepository theatreRepository;
	private final ShowRepository showRepository;
	private final MovieRepository movieRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CityRepository cityRepository;


	public MovieServiceImpl(TheatreRepository theatreRepository, ShowRepository showRepository, MovieRepository movieRepository) {
		this.theatreRepository = theatreRepository;
		this.showRepository = showRepository;
		this.movieRepository = movieRepository;
	}


	public List<MovieDTO> getAllMovies(Long cityId) {
//		Optional<List<Long>> theatres = null;//theatreRepository.findByCityId(cityId);
//		if(theatres.isEmpty())
//			throw new GenException("No Theatres found in your City");
//		List<Long> theatreList = theatres.get();
//		Optional<List<Movie>> movies = showRepository.findByTheatreIds(theatreList);
//		if(movies.get().size()==0)
//			throw new GenException("No Movies found in your City");
		return null;
	}


	@Override
	public List<MovieDTO> getAllMovies() {
		List<Movie> movies = movieRepository.findAll();
		return movies.stream().map(movie->modelMapper.map(movie,MovieDTO.class)).toList();
	}

	public Map<String,List<Show>> getAllShows(Long city, Long movie) {
		Optional<List<Long>> theatres = Optional.empty();//theatreRepository.findByCityId(city);
		if(theatres.get().isEmpty())
			throw new GenException("No Theatres found in your City");
		List<Long> theatreList = theatres.get();
		
		Optional<List<Show>> shows = showRepository.findByTheatreIdsAndMovieId(theatreList,movie);
		if(shows.get().size()==0)
			throw new GenException("No Movies found in your City");
		List<Show> showList = shows.get();
		
		Map<String,List<Show>> map = new HashMap<>();
		
		for(Show eachShow: showList) {
			String threatreName = eachShow.getTheatre().getTheatreName();
			if(map.containsKey(threatreName)) {
				List<Show> show = map.get(threatreName);
				show.add(eachShow);
				map.put(threatreName, show);	
			}
			else
				map.put(threatreName, new ArrayList<>(Arrays.asList(eachShow)));
			
		}
		return map;
	}


	public MovieDTO addNewMovie(@Valid MovieDTO movieDTO) {
		Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie savedFromDb =  movieRepository.save(movie);
		return modelMapper.map(savedFromDb,MovieDTO.class);
	}

	@Override
	public List<MovieDTO> getAllMoviesFromCity(Long cityId) {

		City city = cityRepository.findById(cityId)
				.orElseThrow(()->new GenException("City Not Found"));

		List<Theatre> theatres = theatreRepository.findByCity(city);

		if(theatres.isEmpty())
			throw new GenException("No Theatre found in the City: "+city.getCityName());

		List<Movie> movies = showRepository.findDistinctMoviesByTheatreIn(theatres);
		if(movies.isEmpty())
			throw new GenException("No Movies found in the City: "+city.getCityName());

		return movies.stream()
				.map(movie -> modelMapper.map(movie,MovieDTO.class))
				.toList();
	}




	public List<Theatre> getthea() {
		// TODO Auto-generated method stub
		return theatreRepository.findAll();
	}


//	@Transactional
//	public Show createShow(@Valid ShowDTO showDTO) {
//Optional<Theatre> theatre = theatreRepository.findById(showDTO.getTheatreId());
//
//Optional<Movie> movie = movieRepository.findById(showDTO.getMovieId());
//
//		Show show= Show.builder()
//				.movie(movie.get())
//				.price(showDTO.getPrice())
//				.theatre(theatre.get())
//				.showTime(showDTO.getShowTime())
//				.build();
//		List<Seat> seats = new ArrayList<>();
//		for(int i=0;i<theatre.get().getCapacity();i++) {
//			Seat seat = Seat.builder()
//					//.theatre(theatre.get())
//					.seatNumber("s"+i)
//					.show(show)
//					.isBooked(Boolean.FALSE)
//					.build();
//			seats.add(seat);
//
//		}
//		Show Savedshow= showRepository.save(show);
//		List<Seat> savedSeats = seatRepository.saveAll(seats);
//		System.out.println(savedSeats);
//		return Savedshow;
//	}
	
	

}

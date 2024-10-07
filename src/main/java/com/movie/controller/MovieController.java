package com.movie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.movie.model.Movie;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	private static List<Movie> movies = new ArrayList<>();
	
	@GetMapping("/")
	public List<Movie> getAll() {
		return movies;
	}
	
	@GetMapping("/{id}")
	public Movie get(@PathVariable int id) {
		Movie movie = null;
		for (Movie m: movies) {
			if (m.getId() == id) {
				movie = m;
			}
		}
		return movie;
	}
	
	@PostMapping("")
	public Movie add(@RequestBody Movie movie) {
		// incoming movie needs to be assigned an id, regardless if
		// one is provided already. It should be 1 more than the max id 
		// of all the movie ids.
		//System.out.println(movie);
		int maxId = 0;
		for (Movie m: movies) {
			maxId = Math.max(maxId, m.getId());
		}
		maxId += 1;
		movie.setId(maxId);
		movies.add(movie);
		return movie;
	}
	
	@PutMapping("")
	public Movie update(@RequestBody Movie movie) {
		int idx = -1;
		for (int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getId() == movie.getId()) {
				idx = i;
				movies.set(i, movie);
				break;
			}
		}
		if (idx < 0) {
			System.err.println("Error updating movie - id not found.");
		}
		return movie;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		for (Movie m: movies) {
			if (m.getId() == id) {
				movies.remove(m);
				break;
			}
		}
		return "Movie removed";
	}
}

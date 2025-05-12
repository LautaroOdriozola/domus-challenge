package domus.challenge.service.impl;

import domus.challenge.client.MovieClient;
import domus.challenge.dto.MovieDto;
import domus.challenge.dto.MovieResponseDto;
import domus.challenge.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final MovieClient movieClient;

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieDto> allMovies = new ArrayList<>();
        MovieResponseDto firstPageResponse = movieClient.getMoviesByPage(1);

        if (firstPageResponse != null && firstPageResponse.getData() != null) {
            allMovies.addAll(firstPageResponse.getData());
            Integer totalPages = firstPageResponse.getTotal_pages();
            for (Integer page = 2; page <= totalPages; page++) {
                MovieResponseDto pageResponse = movieClient.getMoviesByPage(page);
                if (pageResponse != null && pageResponse.getData() != null) {
                    allMovies.addAll(pageResponse.getData());
                }
            }
        }

        return allMovies;
    }
}

package domus.challenge.service.impl;

import domus.challenge.dto.MovieDto;
import domus.challenge.service.DirectorsService;
import domus.challenge.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DirectorsServiceImpl implements DirectorsService {

    private final MoviesService moviesService;

    @Override
    public Map<String, List<String>> getDirectorsAboveThreshold(Integer threshold) {

        if (threshold < 0) {
            return Map.of("directors", List.of());
        }

        List<MovieDto> movies = moviesService.getAllMovies();
        Map<String, Integer> directorCountMap = new HashMap<>();

        for (MovieDto movie : movies) {
            String director = movie.getDirector();
            if (director != null && !director.isBlank()) {
                directorCountMap.put(director, directorCountMap.getOrDefault(director, 0) + 1);
            }
        }

        List<String> directors = directorCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();

        return Map.of("directors", directors);
    }

}

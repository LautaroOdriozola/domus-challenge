package domus.challenge.client;

import domus.challenge.dto.MovieResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "MovieClient", url = "${movie.api.url}")
public interface MovieClient {

    @GetMapping("${movie.api.search-endpoint}")
    MovieResponseDto getMoviesByPage(@RequestParam("page") Integer page);

}

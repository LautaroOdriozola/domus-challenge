package domus.challenge.controller;

import domus.challenge.service.DirectorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Movies", description = "Operations related to movies and directors")
@RequiredArgsConstructor
public class MoviesController {

    private final DirectorsService directorsService;

    @Operation(summary = "Get directors with more than a certain number of movies")
    @GetMapping("/directors")
    public ResponseEntity<?> getDirectorsAboveThreshold(@RequestParam(value = "threshold", required = false) Integer threshold) {
        if (threshold == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Threshold is required and must be a number"));
        }
        return ResponseEntity
                .ok(directorsService.getDirectorsAboveThreshold(threshold));
    }

}

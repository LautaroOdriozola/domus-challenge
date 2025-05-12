package domus.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<MovieDto> data;
}

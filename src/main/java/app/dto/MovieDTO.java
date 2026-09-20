package app.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDTO(int id, String title, double vote_average, double popularity, String release_date) {
}
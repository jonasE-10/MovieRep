package app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreditsDTO(List<ActorDTO> cast, List<CrewDTO> crew) {
}

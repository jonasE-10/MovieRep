package app.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TMDbClient {
    String apiKey = System.getenv("TMDB_API_KEY");

    public String getAllMoviesFromPage(int pageNumber){
        String moviesByPageURI = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page="+pageNumber+"&primary_release_date.gte=2021-09-15&sort_by=popularity.desc&with_origin_country=DK&with_original_language=da";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(moviesByPageURI))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMovie(int id) {
        String movieByIdURI = "https://api.themoviedb.org/3/movie/"+id+"?language=en-US";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(movieByIdURI))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCreditsByMovieId(int id) {
        String creditsByMovieIdURI = "https://api.themoviedb.org/3/movie/"+id+"/credits?language=en-US";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(creditsByMovieIdURI))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

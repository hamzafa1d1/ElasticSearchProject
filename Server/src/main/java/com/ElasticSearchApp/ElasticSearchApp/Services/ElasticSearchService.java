package com.ElasticSearchApp.ElasticSearchApp.Services;

import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchQuery;
import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchResponse;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import com.ElasticSearchApp.ElasticSearchApp.Models.SearchText;
import com.ElasticSearchApp.ElasticSearchApp.Utils.ImageUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ElasticSearchService {

    public static final String ElasticSearchServer = "http://localhost:9200";
    public static final String FlickerPhotos = "/flickrphotos/_search";
    private final WebClient webClient;
    public ElasticSearchService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ElasticSearchServer).build();
    }
    public Mono<ElasticSearchResponse> fetchDataFromElasticSearch(SearchText searchText) {
        return webClient
                .post()
                .uri(FlickerPhotos) // Replace with your API endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(generateQuery(searchText.Text))
                .retrieve()
                .bodyToMono(ElasticSearchResponse.class);
    }
    private ElasticSearchQuery generateQuery(String searchTerm){
        ElasticSearchQuery elasticSearchQuery = new ElasticSearchQuery();
        ElasticSearchQuery.Query query = new ElasticSearchQuery.Query();
        ElasticSearchQuery.MultiMatch multiMatch = new ElasticSearchQuery.MultiMatch();

        multiMatch.setQuery(searchTerm);
        multiMatch.setFuzziness("Auto");
        multiMatch.setFields(List.of("tags", "title"));

        query.setMulti_match(multiMatch);
        elasticSearchQuery.setQuery(query);
        return elasticSearchQuery;
    }

}

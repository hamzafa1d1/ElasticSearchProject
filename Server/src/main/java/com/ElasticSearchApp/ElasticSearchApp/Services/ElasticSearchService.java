package com.ElasticSearchApp.ElasticSearchApp.Services;

import com.ElasticSearchApp.ElasticSearchApp.Models.*;
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
    public Mono<ElasticSearchResponse> fetchDataFromElasticSearchImageEmbedding(ImageEmbedding imageEmbedding) {
        return webClient
                .post()
                .uri(FlickerPhotos) // Replace with your API endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(generateQueryForImageEmbeddingSearch(imageEmbedding))
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
    private ElasticSearchKnnQuery generateQueryForImageEmbeddingSearch(ImageEmbedding imageEmbedding){

        ElasticSearchKnnQuery elasticSearchKnnQuery = new ElasticSearchKnnQuery();
        ElasticSearchKnnQuery.KnnConfig knnConfig = new ElasticSearchKnnQuery.KnnConfig();

        knnConfig.k = 10;
        knnConfig.field = "vector";
        knnConfig.num_candidates = 100;
        knnConfig.query_vector = imageEmbedding.Embedding;
        elasticSearchKnnQuery.knn = knnConfig;
        return elasticSearchKnnQuery;
    }

}

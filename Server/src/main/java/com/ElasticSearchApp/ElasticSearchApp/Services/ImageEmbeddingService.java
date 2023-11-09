package com.ElasticSearchApp.ElasticSearchApp.Services;

import com.ElasticSearchApp.ElasticSearchApp.Models.ImageEmbedding;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ImageEmbeddingService {
    public static final String FastApiEndpoint = "http://localhost:8000";
    public static final String generateEmbedding = "/uploadfile";
    private final WebClient webClient;
    public ImageEmbeddingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(FastApiEndpoint).build();
    }

    public Mono<ImageEmbedding> getImageEmbedding(MultipartFile image) {
        return webClient
                .post()
                .uri(generateEmbedding)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(image)
                .retrieve()
                .bodyToMono(ImageEmbedding.class);
    }
}

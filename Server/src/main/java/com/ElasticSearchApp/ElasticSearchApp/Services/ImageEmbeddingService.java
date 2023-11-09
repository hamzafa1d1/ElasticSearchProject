package com.ElasticSearchApp.ElasticSearchApp.Services;

import com.ElasticSearchApp.ElasticSearchApp.Models.ImageEmbedding;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
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
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", image.getResource());

        return webClient
                .post()
                .uri(generateEmbedding)
                .contentType(MediaType.MULTIPART_FORM_DATA)  // Set content type only for the part, not for the entire request
                .body(BodyInserters.fromMultipartData(builder.build()))  // Add the image as a part
                .retrieve()
                .bodyToMono(ImageEmbedding.class);
    }
}

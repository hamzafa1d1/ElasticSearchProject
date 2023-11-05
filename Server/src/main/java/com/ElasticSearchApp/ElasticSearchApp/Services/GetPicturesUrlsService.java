package com.ElasticSearchApp.ElasticSearchApp.Services;

import com.ElasticSearchApp.ElasticSearchApp.Contracts.GetPicturesUrlsServiceInterface;
import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchResponse;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageEmbedding;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import com.ElasticSearchApp.ElasticSearchApp.Models.SearchText;
import com.ElasticSearchApp.ElasticSearchApp.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPicturesUrlsService implements GetPicturesUrlsServiceInterface {
    @Autowired
    ElasticSearchService elasticSearchService;
    @Autowired
    ImageEmbeddingService imageEmbeddingService;
   public List<ImageUrl> GetPicturesUrls(SearchText searchText){
       ElasticSearchResponse elasticSearchResponse = new ElasticSearchResponse();
       elasticSearchResponse = elasticSearchService.fetchDataFromElasticSearch(searchText).block();
       return ImageUtils.generateImageUrls(elasticSearchResponse);
    }
    public List<ImageUrl> GetPicturesUrlsFromImage(MultipartFile image){
        ImageEmbedding embedding =  imageEmbeddingService.getImageEmbedding(image).block();
        ElasticSearchResponse elasticSearchResponse = new ElasticSearchResponse();
        elasticSearchResponse = elasticSearchService.fetchDataFromElasticSearchImageEmbedding(embedding).block();
        return ImageUtils.generateImageUrls(elasticSearchResponse);
    }
}

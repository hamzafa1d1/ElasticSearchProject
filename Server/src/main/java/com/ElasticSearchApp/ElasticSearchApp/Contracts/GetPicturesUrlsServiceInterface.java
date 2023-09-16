package com.ElasticSearchApp.ElasticSearchApp.Contracts;

import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchResponse;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import com.ElasticSearchApp.ElasticSearchApp.Models.SearchText;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GetPicturesUrlsServiceInterface {

     List<ImageUrl> GetPicturesUrls(SearchText searchText);
}

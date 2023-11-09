package com.ElasticSearchApp.ElasticSearchApp.Utils;

import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchResponse;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    public static List<ImageUrl> generateImageUrls(ElasticSearchResponse elasticSearchResponse){
        List<ImageUrl> imageUrls = new ArrayList<>();
        int n = Math.min(10, elasticSearchResponse.hits.hits.size());
        for(int i=0;i<n;++i){
            ElasticSearchResponse.Hits.Hit.Source src = elasticSearchResponse.hits.hits.get(i)._source;
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.ImageUrl = src.url;
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }
}

package com.ElasticSearchApp.ElasticSearchApp.Utils;

import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchResponse;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    public static List<ImageUrl> generateImageUrls(ElasticSearchResponse elasticSearchResponse){
        List<ImageUrl> imageUrls = new ArrayList<>();
        int n = Math.min(10, elasticSearchResponse.hits.hits.size());
        for(int i=0;i<n;++i){
            ElasticSearchResponse.Hits.Hit.Source src = elasticSearchResponse.hits.hits.get(i)._source;
            imageUrls.add(generateUrl(src.flickr_server, src.flickr_secret, src.flickr_farm, src.id));
        }
        return imageUrls;
    }

    private static ImageUrl generateUrl(String flickr_server, String flickr_secret, String flickr_farm, String id){
        String url = "http://farm" + flickr_farm + ".staticflickr.com/" + flickr_server + "/" + id + "_"
                + flickr_secret + ".jpg";
        ImageUrl imageUrl = new ImageUrl();
        imageUrl.ImageUrl = url;
        return imageUrl;
    }
}

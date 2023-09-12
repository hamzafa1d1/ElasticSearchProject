package com.ElasticSearchApp.ElasticSearchApp.Services;

import com.ElasticSearchApp.ElasticSearchApp.Contracts.GetPicturesUrlsServiceInterface;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import com.ElasticSearchApp.ElasticSearchApp.Models.SearchText;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPicturesUrlsService implements GetPicturesUrlsServiceInterface {
   public List<ImageUrl> GetPicturesUrls(SearchText searchText){
        return new ArrayList<ImageUrl>();
    }
}

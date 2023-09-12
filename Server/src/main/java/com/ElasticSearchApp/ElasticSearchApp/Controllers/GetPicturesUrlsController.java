package com.ElasticSearchApp.ElasticSearchApp.Controllers;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import com.ElasticSearchApp.ElasticSearchApp.Models.SearchText;
import com.ElasticSearchApp.ElasticSearchApp.Services.GetPicturesUrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/ElasticSearchApp")
public class GetPicturesUrlsController {
    GetPicturesUrlsService getPicturesUrlsService;
    @GetMapping("/pictures")
    public List<ImageUrl> GetPicturesUrls(@RequestBody SearchText searchText){
        return getPicturesUrlsService.GetPicturesUrls(searchText);
    }
}

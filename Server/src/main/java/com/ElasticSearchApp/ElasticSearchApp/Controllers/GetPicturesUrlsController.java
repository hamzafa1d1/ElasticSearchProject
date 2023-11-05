package com.ElasticSearchApp.ElasticSearchApp.Controllers;
import com.ElasticSearchApp.ElasticSearchApp.Models.ElasticSearchResponse;
import com.ElasticSearchApp.ElasticSearchApp.Models.ImageUrl;
import com.ElasticSearchApp.ElasticSearchApp.Models.SearchText;
import com.ElasticSearchApp.ElasticSearchApp.Services.GetPicturesUrlsService;
import com.ElasticSearchApp.ElasticSearchApp.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/ElasticSearchApp")
public class GetPicturesUrlsController {
    @Autowired
    GetPicturesUrlsService getPicturesUrlsService;
    @PostMapping("/pictures")
    public List<ImageUrl> GetPicturesUrls(@RequestBody SearchText searchText){
        return getPicturesUrlsService.GetPicturesUrls(searchText);
    }
    @PostMapping("/uploaded-image")
    public List<ImageUrl> GetPicturesUrls(@RequestParam("file") MultipartFile image){
        return getPicturesUrlsService.GetPicturesUrlsFromImage(image);
    }
}

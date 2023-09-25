package com.ElasticSearchApp.ElasticSearchApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ElasticSearchResponse {
    public Hits hits;

    // Getters and setters for hits

    public static class Hits {
        public List<Hit> hits;

        // Getters and setters for hits

        public static class Hit {
            public Source _source;

            // Getters and setters for _source

            public static class Source {
                public String id;
                public String flickr_secret;
                public String flickr_server;
                public String flickr_farm;

                // Getters and setters for id, flickr_secret, flickr_server, flickr_farm
            }
        }
    }
}

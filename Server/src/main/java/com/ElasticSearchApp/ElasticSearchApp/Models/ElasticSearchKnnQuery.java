package com.ElasticSearchApp.ElasticSearchApp.Models;

import java.util.List;

public class ElasticSearchKnnQuery {
    public KnnConfig knn;
    public static class KnnConfig {
        public String field;
        public List<Double> query_vector;
        public int k;
        public int num_candidates;
    }
}

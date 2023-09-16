package com.ElasticSearchApp.ElasticSearchApp.Models;

import java.util.List;

public class ElasticSearchQuery {
    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public static class Query {
        private MultiMatch multi_match;

        public MultiMatch getMulti_match() {
            return multi_match;
        }

        public void setMulti_match(MultiMatch multi_match) {
            this.multi_match = multi_match;
        }
    }

    public static class MultiMatch {
        private String query;
        private String fuzziness;
        private List<String> fields;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getFuzziness() {
            return fuzziness;
        }

        public void setFuzziness(String fuzziness) {
            this.fuzziness = fuzziness;
        }

        public List<String> getFields() {
            return fields;
        }

        public void setFields(List<String> fields) {
            this.fields = fields;
        }
    }
}

package com.telstra.codechallenge.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Repository implements Serializable {

    @JsonProperty("items")
    private List<Item> items;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @NoArgsConstructor
    public static class Item {


        @JsonProperty("watchers_count")
        private String watchersCount;

        @JsonProperty("language")
        private String language;

        @JsonProperty("description")
        private String description;

        @JsonProperty("name")
        private String name;

        @JsonProperty("html_url")
        private String htmlUrl;

    }

}

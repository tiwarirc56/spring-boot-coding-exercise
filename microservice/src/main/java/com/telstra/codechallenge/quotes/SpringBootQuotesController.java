package com.telstra.codechallenge.quotes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SpringBootQuotesController {

    private static final String STARS = "stars";
    private static final String ORDER = "desc";
    private static final String PAGE = "1";
    private static String PER_PAGE = "10";

    @Value("${git.url}")
    private String gitUrl;

    private SpringBootQuotesService springBootQuotesService;

    @Autowired
    private RestTemplate restTemplate;

    public SpringBootQuotesController(
            SpringBootQuotesService springBootQuotesService) {
        this.springBootQuotesService = springBootQuotesService;
    }

    @RequestMapping(path = "/quotes", method = RequestMethod.GET)
    public List<Quote> quotes() {
        return Arrays.asList(springBootQuotesService.getQuotes());
    }

    @RequestMapping(path = "/quotes/random", method = RequestMethod.GET)
    public Quote quote() {
        return springBootQuotesService.getRandomQuote();
    }

    @RequestMapping(path = "/hottest/repo", method = RequestMethod.GET)
    public ResponseEntity hottestRepo(@RequestParam String count) throws IOException {
        if (!count.isEmpty()) {
            Integer i = 0;
            try {
                i = Integer.parseInt(count);
            } catch (NumberFormatException e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            List<Repository.Item> list = process(i);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private List process(Integer count) {

        String date = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String url = new StringBuilder(gitUrl + "?q=created:<" + date + "&sort=" +
                STARS + "&order=" + ORDER + "&page=" + PAGE + "&per_page=" + count).
                toString();
        Repository data = restTemplate.getForObject(url, Repository.class);

        return new ArrayList(data.getItems());
    }
}

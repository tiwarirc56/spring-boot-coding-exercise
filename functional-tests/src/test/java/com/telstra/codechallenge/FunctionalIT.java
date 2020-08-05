package com.telstra.codechallenge;

import com.intuit.karate.junit5.Karate;

public class FunctionalIT {
  @Karate.Test
  Karate testMicroservice() {
    return Karate.run("microservice").relativeTo(getClass());
  }

  @Karate.Test
  Karate testHelloWorld() {
    return Karate.run("helloworld").relativeTo(getClass());
  }

  @Karate.Test
  Karate testQuotes() {
    return Karate.run("quotes").relativeTo(getClass());
  }

  //Test method for given exercise.
  @Karate.Test
  Karate testRepos() {
    return Karate.run("repos").relativeTo(getClass());
  }

}
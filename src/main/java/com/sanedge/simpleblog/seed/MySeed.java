package com.sanedge.simpleblog.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("seeds")
@Component
public class MySeed implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

}

package com.adidas.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt"
        },
        glue = "com/adidas/step_defs",
        features = "src/test/resources/features",
        tags = "@wip")

public class CukesRunner {


}

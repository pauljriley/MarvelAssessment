package uk.me.paulriley.marvelassessment.test;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "features",
        glue = {"uk.me.paulriley.marvelassessment.cucumber.steps"},
        format = {"pretty",
                "html:/data/data/uk.me.paulriley.marvelassessment/cucumber-reports/cucumber-html-report",
                "json:/data/data/uk.me.paulriley.marvelassessment/cucumber-reports/cucumber.json",
                "junit:/data/data/uk.me.paulriley.marvelassessment/cucumber-reports/cucumber.xml"
        },
        tags = {"~@manual",
                "@main"}
)
class CucumberTestCase {
}

package options;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"},
		glue = {"passos"},
		features = {"src/test/funcionalidades/livro.feature"})
public class CucumberTestsLivro {}

package io.mdn;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.mdn.guice.ConfigModule;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class JsonFilterApplication extends Application<JsonFilterConfiguration> {

    public static void main(final String[] args) throws Exception {
        new JsonFilterApplication().run(args);
    }

    @Override
    public String getName() {
        return "JsonFilter";
    }

    @Override
    public void initialize(final Bootstrap<JsonFilterConfiguration> bootstrap) {
        GuiceBundle guiceBundle = GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new ConfigModule())
                .build();
        bootstrap
                .addBundle(guiceBundle);
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor()
                )
        );
    }

    @Override
    public void run(final JsonFilterConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

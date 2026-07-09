package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:ui.properties"
})

public interface AuthConfig extends Config {
    @Config.Key("auth.username")
    String username();

    @Config.Key("auth.password")
    String password();
}

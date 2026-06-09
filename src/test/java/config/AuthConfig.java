package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:ui.properties"
})

public interface AuthConfig extends Config {
    @Config.Key("auth.username")
    String username();

    @Config.Key("auth.password")
    String password();
}

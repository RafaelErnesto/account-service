package dev.com.config;

import io.smallrye.config.ConfigMapping;

import javax.enterprise.context.ApplicationScoped;

@ConfigMapping(prefix = "account")
@ApplicationScoped
public interface AccountConfigProperties {

    String tableName();
}

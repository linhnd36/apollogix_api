package com.apollogix.managerskill.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class ConfigurationApplication {

    /**
     * Creates and configures a MessageSource bean.
     *
     * This method creates a ReloadableResourceBundleMessageSource object and configures it with the basename
     * "classpath:static/messages" and the default encoding "UTF-8". The created MessageSource bean is then returned.
     *
     * @return the configured MessageSource bean
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:static/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Creates and configures an OpenAPI object.
     *
     * This method creates an OpenAPI object and configures it with a SecurityRequirement and SecurityScheme.
     * The SecurityRequirement is set to "Bearer Authentication" and the SecurityScheme is created using the
     * createAPIKeyScheme() method. The configured OpenAPI object is then returned.
     *
     * @return the configured OpenAPI object
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()));
    }

    /**
     * Creates and configures a SecurityScheme object for API key authentication.
     *
     * This method creates a SecurityScheme object and configures it with the type "HTTP",
     * the bearer format "JWT", and the scheme "bearer". The configured SecurityScheme object
     * is then returned.
     *
     * @return the configured SecurityScheme object
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}

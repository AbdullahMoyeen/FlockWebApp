package com.viiup.web.flock.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Niranjan on 1/28/2016.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.viiup.web.flock")
public class FlockRestAPIConfiguration {
}

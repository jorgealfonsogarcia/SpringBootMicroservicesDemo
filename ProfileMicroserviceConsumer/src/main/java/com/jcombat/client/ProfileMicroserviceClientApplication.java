package com.jcombat.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ProfileMicroserviceClientApplication {
	
	public final String profilesServiceUrl;

	public ProfileMicroserviceClientApplication(@Value("${profiles.service.url}") String profilesServiceUrl) {
		this.profilesServiceUrl = profilesServiceUrl;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProfileMicroserviceClientApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public ProfileRepository profileRepository(){
		return new RemoteProfileRepository(profilesServiceUrl);
	}
}
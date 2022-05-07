package es.urjc.dad.leaguesports;


import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@EnableHazelcastHttpSession
@EnableCaching
@SpringBootApplication
public class LeaguesportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaguesportsApplication.class, args);
	}

	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();

		joinConfig.getMulticastConfig().setEnabled(true);
		joinConfig.getTcpIpConfig().setEnabled(false);
		config.addMapConfig(new MapConfig().setName("players"));
		return config;
	}

	@Bean
	public HazelcastInstance hazelcastInstance(Config config){
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public CacheManager cacheManager(HazelcastInstance hazelcastInstance){
		return new HazelcastCacheManager(hazelcastInstance);
	}

}

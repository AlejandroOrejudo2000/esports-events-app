package es.urjc.dad.leaguesports;

import java.util.Collections;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
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
		joinConfig.getTcpIpConfig().setEnabled(false)

			.setMembers(Collections.singletonList("127.0.0.1"));

		return config;
	}

	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager("players");
	}

}

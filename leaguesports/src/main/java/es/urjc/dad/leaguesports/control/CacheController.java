package es.urjc.dad.leaguesports.control;

import java.util.Map;

import com.hazelcast.spring.cache.HazelcastCacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CacheController {
    
    @Autowired CacheManager cacheManager;

    @GetMapping("/cache")
    public Map<Object, Object> getCacheContent(){
        HazelcastCacheManager manager = (HazelcastCacheManager) cacheManager;
        return manager.getHazelcastInstance().getMap("players");
    }
}

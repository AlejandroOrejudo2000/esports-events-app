package es.urjc.dad.leaguesports.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CacheController {
    
    @Autowired CacheManager cacheManager;

    @GetMapping("/cache")
    public Map<Object, Object> getCacheContent(){
        ConcurrentMapCacheManager manager = (ConcurrentMapCacheManager) cacheManager;
        ConcurrentMapCache cache = (ConcurrentMapCache) manager.getCache("players");    
        return cache.getNativeCache();
    }
}

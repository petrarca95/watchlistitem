package com.openclassrooms.watchlist.config;

import com.openclassrooms.watchlist.repository.WatchlistitemRepository;
import com.openclassrooms.watchlist.service.MovieRatingService;
import com.openclassrooms.watchlist.service.MovieRatingServiceDummyImpl;
import com.openclassrooms.watchlist.service.MovieRatingServiceLiveImpl;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
** Note we do not need to instantiate an ApplicationContext Annotation implementation to add all our beans to a container. It seems like Spring Auto Component Scanning picks this class up.
 ** and beans defined in here are registered in a container.
 */
//*either @Profile or @ConditionalOnProperty can be used

//* @Configuration class was scanned automatically (because of @SpringBootApplication)
//* If XML is used to configure and inject beans, we need to load the XML to a configuration class, either this one or the one defined by @SpringBootApplication
@Configuration
public class AppConfig {

    @Bean
//    @ConditionalOnProperty(name = "mode", havingValue = "dev")
    @Profile("dev")
    public WatchlistService watchListServiceDev(){
        //* Here we perform DI using java instead of annotation (@Autowired), DI here is still done by Spring container also achieving loose coupling
        return new WatchlistService(watchlistitemRepository(), MovieRatingServiceDummy());
    }

    @Bean
//    @ConditionalOnProperty(name = "mode", havingValue = "prod")
    @Profile("prod")
    public WatchlistService watchListServiceProd(){
        return new WatchlistService(watchlistitemRepository(), MovieRatingServiceLive());
    }

    @Bean
    public WatchlistitemRepository watchlistitemRepository(){
        return new WatchlistitemRepository();
    }

    @Bean
    public MovieRatingService MovieRatingServiceLive(){
        return new MovieRatingServiceLiveImpl();
    }

    @Bean
    public MovieRatingService MovieRatingServiceDummy(){
        return new MovieRatingServiceDummyImpl();
    }


}

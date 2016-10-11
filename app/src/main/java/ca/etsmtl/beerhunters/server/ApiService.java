package ca.etsmtl.beerhunters.server;

import ca.etsmtl.beerhunters.model.BeerContainerResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by admin on 10/10/2016.
 */
public interface ApiService {
    @GET("beers")
    Observable<BeerContainerResponse> getBeers(@Query("key") String key, @Query("styleId") int styleID, @Query("p") int page, @Query("withBreweries") String withBreweries);

}

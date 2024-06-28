package adapters.recomendadorUbicaciones;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRecomendarAdapter {
  @GET("getPuntosRecomendados")
  Call <ListaPuntosReferenciados> getPuntosRecomendados(@Query("lat") double lat
          , @Query("lon") double lon,
                                                        @Query("radio") double radio);

}


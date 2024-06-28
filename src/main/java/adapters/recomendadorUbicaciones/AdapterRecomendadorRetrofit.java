package adapters.recomendadorUbicaciones;

import domain.accesorios.PuntoUbicacion;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class AdapterRecomendadorRetrofit implements AdapterRecomendadorUbicaciones {
    @Getter
    @Setter
    private static String url="https://9202a509-840f-45cd-8320-7cada690550f.mock.pstmn.io/";
    private static AdapterRecomendadorRetrofit instanciaDeRecomendador=null;
    private Retrofit myRetrofit;

    @Override
  public List<PuntoUbicacion> recomendarUbicaciones(PuntoUbicacion punto,double radio) throws IOException {
      IRecomendarAdapter recomendador=this.myRetrofit.create(IRecomendarAdapter.class);
      Call<ListaPuntosReferenciados> requestPuntos=recomendador.getPuntosRecomendados(punto.getLatitud(), punto.getLongitud(), radio);
      Response<ListaPuntosReferenciados> response=requestPuntos.execute();
      System.out.println("cant. puntos Recomendados: "+response.body().getPuntos().size());
    return response.body().getPuntos();
  }
  private AdapterRecomendadorRetrofit(){
      this.myRetrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
  }

    public static AdapterRecomendadorRetrofit getInstanciaRecomendador(){
      if(  instanciaDeRecomendador==null){
            instanciaDeRecomendador=new AdapterRecomendadorRetrofit() ;
      }
      return    instanciaDeRecomendador;
    }

}

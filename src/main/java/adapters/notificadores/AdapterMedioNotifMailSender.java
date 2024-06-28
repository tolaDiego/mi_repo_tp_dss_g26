package adapters.notificadores;

import adapters.notificadores.AdapterMedioNotificacion;
import adapters.notificadores.Mensaje;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import domain.accesorios.Contacto;

public class AdapterMedioNotifMailSender implements AdapterMedioNotificacion {
    private final String  apiKey="re_frsxaaWH_Gb7F15z6WYxm2U3VaAgeyTfb";

    @Override
        public  void notificar(Contacto contacto, Mensaje mensaje){
        Resend resend = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(""+contacto.getContacto())
                .subject("sistema_viandas")
                .html("<strong>hello !!"+ mensaje+"</strong>")
                .build();
        try {
           resend.emails().send(params);
       } catch (ResendException e) {
           e.printStackTrace();
        }
    }

}

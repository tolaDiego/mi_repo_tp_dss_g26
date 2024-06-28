package adapters.notificadores;


import adapters.notificadores.AdapterMedioNotificacion;
import adapters.notificadores.Mensaje;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
        import domain.accesorios.Contacto;

public class AdapterMedioNotifwhatsapp implements AdapterMedioNotificacion {
    private final String cuentaSID="AC84d33ad9eaae2c90c930f56005c1ce51";
    private  final String tokenAuth="dc4c7c9bfc456d34d74eb4ef25ce84d7";
    // Find your Account Sid and Token at twilio.com/console
    @Override
   public void notificar(Contacto contacto, Mensaje mensaje){
       Twilio.init(cuentaSID,tokenAuth);
       Message message = Message.creator(
               new com.twilio.type.PhoneNumber("whatsapp:+549"+contacto.getContacto()),
               new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
               mensaje.getDescripcion())
               .create();

       System.out.println("wp sid:  "+message.getSid());
   }

}

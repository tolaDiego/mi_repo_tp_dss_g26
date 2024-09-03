package adapters.notificadores;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import domain.accesorios.Contacto;

public class AdapterMedioNotifwhatsapp implements AdapterMedioNotificacion {
    private final String cuentaSID="ACce861d085207ce586bc3951a61beb59b";
    private  final String tokenAuth="d24d8113cb5b9ba35b4248ed4a9cbd39";
    // Find your Account Sid and Token at twilio.com/console
    @Override
   public void notificar(Contacto contacto, Mensaje mensaje){
       Twilio.init(cuentaSID,tokenAuth);
       Message message = Message.creator(
               new com.twilio.type.PhoneNumber("whatsapp:+549"+contacto.getContacto()),
               new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
               mensaje.getDescripcion())
               .create();

       System.out.println("wp mensaje enviado, sid :  "+message.getSid());
   }

}

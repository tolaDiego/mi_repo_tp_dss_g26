package domain.converters;

import adapters.notificadores.AdapterMedioNotifMailSender;
import adapters.notificadores.AdapterMedioNotifTelegram;
import adapters.notificadores.AdapterMedioNotificacion;
import adapters.notificadores.AdapterMedioNotifwhatsapp;
import org.glassfish.jersey.internal.util.collection.StringIgnoreCaseKeyComparator;

import javax.persistence.AttributeConverter;

public class MedioDeNotificacionConverter implements AttributeConverter<AdapterMedioNotificacion,String> {


    @Override
    public String convertToDatabaseColumn(AdapterMedioNotificacion adapterMedioNotificacion) {
        if(adapterMedioNotificacion==null){
            return  null;
        }
        String tipoMedio="";
        if(adapterMedioNotificacion instanceof AdapterMedioNotifMailSender){
            tipoMedio="MAIL";
        }else if(adapterMedioNotificacion instanceof AdapterMedioNotifwhatsapp){
            tipoMedio="WP";
        }else if(adapterMedioNotificacion instanceof AdapterMedioNotifTelegram){
            tipoMedio="TELEGRAM";
        }
        return  tipoMedio;
    }

    @Override
    public AdapterMedioNotificacion convertToEntityAttribute(String tipoMedio) {
        if(tipoMedio==null){
            return null;
        }
        AdapterMedioNotificacion medioEnvio=null;
        switch (tipoMedio){
            case "WP": medioEnvio=new AdapterMedioNotifwhatsapp(); break;

            case "MAIL": medioEnvio=new AdapterMedioNotifMailSender(); break;

            case "TELEGRAM": medioEnvio=new AdapterMedioNotifTelegram(); break;
        }
    return  medioEnvio;


    }
}

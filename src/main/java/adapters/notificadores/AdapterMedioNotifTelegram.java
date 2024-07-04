package adapters.notificadores;

import adapters.notificadores.AdapterMedioNotificacion;
import adapters.notificadores.Mensaje;
import domain.accesorios.Contacto;
import lombok.Getter;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Getter
public class AdapterMedioNotifTelegram implements AdapterMedioNotificacion {

        private final String chatId="5118817082";

    @Override
    public  void notificar(Contacto contacto, Mensaje mensaje){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            MyTelegramBot bot=MyTelegramBot.getInstanciaBot() ;
            botsApi.registerBot(bot);

            bot.sendMessage(contacto.getContacto(), mensaje.getDescripcion());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private static String buscarIDCharConPor(String telefono) {
    //TODO
        return "123456789";
    }
}

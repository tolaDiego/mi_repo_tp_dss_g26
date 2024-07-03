package adapters.notificadores;

import adapters.notificadores.AdapterMedioNotificacion;
import adapters.notificadores.Mensaje;
import domain.accesorios.Contacto;
import lombok.Getter;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Getter
public class AdapterMedioNotifTelegram implements AdapterMedioNotificacion {
        private final  String botToken="6830980434:AAGV8IASlyv8g36voT2daYYLDAOWSM3w5vE";
        private final String chatId="5118817082";

    @Override
    public  void notificar(Contacto contacto, Mensaje mensaje){

        String messageText = "¡Hola! Este es un mensaje de prueba desde mi aplicación Java.";
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            MyTelegramBot bot = new MyTelegramBot(this.botToken);
            botsApi.registerBot(bot);

            bot.sendMessage(chatId, messageText);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private static String buscarIDCharConPor(String telefono) {
    //TODO
        return "123456789";
    }
}

package adapters.notificadores;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTelegramBot extends TelegramLongPollingBot {
    private static MyTelegramBot instanciaBot=null;
    private final String botToken;

    private MyTelegramBot() {
        this.botToken ="6830980434:AAGV8IASlyv8g36voT2daYYLDAOWSM3w5vE";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    public static MyTelegramBot getInstanciaBot(){

        if(instanciaBot==null){
            instanciaBot=new MyTelegramBot();
        }
        return instanciaBot;
    }

    @Override
    public String getBotUsername() {
        return "NotificadorSistViandasBot";  // Reemplaza con el nombre de tu bot
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Aquí puedes manejar actualizaciones entrantes
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String messageText = update.getMessage().getText();

            // Aquí puedes almacenar o usar el chatId
            System.out.println("Chat ID: " + chatId);
            System.out.println("Message Text: " + messageText);

            // Responder al usuario
            sendMessage(chatId, "Tu ID de chat es: " + chatId);
        }

    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

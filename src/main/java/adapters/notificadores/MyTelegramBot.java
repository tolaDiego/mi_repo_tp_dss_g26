package adapters.notificadores;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTelegramBot extends TelegramLongPollingBot {

    private String botToken;

    public MyTelegramBot(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public String getBotToken() {
        return botToken;
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
package ucb.edu.bo.sabicovid19.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.bo.sabicovid19.bot.bl.BotBl;

import java.util.List;

@Slf4j
public class MainBot extends TelegramLongPollingBot {
    private BotBl botBl;

    public MainBot(BotBl botBl) {
        this.botBl = botBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.getMessage().getText());
        log.info(update.getMessage().getFrom().getId().toString());
        if (update.hasMessage() && update.getMessage().hasText()) {
            List<String> messages = botBl.processUpdate(update);
            messages.forEach(s -> {
                // Create a SendMessage object with mandatory fields
                SendMessage message = new SendMessage()
                        .enableMarkdown(true)
                        .setChatId(update.getMessage().getChatId())
                        .setText(s);
                try {
                    this.execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public String getBotUsername() {
        return "Agendafinal_bot";
    }

    @Override
    public String getBotToken() {
        return "1041844175:AAF83u1AxL4YN-zKT39ry-9MJ-WwdIEpHT4";
    }
}

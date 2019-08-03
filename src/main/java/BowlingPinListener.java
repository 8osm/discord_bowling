

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

import javax.annotation.Nonnull;

public class BowlingPinListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        Guild guild = event.getGuild();
        VoiceChannel bowling = guild.getVoiceChannelsByName("bowling", true).get(0);
        AudioManager audioManager = guild.getAudioManager();
        if (content.equals("!connect")) {
            MessageChannel channel = event.getChannel();
            message.addReaction("\ud83c\udd97").queue();
            audioManager.openAudioConnection(bowling);
        }
    }
}

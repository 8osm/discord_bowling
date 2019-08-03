import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;


public class BowlingBallListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
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
        if(content.equals("!disconnect")){
            MessageChannel channel = event.getChannel();
            message.addReaction("\ud83c\udd97").queue();
            audioManager.closeAudioConnection();
            BotConnector.pins.get(0).getAudioManagers().get(0).closeAudioConnection();
            for (JDA pin : BotConnector.pins){
                pin.getAudioManagers().get(0).closeAudioConnection();
            }
        }
        if(content.equals("!strike")){
            AudioHelper helper = new AudioHelper();
            MessageChannel channel = event.getChannel();
            AudioManager ballAudio = BotConnector.ball.getAudioManagers().get(0);
            message.addReaction("\ud83c\udd97").queue();
            GuildMusicManager musicManager = helper.getGuildAudioPlayer(ballAudio, event.getGuild());
            try {
                    BotConnector.pins.get(0).getAudioManagers().get(0);
            }catch (IndexOutOfBoundsException e ){
                channel.sendMessage("Refill first!");
            }
            finally {
                helper.loadAndPlay(musicManager, "https://www.youtube.com/watch?v=YbtGuGpzVIk");
                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){

                }
                BotConnector.pins.get(0).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(1).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(2).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(3).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(4).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(5).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(6).getAudioManagers().get(0).closeAudioConnection();
                BotConnector.pins.get(7).getAudioManagers().get(0).closeAudioConnection();
            }
        }
        if(content.equals("!refill")){
            MessageChannel channel = event.getChannel();
            message.addReaction("\ud83c\udd97").queue();
            for (JDA pin : BotConnector.pins){
                pin.getAudioManagers().get(0).openAudioConnection(bowling);
            }
        }

    }
}

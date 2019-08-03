

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class BotConnector extends ListenerAdapter {
    public static ArrayList<JDA> pins = new ArrayList<>();
    public static JDA ball;
public static void connectBots()throws LoginException {
    final String BALL_TOKEN = "";
    final String[] PIN_TOKENS = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",

    };
    for (String PIN_TOKEN : PIN_TOKENS) {
        pins.add(new JDABuilder(PIN_TOKEN).build());
    }
    for (JDA pin : pins) {
        pin.addEventListener(new BowlingPinListener());
    }
    ball = new JDABuilder(BALL_TOKEN).build();
    ball.addEventListener(new BowlingBallListener());
}
}

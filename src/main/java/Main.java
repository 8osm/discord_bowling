import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

import jdk.nashorn.internal.scripts.JD;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


import java.util.ArrayList;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        BotConnector.connectBots();
    }
}
package tech.nevets.nevetsbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.simpleyaml.configuration.file.YamlFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class Bot {
    public static JDA jda;
    private static YamlFile config;
    public static String prefix;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    public static void main(String[] args) throws Exception {
        Config.loadConfig();
        String token = Config.getConfig().getString("bot.token");
        config = Config.getConfig();
        prefix = Config.getConfig().getString("bot.prefix");

        jda = JDABuilder.createDefault(token)
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE
                ))
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Listener())
                .build();
        getActivity();
    }

    public static void getActivity() {
    }
}
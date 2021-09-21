package tech.nevets.nevetsbot.commands.pics;

import net.dv8tion.jda.api.EmbedBuilder;
import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.CommandContext;
import tech.nevets.nevetsbot.commands.ICommand;
import tech.nevets.nevetsbot.util.httprequests.temp.FoxRequest;

import java.io.IOException;

public class FoxCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try { FoxRequest.getHttpConnection(); } catch (IOException | InterruptedException e) { e.printStackTrace(); }
        EmbedBuilder eb = new EmbedBuilder();

        ctx.getChannel().sendTyping().queue();
        eb.setImage(FoxRequest.url);
        ctx.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "fox";
    }

    @Override
    public String getHelp() {
        return "Sends a picture of a fox!\n" +
                "Usage: `" + Bot.prefix + "fox`";
    }
}

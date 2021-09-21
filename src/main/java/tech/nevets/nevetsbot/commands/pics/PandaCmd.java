package tech.nevets.nevetsbot.commands.pics;

import net.dv8tion.jda.api.EmbedBuilder;
import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.CommandContext;
import tech.nevets.nevetsbot.commands.ICommand;
import tech.nevets.nevetsbot.util.httprequests.temp.PandaRequest;

import java.io.IOException;

public class PandaCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try { PandaRequest.getHttpConnection(); } catch (IOException | InterruptedException e) { e.printStackTrace(); }
        EmbedBuilder eb = new EmbedBuilder();

        ctx.getChannel().sendTyping().queue();
        eb.setImage(PandaRequest.url);
        ctx.getChannel().sendMessageEmbeds(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "panda";
    }

    @Override
    public String getHelp() {
        return "Sends a picture of a panda!\n" +
                "Usage: `" + Bot.prefix + "panda`";
    }
}

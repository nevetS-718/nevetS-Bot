package tech.nevets.nevetsbot.commands.games;

import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.CommandContext;
import tech.nevets.nevetsbot.commands.ICommand;
import tech.nevets.nevetsbot.util.httprequests.DadJoke;

import java.io.IOException;
import java.util.List;

public class DadJokeCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try { DadJoke.getHttpConnection(); } catch (IOException | InterruptedException e) { e.printStackTrace(); }

        ctx.getChannel().sendTyping().queue();
        ctx.getChannel().sendMessage(DadJoke.joke).queue();
    }

    @Override
    public String getName() {
        return "dadjoke";
    }

    @Override
    public String getHelp() {
        return "Sends a dad joke :P\n" +
                "Usage: `" + Bot.prefix + "dadjoke`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("joke");
    }
}

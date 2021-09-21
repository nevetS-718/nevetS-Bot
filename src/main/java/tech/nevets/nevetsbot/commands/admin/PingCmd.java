package tech.nevets.nevetsbot.commands.admin;

import net.dv8tion.jda.api.JDA;
import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.CommandContext;
import tech.nevets.nevetsbot.commands.ICommand;

public class PingCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue((ping) -> ctx.getChannel().sendMessageFormat("Rest API Ping: %sms\nWebSocket Ping: %sms", ping, jda.getGatewayPing()).queue());

    }

    @Override
    public String getHelp() {
        return "Shows the current ping from the bot to the Discord servers" +
                "Usage: `" + Bot.prefix + "ping`";
    }

    @Override
    public String getName() {
        return "ping";
    }
}

package tech.nevets.nevetsbot.commands.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.CommandContext;
import tech.nevets.nevetsbot.commands.ICommand;

import java.util.List;

public class KickCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Message message = ctx.getMessage();
        final Member member = ctx.getMember();
        final List<String> args = ctx.getArgs();

        if (args.size() < 2 || message.getMentionedMembers().isEmpty()) {
            channel.sendMessage("Missing arguments").queue();
            return;
        }

        final Member target = message.getMentionedMembers().get(0);

        if (!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("You do not have permission to kick members").queue();
            return;
        }

        final Member selfMember = ctx.getSelfMember();

        if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("You do not have permission to kick members").queue();
            return;
        }

        final String reason = String.join(" ", args.subList(1, args.size()));

        ctx.getGuild()
                .kick(target, reason)
                .reason(reason)
                .queue(
                        (__) -> channel.sendMessage("User successfully kicked").queue(),
                        (error) -> channel.sendMessageFormat("Could not kick %s", error.getMessage()).queue()
                );
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getHelp() {
        return "Kick a member from the server \n" +
                "Usage: `" + Bot.prefix + " kick <@user> <reason>";
    }
}

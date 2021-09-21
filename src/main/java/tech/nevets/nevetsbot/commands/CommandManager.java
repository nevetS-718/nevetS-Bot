package tech.nevets.nevetsbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.admin.KickCmd;
import tech.nevets.nevetsbot.commands.admin.PingCmd;
import tech.nevets.nevetsbot.commands.games.CoinFlipCmd;
import tech.nevets.nevetsbot.commands.games.DadJokeCmd;
import tech.nevets.nevetsbot.commands.games.DiceCmd;
import tech.nevets.nevetsbot.commands.music.JoinCmd;
import tech.nevets.nevetsbot.commands.pics.CatCmd;
import tech.nevets.nevetsbot.commands.pics.DogCmd;
import tech.nevets.nevetsbot.commands.pics.FoxCmd;
import tech.nevets.nevetsbot.commands.pics.PandaCmd;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    protected final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        //Add to this list in alphabetical order
        addCommand(new CatCmd());
        addCommand(new CoinFlipCmd());
        addCommand(new DadJokeCmd());
        addCommand(new DiceCmd());
        addCommand(new DogCmd());
        addCommand(new FoxCmd());
        addCommand(new HelpCmd(this));
        addCommand(new JoinCmd());
        addCommand(new KickCmd());
        addCommand(new PandaCmd());
        addCommand(new PingCmd());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Bot.prefix), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd !=null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }
}
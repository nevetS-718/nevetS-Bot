package tech.nevets.nevetsbot.commands.games;

import tech.nevets.nevetsbot.Bot;
import tech.nevets.nevetsbot.commands.CommandContext;
import tech.nevets.nevetsbot.commands.ICommand;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Random rand = ThreadLocalRandom.current();

        int roll = rand.nextInt(6) + 1;
        ctx.getChannel().sendTyping().queue();
        ctx.getChannel().sendMessage("Your roll: " + roll).queue();
    }

    @Override
    public String getName() {
        return "dice";
    }

    @Override
    public String getHelp() {
        return "Returns a value from 1-6" +
                "Usage: `" + Bot.prefix + "dice`";
    }
}

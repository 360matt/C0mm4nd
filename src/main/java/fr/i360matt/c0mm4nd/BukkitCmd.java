package fr.i360matt.c0mm4nd;


import fr.i360matt.c0mm4nd.exceptions.BadArgException;
import fr.i360matt.c0mm4nd.exceptions.MissingArgException;
import fr.i360matt.c0mm4nd.exceptions.SenderNotPlayerException;
import fr.i360matt.c0mm4nd.expressions.Linguistic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BukkitCmd extends Command {

    private static CommandMap commandMap;
    private final String name;

    static {
        try {
            final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            commandMap = (CommandMap) f.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public BukkitCmd (final String name) {
        super(name);
        this.name = name;
    }

    public void register () {
        if (commandMap != null)
            commandMap.register(name, this);
    }

    public void unregister () {
        commandMap.getCommand(name).unregister(commandMap);
    }



    // Overridable method
    public abstract void onCommand (final BukkitExec exec) throws Exception;


    // Overridable method
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return null;
    }


    // from Bukkit
    @Override
    public final boolean execute (@NotNull final CommandSender sender, @NotNull final String label, @NotNull final String[] args) {
        final BukkitExec exex = new BukkitExec(this, sender, args);

        try {
            this.onCommand(exex);
        } catch (final BadArgException e) {
            exex.reply(Linguistic.BAD_ARG.getValue(e.getArgNeeded(), e.getType()));
        } catch (final MissingArgException e) {
            exex.reply(Linguistic.MISSING_ARGS.getValue(e.getArgNeeded()));
        } catch (final SenderNotPlayerException e) {
            exex.reply(Linguistic.SENDER_NOT_PLAYER.getValue());
        } catch (final Exception e) {
            e.printStackTrace();
            // other throws
        }

        return true;
    }

    // from Bukkit
    @NotNull
    @Override
    public final List<String> tabComplete (@NotNull final CommandSender sender, @NotNull final String alias, @NotNull final String[] args) throws IllegalArgumentException {
        List<String> list = this.tabComplete(sender, args);
        return (list != null) ? list : new ArrayList<>();
    }

    // from Bukkit
    @NotNull
    @Override
    public final List<String> tabComplete (@NotNull final CommandSender sender, @NotNull final String alias, @NotNull final String[] args, @Nullable final Location location) throws IllegalArgumentException {
        List<String> list = this.tabComplete(sender, args);
        return (list != null) ? list : new ArrayList<>();
    }
}

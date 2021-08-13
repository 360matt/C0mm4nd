package fr.i360matt.c0mm4nd;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;

public abstract class BukkitCmd extends Command {

    private static CommandMap commandMap;

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
        if (commandMap != null)
            commandMap.register(name, this);
    }



    // Overridable method
    public abstract BukkitExec onCommand ();


    // Overridable method
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return null;
    }


    // proxy-method
    @Override
    public final boolean execute (@NotNull final CommandSender sender, @NotNull final String label, @NotNull final String[] args) {
        BukkitExec v2_cExec = this.onCommand(); // instantiate a new object
        v2_cExec.performExecution(this, sender, args);
        return true;
    }

    // proxy-method
    @NotNull
    @Override
    public final List<String> tabComplete (@NotNull final CommandSender sender, @NotNull final String alias, @NotNull final String[] args) throws IllegalArgumentException {
        return this.tabComplete(sender, args);
    }

    // proxy-method
    @NotNull
    @Override
    public final List<String> tabComplete (@NotNull final CommandSender sender, @NotNull final String alias, @NotNull final String[] args, @Nullable final Location location) throws IllegalArgumentException {
        return this.tabComplete(sender, args);
    }
}

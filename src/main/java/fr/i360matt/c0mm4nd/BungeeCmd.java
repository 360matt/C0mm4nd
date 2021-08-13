package fr.i360matt.c0mm4nd;


import fr.i360matt.c0mm4nd.BungeeExec;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.List;

public abstract class BungeeCmd extends Command implements Listener, TabExecutor {


    public BungeeCmd (final String name) {
        super(name);
        ProxyServer.getInstance().getPluginManager().registerCommand(null, this);
    }




    // Overridable method
    public abstract BungeeExec onCommand ();



    // proxy-method
    @Override
    public final void execute (final CommandSender sender, final String[] args) {
        BungeeExec v2_cExec = this.onCommand(); // instantiate a new object
        v2_cExec.performExecution(this, sender, args);
    }

    // proxy-method
    public final Iterable<String> onTabComplete (CommandSender sender, String[] args) {
        return this.tabComplete(sender, args);
    }


    // Overridable method
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return null;
    }


}

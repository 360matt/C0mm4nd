package fr.i360matt.c0mm4nd;


import fr.i360matt.c0mm4nd.exceptions.BadArgException;
import fr.i360matt.c0mm4nd.exceptions.MissingArgException;
import fr.i360matt.c0mm4nd.exceptions.SenderNotPlayerException;
import fr.i360matt.c0mm4nd.expressions.Linguistic;
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
    public abstract void onCommand (final BungeeExec exec) throws Exception;



    // from Bungee
    @Override
    public final void execute (final CommandSender sender, final String[] args) {
        final BungeeExec exec = new BungeeExec(this, sender, args); // instantiate a new object

        try {
            this.onCommand(exec);
        } catch (final BadArgException e) {
            exec.reply(Linguistic.BAD_ARG.getValue(e.getArgNeeded(), e.getType()));
        } catch (final MissingArgException e) {
            exec.reply(Linguistic.MISSING_ARGS.getValue(e.getArgNeeded()));
        } catch (final SenderNotPlayerException e) {
            exec.reply(Linguistic.SENDER_NOT_PLAYER.getValue());
        } catch (final Exception e) {
            e.printStackTrace();
            // other throws
        }

    }

    // from Bungee
    public final Iterable<String> onTabComplete (CommandSender sender, String[] args) {
        return this.tabComplete(sender, args);
    }


    // Overridable method
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return null;
    }


}

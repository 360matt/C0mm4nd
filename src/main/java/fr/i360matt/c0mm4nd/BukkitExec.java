package fr.i360matt.c0mm4nd;

import fr.i360matt.c0mm4nd.exceptions.BadArgException;
import fr.i360matt.c0mm4nd.exceptions.MissingArgException;
import fr.i360matt.c0mm4nd.exceptions.SenderNotPlayerException;
import fr.i360matt.c0mm4nd.expressions.Linguistic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.rmi.server.ServerNotActiveException;

public abstract class BukkitExec {

    private Command command;
    private CommandSender sender;
    private String[] args;

    protected void performExecution (final Command command, final CommandSender sender, final String[] args) {
        this.command = command;
        this.sender = sender;
        this.args = args;

        try {
            this.exec();
        } catch (final BadArgException e) {
            reply(Linguistic.BAD_ARG.getValue(e.getArgNeeded(), e.getType()));
        } catch (final MissingArgException e) {
            reply(Linguistic.MISSING_ARGS.getValue(e.getArgNeeded()));
        } catch (final SenderNotPlayerException e) {
            reply(Linguistic.SENDER_NOT_PLAYER.getValue());
        } catch (final Exception e) {
            e.printStackTrace();
            // other throws
        }



    }

    public abstract void exec() throws Exception;


    /* ___________________________________________________________________ */


    public final Command getCommand () {
        return this.command;
    }

    public final CommandSender getSender () {
        return this.sender;
    }

    public final String[] getArgs () {
        return this.args;
    }

    public final void reply (final String message) {
        this.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    public void broadcast (final String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void execute (final BukkitCmd cmdInstance) {
        cmdInstance.onCommand().performExecution(this.command, this.sender, this.args);
    }

    public void execute (final BukkitExec execInstance) {
        execInstance.performExecution(this.command, this.sender, this.args);
    }


    public boolean isPlayer () {
        return this.sender instanceof Player;
    }

    public Player getPlayer () throws ServerNotActiveException {
        if (this.sender instanceof Player) {
            return (Player) this.sender;
        }
        throw new ServerNotActiveException();
    }


    public boolean arg (final int ind) {
        return this.args.length-1 >= ind;
    }

    public boolean argOrErr (final int ind) throws MissingArgException {
        if (this.args.length-1 >= ind)
            return true;
        throw new MissingArgException(ind+1);
    }

    public boolean arg (final int ind, String cond) {
        return this.args.length-1 >= ind && this.args[ind].equals(cond);
    }
    public boolean argOrErr (final int ind, String cond) throws MissingArgException, BadArgException {
        if (this.args.length-1 >= ind) {
            if (this.args[ind].equals(cond))
                return true;
            throw new BadArgException(ind+1, String.class);
        }
        throw new MissingArgException(ind+1);
    }

    public boolean arg (final int ind, String... cond) {
        if (this.args.length-1 >= ind) {
            final String toTest = this.args[ind];
            for (final String candidate : cond)
                if (toTest.equals(candidate))
                    return true;
        }
        return false;
    }
    public boolean argOrErr (final int ind, String... cond) throws BadArgException, MissingArgException {
        if (this.args.length-1 >= ind) {
            final String toTest = this.args[ind];
            for (final String candidate : cond)
                if (toTest.equals(candidate))
                    return true;
            throw new BadArgException(ind+1, String.class);
        }
        throw new MissingArgException(ind+1);
    }

    public boolean arg (final int ind, int... cond) {
        if (this.args.length-1 >= ind) {
            try {
                final int toTest = Integer.parseInt(this.args[ind]);
                for (final int candidate : cond)
                    if (toTest == candidate)
                        return true;
            } catch (final NumberFormatException ignored) {}
        }
        return false;
    }

    public boolean argOrErr (final int ind, int... cond) throws BadArgException, MissingArgException {
        if (this.args.length-1 >= ind) {
            try {
                final int toTest = Integer.parseInt(this.args[ind]);
                for (final int candidate : cond)
                    if (toTest == candidate)
                        return true;
                return false;
            } catch (final NumberFormatException e) {
                throw new BadArgException(ind+1, Integer.class);
            }
        }
        throw new MissingArgException(ind+1);
    }

    public int argsLength () {
        return this.args.length;
    }

    public Player argPlayerNullable (final int ind) {
        if (this.args.length-1 >= ind) {
            final String candidateName = this.args[ind];
            return Bukkit.getPlayer(candidateName);
        }
        return null;
    }

    public boolean isArgPlayer (final int ind) {
        if (this.args.length-1 >= ind) {
            return Bukkit.getPlayer(this.args[ind]) != null;
        }
        return false;
    }

    public Player argPlayer (final int ind) throws MissingArgException, BadArgException {
        if (this.args.length-1 >= ind) {
            final String candidateName = this.args[ind];
            final Player player = Bukkit.getPlayer(candidateName);
            if (player != null)
                return player;
            throw new BadArgException(ind+1, Linguistic.Player.class);
        }
        throw new MissingArgException(ind+1);
    }

    public OfflinePlayer argOfflinePlayer (final int ind) throws MissingArgException {
        if (this.args.length-1 >= ind) {
            final String candidateName = this.args[ind];
            return Bukkit.getOfflinePlayer(candidateName);
        }
        throw new MissingArgException(ind+1);
    }

    public boolean isArgInt (final int ind) {
        if (this.args.length-1 >= ind) {
            try {
                Integer.parseInt(this.args[ind]);
                return true;
            } catch (final NumberFormatException ignored) {}
            return Bukkit.getPlayer(this.args[ind]) != null;
        }
        return false;
    }

    public int getArgInt (final int ind) throws MissingArgException, BadArgException {
        if (this.args.length-1 >= ind) {
            try {
                return Integer.parseInt(this.args[ind]);
            } catch (final NumberFormatException e) {
                throw new BadArgException(ind+1, Integer.class);
            }
        }
        throw new MissingArgException(ind+1);
    }

    public boolean isArgDouble (final int ind) {
        if (this.args.length-1 >= ind) {
            try {
                Double.parseDouble(this.args[ind]);
                return true;
            } catch (final NumberFormatException ignored) {}
            return Bukkit.getPlayer(this.args[ind]) != null;
        }
        return false;
    }

    public double getArgDouble (final int ind) throws MissingArgException, BadArgException {
        if (this.args.length-1 >= ind) {
            try {
                return Double.parseDouble(this.args[ind]);
            } catch (final NumberFormatException e) {
                throw new BadArgException(ind+1, Double.class);
            }
        }
        throw new MissingArgException(ind+1);
    }

    public boolean isArgLong (final int ind) {
        if (this.args.length-1 >= ind) {
            try {
                Long.parseLong(this.args[ind]);
                return true;
            } catch (final NumberFormatException ignored) {}
            return Bukkit.getPlayer(this.args[ind]) != null;
        }
        return false;
    }

    public long getArgLong (final int ind) throws MissingArgException, BadArgException {
        if (this.args.length-1 >= ind) {
            try {
                return Long.parseLong(this.args[ind]);
            } catch (final NumberFormatException e) {
                throw new BadArgException(ind+1, Long.class);
            }
        }
        throw new MissingArgException(ind+1);
    }

    public boolean isArgBoolean (final int ind) {
        if (this.args.length-1 >= ind) {
            if (this.args[ind].equalsIgnoreCase("true"))
                return true;
            if (this.args[ind].equalsIgnoreCase("false"))
                return true;
        }
        return false;
    }

    public boolean getArgBoolean (final int ind) throws MissingArgException, BadArgException {
        if (this.args.length-1 >= ind) {
            if (this.args[ind].equalsIgnoreCase("true"))
                return true;
            if (this.args[ind].equalsIgnoreCase("false"))
                return false;
            throw new BadArgException(ind+1, Boolean.class);
        }
        throw new MissingArgException(ind+1);
    }

}
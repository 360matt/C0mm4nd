package fr.i360matt.c0mm4nd.expressions;

public class Linguistic {

    public interface Lambda {
        String getValue (Object... args);
    }

    public static class Player {} // instead of Player/Bungee class, for compatibility


    public static Lambda MISSING_ARGS = (args) -> "§cErreur: §e" + args[0] + " §cargument(s) minimum attendu(s)";

    public static Lambda SENDER_NOT_PLAYER = (args) -> "§cErreur: seul les joueurs peuvent executer cette commande";

    public static Lambda BAD_ARG = (args) -> {
        final Class<?> classType = (Class<?>) args[1];

        String res = "§cErreur: Le §e" + args[0] + "° §cargument";
        if (classType == String.class)
            res += " a une valeur §etexte §cinvalide";
        if (classType == Integer.class)
            res += " n'est pas un §enombre (court)";
        if (classType == Long.class || classType == Double.class)
            res += " n'est pas un §enombre";
        if (classType == Boolean.class)
            res += " n'est pas un §eboolean";
        if (classType == Player.class)
            res += " n'est pas un §ejoueur connecté";

        return res;
    };



}

package fr.i360matt.c0mm4nd.expressions;

public interface Linguistic {

    class Player {}


    Linguistic MISSING_ARGS = (args) -> "§cErreur: §e" + args[0] + " §cargument(s) minimum attendu(s)";

    Linguistic SENDER_NOT_PLAYER = (args) -> "§cErreur: seul les joueurs peuvent executer cette commande";

    Linguistic BAD_ARG = (args) -> {
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

    String getValue (Object... args);

}

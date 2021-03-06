# ✨C0mm4nd
A simple and powerfull command API for bukkit and bungeecord  
With command injection (without plugin.yml)  

My Discord: ``Matteow#6953``

## Maven
```
<repositories>
    <repository>
        <id>sonatype</id>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>


<dependencies>
    <dependency>
        <groupId>io.github.360matt</groupId>
        <artifactId>C0mm4nd</artifactId>
        <version>1.4.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Features
### Shortened methods:
* check if the sender is a player (and get the Player)
* check the number of arguments
* retrieve the arguments according to their type (player, string, int, long, boolean)
* test for equality on arguments
* send a message to the sender
* broadcast a message
### Based on Exceptions:
* all methods on arguments can thrown or not if value is invalid
* exceptions are automatically catch
* an error message is automatically sent to the sender
### Sub-orders division:
* possibility to split the command into several pieces, in different class
* throw support

## Translation (default: French, fuck English :))  
All fields are using Linguistic.Lambda interface  

```java
Linguistic.MISSING_ARGS = (args) -> "";
// args[0] = required args count (int)

Linguistic.SENDER_NOT_PLAYER = (args) -> "";
// no args

Linguistic.BAD_ARG = (args) -> "";
// args[0] = place of bad argument (int)
// args[1] = type (Class<?>) beetween String | Integer | Long | Boolean | Linguistic.Player


// simply ^^
```


## Register a command:
Just instantiate it.  
```java
new XX(); // is done.
```

## Bukkit
```java
public class XX extends BukkitCmd {

    public TestBukkitCommand () {
        super("truc");
        // setPermissionMessage("Not pemrission message");
        // setPermission("");
        // setAliases(Arrays.asList("Wooaw"));

    }

    @Override
    public void onCommand (final BukkitExec exec) {

          Player player = getPlayer();
          // stop and throw if is console.

          if (isArg(0, "one", "two", "three")) {
              // if args[0] equals "one" or equals "two" or equals "three"
              // not throwable
          }

          if (isArgOrErr(0, "one", "two", "three")) {
              // if args[0] equals "one" or equals "two" or equals "three"
              // if not equals, stop the command, throw and if not catched: send error message automatically
          }

          if (isArgOrErr(0, 10, 20, 30)) {} // same with int
          if (isArg(0, 10, 20, 30)) {}



          Player playerArg1 = getArgPlayerNullable(1); // not throwable, but nullable
          Player playerArg2 = getArgPlayer(1); // throwable
          boolean state1 = isArgPlayer(1);

          OfflinePlayer offlinePlayer = getArgOfflinePlayer(1);


          long number1 = getArgLong(2);
          boolean state2 = isArgBoolean(2);

          int number2 = getArgInt(2);
          boolean state3 = isArgInt(2);

          double number3 = getArgDouble(2);
          boolean state4 = isArgDouble(2);

          boolean boolean1 = getArgBoolean(2);
          boolean state5 = isArgBoolean(2);
                
    }

    @Override
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return Arrays.asList("example", "issou");
    }
}
```

## Bungee
```java
public class TestBungeeCommand extends BungeeCmd {
    public TestBungeeCommand () {
        super("truc");
    }

    @Override
    public void onCommand (final BungeeExec exec) {

        // similar to Bukkit usage, read it :)

    }

    @Override
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return Arrays.asList("example", "issou");
    }
}
```

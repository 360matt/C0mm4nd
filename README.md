# ✨C0mm4nd
A simple and powerfull command API for bukkit and bungeecord  
With command injection (without plugin.yml)  

My Discord: ``Matteow#6953``

## Maven
```
<repositories>
    <repository>
        <id>bungeecord-repo</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>


<dependencies>
    <dependency>
        <groupId>io.github.360matt</groupId>
        <artifactId>RunnableOverNetwork</artifactId>
        <version>1.0-SNAPSHOT</version>
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
    public BukkitExec onCommand () {


        return new BukkitExec() {
            @Override
            public void exec () throws Exception {
                
                // Do stuff

            }
        };
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
    public BungeeExec onCommand () {
        return new BungeeExec() {
            @Override
            public void exec () throws Exception {

                // Do stuff

            }
        };
    }

    @Override
    public List<String> tabComplete (final CommandSender sender, final String[] args) {
        return Arrays.asList("example", "issou");
    }
}
```

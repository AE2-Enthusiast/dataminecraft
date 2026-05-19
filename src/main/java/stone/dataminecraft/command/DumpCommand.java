package stone.dataminecraft.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class DumpCommand extends CommandBase {

  public static final Map<String, Consumer<String>> REGISTRY = new HashMap<>();
  
	@Override
	public String getName() {
    return "dump-data";
	}

	@Override
	public String getUsage(ICommandSender sender) {
    return "/dump-data <section>\n" +
      "Dumps data relevant to the section to files. Default is to dump everything available";
	}

  @Override
  public int getRequiredPermissionLevel()
  {
    return 0;
  }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    if (args.length >= 1) {
      Consumer<String> miner = REGISTRY.get(args[0]);
      if (miner != null) {
        miner.accept(args.length >= 2 ? args[1] : null);
      }
    }
	}

  public static void registerMiner(String id, Consumer<String> miner) {
    REGISTRY.put(id, miner);
  }
}

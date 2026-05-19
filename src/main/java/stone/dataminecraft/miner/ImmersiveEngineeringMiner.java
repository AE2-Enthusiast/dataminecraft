package stone.dataminecraft.miner;

import java.util.function.Consumer;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import blusunrize.immersiveengineering.api.tool.ExcavatorHandler.MineralMix;

// this should probably be a proper interface, but whatever
public class ImmersiveEngineeringMiner implements Consumer<String> {

	@Override
	public void accept(String arg) {
    for (var vein : ExcavatorHandler.mineralList.entrySet()) {
      MineralMix mineral = vein.getKey();
      System.out.println('{');
      System.out.printf("\"name\": \"%s\",\n", mineral.name);
      System.out.printf("\"weight\": %d\n", vein.getValue());
      System.out.println('}');
    }
	}
  
}

package stone.dataminecraft.api.json.immersiveengineering;

import java.util.Map;

import com.github.bsideup.jabel.Desugar;

import stone.dataminecraft.api.json.minecraft.ItemStack;

// apparently jackson hates records?? idk it didn't work, so public objects here
// we come baby
@Desugar
public class MineralMix
 {
   public String name;
   public float failChance;
   public String[] ores;
   public float[]chances;
   public ItemStack[] oreOutput;
   public float[] recalculatedChances;
   public boolean valid;
   public Map<String, String> replacementOres;
   public int[] dimensionWhitelist;
   public int[] dimensionBlacklist;
}

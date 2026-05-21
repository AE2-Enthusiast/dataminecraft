package stone.dataminecraft.miner;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import blusunrize.immersiveengineering.api.tool.ExcavatorHandler.MineralMix;
import stone.dataminecraft.api.json.minecraft.ItemStackSerializer;

// this should probably be a proper interface, but whatever
public class ImmersiveEngineeringMiner implements Consumer<String> {

	@Override
	public void accept(String arg) {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(new ItemStackSerializer());
    module.addSerializer(new MineralListSerializer());
    mapper.registerModule(module);

    File dir =  new File("dataminecraft/immersiveengineering");
    dir.mkdirs();
    File output = new File(dir, "mineral_veins.json");

    try {
		mapper.writeValue(output, ExcavatorHandler.mineralList.entrySet());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    /*
    for (var vein : ExcavatorHandler.mineralList.entrySet()) {
      try {
        mapper.writeValue(output, vein);
        //String json = mapper.writeValueAsString(vein.getKey());
        //System.out.println(json);
        //MineralMix mix = mapper.readValue(json, MineralMix.class);
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mix));
      } catch (IOException e) {
        e.printStackTrace();
	  }
    }
    */
	}

  public static class MineralListSerializer extends StdSerializer<Entry> {

	protected MineralListSerializer() {
		super(Entry.class);
	}

	@Override
	public void serialize(Entry value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
    gen.writeStartObject();
    provider.defaultSerializeField("mineral", value.getKey(), gen);
    gen.writeNumberField("weight", (Integer) value.getValue());
    gen.writeEndObject();
	}

  }
}

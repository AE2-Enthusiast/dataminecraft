package stone.dataminecraft.miner;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

// for dumping MinecraftForge stuff like the fluid registry
public class MinecraftForgeMiner implements Consumer<String> {

	@Override
	public void accept(String arg0) {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(new FluidRegistrySerializer());
    module.addSerializer(new FluidSerializer());
    mapper.registerModule(module);
    File dir =  new File("dataminecraft/minecraftforge");
    dir.mkdirs();
    File output = new File(dir, "fluids.json");
    try {
		mapper.writeValue(output, FluidRegistry.getRegisteredFluids().entrySet());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

  public static class FluidRegistrySerializer extends StdSerializer<Entry> {

    public FluidRegistrySerializer() {
      super(Entry.class);
    }
	@Override
	public void serialize(Entry value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    provider.defaultSerializeField("name", value.getKey(), gen);
    provider.defaultSerializeField("fluid", value.getValue(), gen);
    gen.writeEndObject();
	}

  }
  public static class FluidSerializer extends StdSerializer<Fluid> {

    public FluidSerializer() {
      super(Fluid.class);
    }
    @Override
    public void serialize(Fluid value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeStartObject();
      gen.writeStringField("fluidName", value.getName());
      gen.writeStringField("unlocalizedName", value.getUnlocalizedName());
      gen.writeNumberField("luminosity", value.getLuminosity());
      gen.writeNumberField("density", value.getDensity());
      gen.writeNumberField("temperature", value.getTemperature());
      gen.writeNumberField("viscosity", value.getViscosity());
      gen.writeBooleanField("gaseous", value.isGaseous());
      gen.writeNumberField("rarity", value.getRarity().ordinal());
      gen.writeNumberField("color", value.getColor());
      gen.writeEndObject();
    }

  }
}

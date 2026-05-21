package stone.dataminecraft.api.json.minecraft;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackSerializer extends StdSerializer<ItemStack> {

  public ItemStackSerializer() {
    super(ItemStack.class);
  }
  
	@Override
	public void serialize(ItemStack value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("id", Item.REGISTRY.getNameForObject(value.getItem()).toString());
    gen.writeNumberField("damage", value.getItemDamage());
    gen.writeNumberField("count", value.getCount());
    gen.writeObjectField("nbt", value.getTagCompound());
    gen.writeEndObject();
	}

}

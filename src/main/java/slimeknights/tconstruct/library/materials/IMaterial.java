package slimeknights.tconstruct.library.materials;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.Color;
import slimeknights.tconstruct.TConstruct;

public interface IMaterial {

  /**
   * Fallback material. Used for operations where a material or specific aspects of a material are used,
   * but the given input is missing or does not match the requirements.
   * Think of this as "anti-crash" when trying to build invalid tools.
   * <p>
   * The fallback material needs to have all part types associated with it.
   */
  IMaterial UNKNOWN = new Material(new ResourceLocation(TConstruct.modID, "unknown"), Fluids.EMPTY, false);

  /**
   * Used to identify the material in NBT and other constructs.
   * Basically everywhere where the material has to be referenced and persisted.
   */
  MaterialId getIdentifier();

  /**
   * If the material can be crafted into items in the part builder.
   *
   * @return Return false if the material can only be cast or is not craftable at all.
   */
  boolean isCraftable();

  /**
   * The fluid associated with this material, if not Fluids.EMPTY.
   * Prerequisite for parts to be cast using the casting table and a cast.
   * Just to make this completely clear: This is the indicator if a material is castable.
   *
   * @return The associated fluid or Fluids.EMPTY if material is not castable
   */
  Fluid getFluid();

  /**
   * Gets the amount of fluid needed to produce one "unit" of this material. Typically {@link MaterialValues#INGOT} for metal based materials.
   * Will be 0 for non-fluids
   *
   * @return  Amount of fluid per unit.
   */
  int getFluidPerUnit();

  /**
   * Gets the translation key for this material
   * @return the translation key
   */
  default String getTranslationKey() {
    return Util.makeTranslationKey("material", getIdentifier());
  }

  /**
   * Gets the text color for this material
   * @return the text color
   */
  Color getColor();

  /**
   * Gets the temperature of this material for use in melting and casting recipes.
   * If this is not castable or meltable, will be 0;
   * @return  Temperature of the material, 0 if not relevant
   */
  int getTemperature();
}

package com.dannbrown.remix.datagen

import com.dannbrown.remix.RemixMod
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistryAccess
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.data.event.GatherDataEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object DatagenRegistries {
  private val LOGGER: Logger = LogManager.getLogger(RemixMod.MOD_ID)
  fun registerDataProviders(event: GatherDataEvent) {
    val generator = event.generator
    val output = generator.packOutput
    val helper = event.existingFileHelper
    val provider = event.lookupProvider.thenApply { obj: HolderLookup.Provider -> createLookup(obj) }
    LOGGER.log(Level.INFO, RemixMod.MOD_ID + " is registering data providers!")
    val modIds: MutableSet<String> = HashSet()
    modIds.add("minecraft")
    modIds.add(RemixMod.MOD_ID)
    generator.addProvider(event.includeClient(), DatagenLang(output));
    generator.addProvider(event.includeClient(), DatagenBlockStates(output, helper))
    generator.addProvider(event.includeClient(), DatagenItemModels(output, helper))
    generator.addProvider(event.includeServer(), DatagenRecipes(output));
//    generator.addProvider(event.includeServer(), DatagenLootTables(output));

    generator.addProvider(event.includeServer(), DatagenBlockTags(output, provider, helper))
    val datapackEntries = DatapackBuiltinEntriesProvider(output, provider, modIds)
    val lookupProvider = datapackEntries.registryProvider
    generator.addProvider(event.includeServer(), datapackEntries)
    generator.addProvider(event.includeServer(), DatagenBiomeTags(output, lookupProvider, helper))
  }

  private fun createLookup(vanillaLookupProvider: HolderLookup.Provider): HolderLookup.Provider {
    val registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)
    val builder = RegistrySetBuilder()
    builder.add(Registries.DENSITY_FUNCTION) { obj -> DatagenDensityFunctions.bootstrap(obj) }
    builder.add(Registries.BIOME) { c -> DatagenBiomes.bootstrap(c) }
    builder.add(Registries.CONFIGURED_FEATURE) { c -> DatagenConfiguredFeatures.bootstrap(c) }
    builder.add(Registries.CONFIGURED_CARVER) { c -> DatagenConfiguredCarvers.bootstrap(c) }
    builder.add(Registries.PLACED_FEATURE) { c -> DatagenPlacedFeatures.bootstrap(c) }
    // builder.add(Registries.DENSITY_FUNCTION, DatagenNoiseRouterData::bootstrap);
    // builder.add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST,
    // DatagenDimensions::bootstrapNoiseBiomeSource);
    builder.add(Registries.DIMENSION_TYPE) { c -> DatagenDimensions.bootstrapType(c) }
    builder.add(Registries.LEVEL_STEM) { c -> DatagenDimensions.bootstrapStem(c) }
    builder.add(Registries.NOISE_SETTINGS) { c -> DatagenDimensions.bootstrapNoise(c) }
    return builder.buildPatch(registryAccess, vanillaLookupProvider)
  }
}

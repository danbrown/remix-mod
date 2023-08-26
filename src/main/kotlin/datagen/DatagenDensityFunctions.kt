package com.dannbrown.remix.datagen

import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.DensityFunctions
import net.minecraft.world.level.levelgen.DensityFunctions.HolderHolder
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.synth.BlendedNoise
import net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters
import kotlin.math.abs

object DatagenDensityFunctions {
  const val GLOBAL_OFFSET = -0.50375f
  private const val ORE_THICKNESS = 0.08f
  private const val VEININESS_FREQUENCY = 1.5
  private const val NOODLE_SPACING_AND_STRAIGHTNESS = 1.5
  private const val SURFACE_DENSITY_THRESHOLD = 1.5625
  private const val CHEESE_NOISE_TARGET = -0.703125
  const val ISLAND_CHUNK_DISTANCE = 64
  const val ISLAND_CHUNK_DISTANCE_SQR = 4096L
  private val BLENDING_FACTOR = DensityFunctions.constant(10.0)
  private val BLENDING_JAGGEDNESS = DensityFunctions.zero()
  private val ZERO = createKey("zero")
  val Y = createKey("y")
  val SHIFT_X = createKey("shift_x")
  val SHIFT_Z = createKey("shift_z")
  private val BASE_3D_NOISE_OVERWORLD = createKey("overworld/base_3d_noise")
  private val BASE_3D_NOISE_NETHER = createKey("nether/base_3d_noise")
  private val BASE_3D_NOISE_END = createKey("end/base_3d_noise")
  val CONTINENTS = createKey("overworld/continents")
  val EROSION = createKey("overworld/erosion")
  val RIDGES = createKey("overworld/ridges")
  val RIDGES_FOLDED = createKey("overworld/ridges_folded")
  val OFFSET = createKey("overworld/offset")
  val FACTOR = createKey("overworld/factor")
  val JAGGEDNESS = createKey("overworld/jaggedness")
  val DEPTH = createKey("overworld/depth")
  private val SLOPED_CHEESE = createKey("overworld/sloped_cheese")
  val CONTINENTS_LARGE = createKey("overworld_large_biomes/continents")
  val EROSION_LARGE = createKey("overworld_large_biomes/erosion")
  private val OFFSET_LARGE = createKey("overworld_large_biomes/offset")
  private val FACTOR_LARGE = createKey("overworld_large_biomes/factor")
  private val JAGGEDNESS_LARGE = createKey("overworld_large_biomes/jaggedness")
  private val DEPTH_LARGE = createKey("overworld_large_biomes/depth")
  private val SLOPED_CHEESE_LARGE = createKey(
    "overworld_large_biomes/sloped_cheese"
  )
  private val OFFSET_AMPLIFIED = createKey("overworld_amplified/offset")
  private val FACTOR_AMPLIFIED = createKey("overworld_amplified/factor")
  private val JAGGEDNESS_AMPLIFIED = createKey("overworld_amplified/jaggedness")
  private val DEPTH_AMPLIFIED = createKey("overworld_amplified/depth")
  private val SLOPED_CHEESE_AMPLIFIED = createKey(
    "overworld_amplified/sloped_cheese"
  )
  private val SLOPED_CHEESE_END = createKey("end/sloped_cheese")
  private val SPAGHETTI_ROUGHNESS_FUNCTION = createKey(
    "overworld/caves/spaghetti_roughness_function"
  )
  private val ENTRANCES = createKey("overworld/caves/entrances")
  private val NOODLE = createKey("overworld/caves/noodle")
  private val PILLARS = createKey("overworld/caves/pillars")
  private val SPAGHETTI_2D_THICKNESS_MODULATOR = createKey(
    "overworld/caves/spaghetti_2d_thickness_modulator"
  )
  private val SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d")
  private fun createKey(p_209537_: String): ResourceKey<DensityFunction> {
    return ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation(p_209537_))
  }

  fun getFunction(
    p_256312_: HolderGetter<DensityFunction>,
    p_256077_: ResourceKey<DensityFunction>?
  ): DensityFunction {
    return HolderHolder(p_256312_.getOrThrow(p_256077_))
  }

  private fun registerAndWrap(
    p_256149_: BootstapContext<DensityFunction>,
    p_255905_: ResourceKey<DensityFunction>, p_255856_: DensityFunction
  ): DensityFunction {
    return HolderHolder(p_256149_.register(p_255905_, p_255856_))
  }

  private fun peaksAndValleys(p_224438_: DensityFunction): DensityFunction {
    return DensityFunctions.mul(
      DensityFunctions.add(
        DensityFunctions.add(p_224438_.abs(), DensityFunctions.constant(-0.6666666666666666)).abs(),
        DensityFunctions.constant(-0.3333333333333333)
      ), DensityFunctions.constant(-3.0)
    )
  }

  fun peaksAndValleys(p_224436_: Float): Float {
    return (-(abs((abs(p_224436_.toDouble()) - 0.6666667f).toDouble()) - 0.33333334f) * 3.0f).toFloat()
  }

  private fun pillars(p_255985_: HolderGetter<NoiseParameters>): DensityFunction {
    val d0 = 25.0
    val d1 = 0.3
    val densityfunction = DensityFunctions.noise(p_255985_.getOrThrow(Noises.PILLAR), 25.0, 0.3)
    val densityfunction1 = DensityFunctions.mappedNoise(
      p_255985_.getOrThrow(Noises.PILLAR_RARENESS), 0.0,
      -2.0
    )
    val densityfunction2 = DensityFunctions.mappedNoise(
      p_255985_.getOrThrow(Noises.PILLAR_THICKNESS), 0.0,
      1.1
    )
    val densityfunction3 = DensityFunctions
      .add(DensityFunctions.mul(densityfunction, DensityFunctions.constant(2.0)), densityfunction1)
    return DensityFunctions.cacheOnce(DensityFunctions.mul(densityfunction3, densityfunction2.cube()))
  }

  fun bootstrap(p_256220_: BootstapContext<DensityFunction>): Holder<out DensityFunction> {
    val holdergetter = p_256220_.lookup(Registries.NOISE)
    val holdergetter1 = p_256220_.lookup(Registries.DENSITY_FUNCTION)
    p_256220_.register(ZERO, DensityFunctions.zero())
    val i = DimensionType.MIN_Y * 2
    val j = DimensionType.MAX_Y * 2
    p_256220_.register(Y, DensityFunctions.yClampedGradient(i, j, i.toDouble(), j.toDouble()))
    val densityfunction = registerAndWrap(
      p_256220_, SHIFT_X, DensityFunctions
        .flatCache(DensityFunctions.cache2d(DensityFunctions.shiftA(holdergetter.getOrThrow(Noises.SHIFT))))
    )
    val densityfunction1 = registerAndWrap(
      p_256220_, SHIFT_Z, DensityFunctions
        .flatCache(DensityFunctions.cache2d(DensityFunctions.shiftB(holdergetter.getOrThrow(Noises.SHIFT))))
    )
    p_256220_.register(BASE_3D_NOISE_OVERWORLD, BlendedNoise.createUnseeded(0.25, 0.125, 80.0, 160.0, 8.0))
    p_256220_.register(BASE_3D_NOISE_NETHER, BlendedNoise.createUnseeded(0.25, 0.375, 80.0, 60.0, 8.0))
    p_256220_.register(BASE_3D_NOISE_END, BlendedNoise.createUnseeded(0.25, 0.25, 80.0, 160.0, 4.0))
    val holder: Holder<DensityFunction> = p_256220_.register(
      CONTINENTS, DensityFunctions.flatCache(
        DensityFunctions
          .shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.CONTINENTALNESS))
      )
    )
    val holder1: Holder<DensityFunction> = p_256220_.register(
      EROSION, DensityFunctions.flatCache(
        DensityFunctions
          .shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.EROSION))
      )
    )
    val densityfunction2 = registerAndWrap(
      p_256220_, RIDGES, DensityFunctions.flatCache(
        DensityFunctions
          .shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.RIDGE))
      )
    )
    p_256220_.register(RIDGES_FOLDED, peaksAndValleys(densityfunction2))
    return p_256220_.register(PILLARS, pillars(holdergetter))
  }
}

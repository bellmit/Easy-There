package com.nexorel.et.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new RecipeGen(generator));
            generator.addProvider(new ModGenLootTables(generator));
        }
        if (event.includeClient()) {
//            generator.addProvider(new ModGenBlockStates(generator, event.getExistingFileHelper()));
//            generator.addProvider(new ModGenItems(generator, event.getExistingFileHelper()));
        }
    }

}

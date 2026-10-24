package me.aleksilassila.litematica.printer.v1_21_11;

import fi.dy.masa.litematica.schematic.LitematicaSchematic;
import fi.dy.masa.litematica.util.FileType;
import me.aleksilassila.litematica.printer.v1_21_11.mixin.LitematicaSchematicAccessor;

import java.io.File;
import java.nio.file.Path;

/**
 * @author IceTank
 * @since 17.12.2024
 */
public class SchematicConverter {
    public static LitematicaSchematic convertAndReturn(Path file, Path out) {
        LitematicaSchematic schematic = LitematicaSchematicAccessor.invokeConstructor(file.toFile(), FileType.VANILLA_STRUCTURE);
        schematic.readFromFile();
        String fileName = file.getFileName().toString().replace(".nbt", "");
        schematic.writeToFile(out, fileName, true);
        LitematicaSchematic newSchem = LitematicaSchematicAccessor.invokeConstructor(out.resolve(fileName + ".litematic").toFile(), FileType.LITEMATICA_SCHEMATIC);
        newSchem.readFromFile();
        return newSchem;
    }
}

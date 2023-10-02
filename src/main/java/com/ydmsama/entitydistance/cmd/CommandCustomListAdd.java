package com.ydmsama.entitydistance.cmd;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CommandCustomListAdd extends CommandBase {

    @Override
    public String getName() {
        return "customlistadd";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/customlistadd <entityID,multiplier>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            sender.sendMessage(new TextComponentString("Invalid arguments! Usage: " + this.getUsage(sender)));
            return;
        }

        String entry = args[0];
        List<String> entries = new ArrayList<>(Arrays.asList(ModConfig.CustomEntityTrackMultipliers));
        if (!entries.contains(entry)) {
            entries.add(entry);
            ModConfig.CustomEntityTrackMultipliers = entries.toArray(new String[0]);
            sender.sendMessage(new TextComponentString(entry + " added to custom entity track multipliers."));
        } else {
            sender.sendMessage(new TextComponentString(entry + " already exists in the custom entity track multipliers."));
        }
        ConfigChangedEvent.OnConfigChangedEvent event = new ConfigChangedEvent.OnConfigChangedEvent(EntityDistance.MOD_ID, null, false, false);
        MinecraftForge.EVENT_BUS.post(event);
    }
}

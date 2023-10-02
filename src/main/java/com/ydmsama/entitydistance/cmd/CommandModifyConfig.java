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

public class CommandModifyConfig extends CommandBase {

    @Override
    public String getName() {
        return "entitydistance";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/entitydistance <configName> <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            sender.sendMessage(new TextComponentString("Invalid arguments! Usage: " + this.getUsage(sender)));
            return;
        }

        String configName = args[0];
        String value = args[1];

        switch(configName.toLowerCase()) {
            case "renderdistancemultiplier":
                ModConfig.renderDistanceMultiplier = Double.parseDouble(value);
                sender.sendMessage(new TextComponentString("Render Distance Multiplier set to " + value));
                break;

            case "trackdistancemultiplier":
                ModConfig.trackDistanceMultiplier = Double.parseDouble(value);
                sender.sendMessage(new TextComponentString("Track Distance Multiplier set to " + value));
                break;

            case "entityblacklist":
                ModConfig.entityList = value.split(",");
                sender.sendMessage(new TextComponentString("Entity Blacklist set to " + value));
                break;

            case "customentitytrackmultipliers":
                ModConfig.CustomEntityTrackMultipliers = value.split(";");
                sender.sendMessage(new TextComponentString("Custom Entity Track Multipliers set to " + value));
                break;

            case "whitelist":
                ModConfig.Whitelist = Boolean.parseBoolean(value);
                sender.sendMessage(new TextComponentString("Whitelist mode set to " + value));
                break;

            default:
                sender.sendMessage(new TextComponentString("Unknown config name: " + configName));
        }
        ConfigChangedEvent.OnConfigChangedEvent event = new ConfigChangedEvent.OnConfigChangedEvent(EntityDistance.MOD_ID, null, false, false);
        MinecraftForge.EVENT_BUS.post(event);
    }
}

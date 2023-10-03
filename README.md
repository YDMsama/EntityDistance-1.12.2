# Entity Distance Mod 

Rendering entities from greater distances or reduce that for improved performance. (Customizable for each entity type)

## Introduction 

This simple mod allows you to customize the rendering and tracking distances for entities in Minecraft1.12.2. For instance, you can opt to see hostile entities from farther away and render neutral entities like animals at closer distances, thus optimizing both FPS and network performance. 

### Glossary: 

- **Entity Rendering Distance**: The distance within which entities are rendered on the client-side. (Affects client performance) 
- **Entity Tracking Distance**: The distance within which the server sends data about entities to the client, enabling them to be tracked. (Affects server performance) 

> Note: If you aim to increase an entity's rendering distance, you must also increase its tracking distance. An entity must first be tracked before it can be rendered.

## Features 

- Adjust entity rendering distance.
- Adjust entity tracking distance.
- Provides a slider in the settings menu for easy adjustments.
- Entity Blacklist/Whitelist
- Custom Entity Track Multipliers

## Commands
- `/entitydistance <configName> <value>` (Possible `<configName>`: renderDistanceMultiplier, trackDistanceMultiplier, Whitelist, entityblacklist, customentitytrackmultipliers)
- `/setrenderdistance <value>`
- `/settrackdistance <value>`
- `/entitylistadd <entityID>`
- `/entitylistremove <entityID>`
- `/customlistadd <entityID>,<multiplier>`
- `/customlistremove <entityID>`

## Installation 

1. Download the latest version of Entity Distance Mod.
2. Place the downloaded jar file into the Minecraft mods folder.
3. After installing the mod, navigate to the settings menu where you'll find sliders to adjust the entity rendering and tracking distances. Adjust them to your preference and enjoy the game!

## FAQ 

**Q**: Can this mod be used alongside other performance mods?  
**A**: Yes, this mod is compatible with most performance mods. However, ensure to test it before using in tandem with other mods.

**Q**: Is this mod server-side or client-side?  
**A**: This mod can function on both the server and client sides.

## FutureFuture Plans 

- Global settings for hostile (and other faction) entities.
- Custom configurations for specific entities, e.g., projectiles.

## Contributing 

If you've identified a bug or wish to propose a new feature, kindly create an issue on our GitHub page. Should you desire to contribute code-wise, feel free to fork the repository and submit a pull request.

## License 

This mod is released under the MIT license. Kindly refer to the LICENSE file for more details.

## Other

thanks to [forge-mixin-example](https://github.com/mouse0w0/forge-mixin-example) for their mixin template! 

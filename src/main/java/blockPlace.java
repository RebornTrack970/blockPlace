
import arc.util.*;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.mod.Mod;
import mindustry.world.*;

public class blockPlace extends Mod {

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register( "place", "<block> <team> <x> <y>", "Place blocks", (args, player) ->{
	    if(!player.admin){
                player.sendMessage("[red]You are not admin");
                return;
            }

            Block sblock = Vars.content.blocks().
                    find(b -> b.name.equals(args[0]));
	    int xx;
	    int yy;
	    try {
                xx = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage("[red]X must be number!");
                return;
            }
	    try {
                yy = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                player.sendMessage("[red]Y must be number!");
                return;
            }
            Team tteam;
            switch (args[1]) {
                case "sharded":
                    tteam = Team.sharded;
                    break;
                case "blue":
                    tteam = Team.blue;
                    break;
                case "crux":
                    tteam = Team.crux;
                    break;
                case "derelict":
                    tteam = Team.derelict;
                    break;
                case "green":
                    tteam = Team.green;
                    break;
                case "purple":
                    tteam = Team.purple;
                    break;
                default:
                    player.sendMessage("[accent]Teams: [yellow]sharded[], [blue]blue[], [red]crux[], [gray]derelict[], [green]green[], [purple]purple[].");
                    return;
            }

            if (sblock != null) {
                Vars.world.tile(xx,yy).setNet(sblock,tteam,0);
                player.sendMessage("[royal]You are placing" + " " +"[accent]"+sblock + " " + "[green]for" + " " +"[accent]"+tteam + " " + "[royal]team at "+xx+","+yy);
            } else {
                player.sendMessage("[red]You may select a Block.");
            }

        });
    }
}

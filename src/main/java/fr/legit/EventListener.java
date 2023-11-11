package fr.legit;

import fr.legit.util.Vector3f;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(event.hasChangedPosition()){
            if(isPlayerInRadius(new Vector3f((float) event.getTo().getX(), (float) event.getTo().getY(), (float) event.getTo().getZ()),player, 5f)){
                Vector v = player.getVelocity();

                float current_motion_x = (float) (event.getTo().getX() - event.getFrom().getX());
                float current_motion_y = (float) (event.getTo().getY() - event.getFrom().getY());
                float current_motion_z = (float) (event.getTo().getZ() - event.getFrom().getZ());

                v.setX(-current_motion_x * 2);
                v.setY(-current_motion_y * 2);
                v.setZ(-current_motion_z * 2);

                player.setVelocity(v);

                player.sendMessage("nuh uh");
            }
        }
    }

    public boolean isPlayerInRadius(Vector3f currPlayerVec3,Player currentPlayer, float radius){ // most fucking unoptimized function ive ever made in my entire life bruh. even more that that blur renderer ;(((((( shits def not skidded tho ;)
        ArrayList<Player> player_list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(Player p : player_list){
            if(p != currentPlayer){
                Vector3f searchingEntityVector3f = new Vector3f((float) p.getX(), (float) p.getY(), (float) p.getZ());

                if(getDistance(currPlayerVec3, searchingEntityVector3f) <= radius){
                    return true;
                }
            }
        }
        return false;

    }

    public float getDistance(Vector3f from, Vector3f to){ // simple 3d distance calc
        return (float) Math.sqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2) + Math.pow(from.z - to.z, 2));
    }
}

package org.parabot.scriptwriter.revival.aAgility.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aAgility.core.Core;
import org.parabot.scriptwriter.revival.aAgility.data.Course;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import static org.rev317.min.api.methods.Walking.walkTo;

public class TraverseArdougneCourse implements Strategy {
    private int[] obstacles = {11630,11633,11430,11429, 11631,11406,11405};
    @Override
    public boolean activate() {
        return Players.getMyPlayer().getAnimation() == -1
                && Core.getSettings().getSelectedCourse() == Course.ARDOUGNE;
    }

    @Override
    public void execute() {

       if(Npcs.getClosest(22) != null && Npcs.getClosest(22).distanceTo() < 5) {
           walkTo(getObject(obstacles[6]).getLocation());
           Time.sleep(5000);
       }

        for(int o : obstacles) {
            SceneObject obj = getObject(o);
            if(obj != null){
                if(o == 11630 && obj.distanceTo() > 3) continue;
                if(Players.getMyPlayer().getLocation().getY() == obj.getLocation().getY() - 11
                        || Players.getMyPlayer().getLocation().getY() == obj.getLocation().getY() + 9
                        || obj.distanceTo() < 7) {
                    obj.interact(SceneObjects.Option.FIRST);
                    Time.sleep(1500);
                    break;
                }
            }
        }
    }

    private SceneObject getObject(int id) {
        for (SceneObject sceneObject : SceneObjects.getAllSceneObjects()) {
            if (sceneObject != null) {
                if (sceneObject.getId() == id) {
                    return sceneObject;
                }
            }
        }
        return null;
    }
}

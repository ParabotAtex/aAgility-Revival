package org.parabot.scriptwriter.revival.aAgility.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aAgility.core.Core;
import org.parabot.scriptwriter.revival.aAgility.data.Course;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class TraverseGnomeCourse implements Strategy {
    private final int[] obstacles = {23145, 23134, 23559, 23557, 23560, 23135, 23138};
    private final int dog = 5920;


    @Override
    public boolean activate() {
        return Players.getMyPlayer().getAnimation() == -1
                && Core.getSettings().getSelectedCourse() == Course.GNOME;
    }

    @Override
    public void execute() {
        try {
            if (Npcs.getClosest(dog) != null && Npcs.getClosest(dog).distanceTo() < 5) {
                getObject(obstacles[0]).interact(SceneObjects.Option.FIRST);
                Time.sleep(1500);
            } else {
                if (getObstacles() == null) {
                    for (int o : obstacles) {
                        SceneObject obj = getObject(o);
                        if (obj != null && obj.distanceTo() < 6) {
                            obj.interact(SceneObjects.Option.FIRST);
                            Time.sleep(1500);
                        }
                    }
                } else {
                    SceneObject obj = SceneObjects.getClosest(23138);
                    if (obj != null && obj.distanceTo() < 5) {
                        obj.interact(SceneObjects.Option.FIRST);
                    } else {
                        getObstacles().interact(SceneObjects.Option.FIRST);
                    }
                    Time.sleep(1500);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
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
    private SceneObject getObstacles() {
        for(int o : obstacles) {
            SceneObject obj = SceneObjects.getClosest(o);
            if(obj != null && obj.distanceTo() < 8) {
                return obj;
            }
        }
        return null;
    }
}

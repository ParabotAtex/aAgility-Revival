package org.parabot.scriptwriter.revival.aAgility.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aAgility.core.Core;
import org.parabot.scriptwriter.revival.aAgility.data.Course;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Menu;

public class PickupMarks implements Strategy {
    private final int markId = 11849;
    private int tryCount = 0;
    @Override
    public boolean activate() {
        return GroundItems.getClosest(markId) != null
                && GroundItems.getClosest(markId).distanceTo() < 2;
    }

    @Override
    public void execute() {
        if(tryCount == 3) {
            resetCourse();
            tryCount = 0;
        } else {
            if(GroundItems.getClosest(markId) != null) {
                GroundItems.getClosest(markId).take();
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return GroundItems.getClosest(markId) == null;
                    }
                }, 2000);
                if (GroundItems.getClosest(markId) != null) {
                    tryCount++;
                } else {
                    tryCount = 0;
                }
            }
        }
    }

    private void resetCourse() {
        Menu.clickButton(19210);
        Time.sleep(3000);
        Menu.clickButton(1167);
        Time.sleep(3000);
        if(Core.getSettings().getSelectedCourse() == Course.GNOME) {
            Menu.clickButton(2495);
            Time.sleep(3000);
        } else if (Core.getSettings().getSelectedCourse() == Course.ARDOUGNE) {
            Menu.clickButton(2498);
            Time.sleep(3000);
        }
    }
}

package org.parabot.scriptwriter.revival.aAgility.core;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.revival.aAgility.data.Settings;
import org.parabot.scriptwriter.revival.aAgility.strategies.PickupMarks;
import org.parabot.scriptwriter.revival.aAgility.strategies.TraverseArdougneCourse;
import org.parabot.scriptwriter.revival.aAgility.strategies.TraverseGnomeCourse;
import org.parabot.scriptwriter.revival.aAgility.ui.GUI;

import java.util.ArrayList;

@ScriptManifest(author = "Atex",
        category = Category.AGILITY,
        description = "Does agility",
        name = "aAgility", servers = { "Revival" },
        version = 0.3)
public class Core extends Script {
    ArrayList<Strategy> strategies = new ArrayList<>();
    private static Settings settings;

    @Override
    public boolean onExecute() {
        strategies.add(new PickupMarks());
        strategies.add(new TraverseGnomeCourse());
        strategies.add(new TraverseArdougneCourse());
        provide(strategies);

        GUI gui = new GUI();
        while(gui.isVisible()) {
            Time.sleep(500);
        }
        if(gui.getSettings() == null) {
            System.out.println("Invalid input");

            return false;
        } else {
            settings = gui.getSettings();
        }

        return true;
    }

    public static Settings getSettings() {
        return settings;
    }
}

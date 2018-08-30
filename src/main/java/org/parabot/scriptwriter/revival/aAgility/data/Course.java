package org.parabot.scriptwriter.revival.aAgility.data;

public enum Course {
    GNOME("Gnome course"), ARDOUGNE("Ardougne course");

    private String name;
    Course(String name) {
       this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

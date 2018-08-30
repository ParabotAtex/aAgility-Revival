package org.parabot.scriptwriter.revival.aAgility.data;

public class Settings {
    private Course selectedCourse;
    public Settings(Course course) {
        this.selectedCourse = course;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }
}

package com.ssjjsy.gradle

import com.ssjjsy.gradle.util.Log
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by SMinsss on 2017/6/13 12:57.
 */
public abstract class BasePlugin implements Plugin<Project> {

    protected Project project;

    @Override
    void apply(Project project) {

        println("========================Ssjjsy groovy plugin, project name is: ${project.name}========================");

        this.project = project;

        def sp = project.gradle.startParameter
        def p = sp.projectDir

        Log.setState(Log.LogState.None)
        Log.header "StartParameter is ${sp}, projectDir is ${p}"

        createExtension()

        configureProject()

        createTask()
    }

    protected void createExtension() {
        // 第三个project是参数
        project.extensions.create('syext', getExtensionClass(), project)
    }

    protected void configureProject() {
        project.gradle.buildFinished { result ->
            if(result.failure != null)  tidyUp()
        }
    }

    protected void createTask() {}

    protected <T extends BaseExtension> T getSyext() {
        return (T) project.syext
    }

    /** Restore state for DEBUG mode */
    protected void tidyUp() { }

    protected abstract Class<? extends BaseExtension> getExtensionClass()
}


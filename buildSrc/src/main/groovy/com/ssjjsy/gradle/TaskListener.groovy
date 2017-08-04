package com.ssjjsy.gradle

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import org.gradle.util.Clock;

import com.ssjjsy.gradle.util.Log

/**
 * Created by Administrator on 2017/6/30.
 */

public class TaskListener implements TaskExecutionListener, BuildListener {

    private Clock clock
    private times = []

    @Override
    void buildStarted(Gradle gradle) {

    }

    @Override
    void settingsEvaluated(Settings settings) {

    }

    @Override
    void projectsLoaded(Gradle gradle) {

    }

    @Override
    void projectsEvaluated(Gradle gradle) {

    }

    @Override
    void buildFinished(BuildResult buildResult) {
        // After all gradle task execute
//        Log.header "--------------------All Task spend time--------------------"
//        times.each {time ->
//            Log.success("task ${time[1]} spend time: ${time[0]} ms")
//        }
    }

    @Override
    void beforeExecute(Task task) {
        clock = new Clock()
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        def ms = clock.timeInMs
        times.add([ms, task.path])
        if(task.name.startsWith("transform")) {
            Log.header "task ${task.path}" + " , spend time: ${ms} ms"
            task.inputs.files.files.each {inputFile->
                Log.success "input: ${inputFile.absolutePath}"
            }
            Log.warn('Next show output')
            task.outputs.files.files.each {outputFile->
                Log.success "output: ${outputFile.absolutePath}"
            }
        }
    }

}

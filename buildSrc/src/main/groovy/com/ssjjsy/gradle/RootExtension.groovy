package com.ssjjsy.gradle

import com.ssjjsy.gradle.util.Log
import org.gradle.api.Project;

/**
 * Created by SMinsss on 2017/6/13 17:38.
 */
public class RootExtension extends BaseExtension{

    protected Project demoProject

    protected Project sdkProject

    protected Project pluginBaseProject

    protected Project pluginAssiProject

    protected Set<Project> libProject

    protected Set<Project> pluginLibProjects

    protected Set<Project> pluginAppProjects

    protected AndroidConfig androidConfig

    // 插件的输出目录
    protected File outputPluginDir

    protected File buildPluginDir

    protected String mP // the executing gradle project name
    protected String mT // the executing gradle task name

    public RootExtension(Project project) {
        super(project)
        // Parse gradle task
        def sp = project.gradle.startParameter
        def t = sp.taskNames[0]
        if(t != null) {
            def p = sp.projectDir
            def pn = null
            Log.header("Print in rootPlugin, taskNames[0]: ${t}")
            Log.header("Print it rootPlugin, projectDir: ${p}")
            if(p == null) {
                Log.failed("no project!!!")
            } else if(p != project.rootProject.projectDir){
                // gradlew -p [project.name] assembleRelease (-p 即选择某个project进行编译)
                pn = p.name
            }
            mP = pn
            mT = t
        }

        androidConfig = new AndroidConfig()
    }

    // 允许自定义
    public def androidConfig(Closure closure) {
        project.configure(androidConfig, closure)
    }

    class AndroidConfig {
        int compileSdkVersion = 25
        int minSdkVersion = 8
        int targetSdkVersion = 25
        String buildToolsVersion = '25.0.3'
    }
}

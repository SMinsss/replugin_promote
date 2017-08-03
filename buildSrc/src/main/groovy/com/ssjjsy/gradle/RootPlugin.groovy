package com.ssjjsy.gradle

import org.gradle.api.Project;

/**
 * Created by SMinsss on 2017/6/13 17:38.
 */
public class RootPlugin extends BasePlugin {

    public static final String PLUGIN_ASSETS_DIR = "/assets/plugins/"

    // lib
    // plugin-base
    // plugin-assistant
    // sdk
    // demo
    // plugin-app
    // plugin-lib

    private Map<String, Set<String>> bundleModules = [:]

    @Override
    void apply(Project project) {
        super.apply(project)
    }

    @Override
    protected Class<? extends BaseExtension> getExtensionClass() {
        return RootExtension.class
    }

    @Override
    protected RootExtension getSyext() {
        return super.getSyext()
    }

    @Override
    protected void configureProject() {
        super.configureProject()

        //project.gradle.addListener(new TaskListener())

        def rootExt = syext

        rootExt.libProject = new HashSet<>()
        rootExt.pluginAppProjects = new HashSet<>()
        rootExt.pluginLibProjects = new HashSet<>()

        project.afterEvaluate {

            def bundleTypes = [:]

            // 配置各个子项目的Android相关的min target compile之类的version
            def androidConfig = rootExt.androidConfig
            if(androidConfig != null) {
                project.subprojects {p->
                    p.afterEvaluate {
                        configVersions(p, androidConfig)
                    }
                }
            }

            project.subprojects {
                if(it.name.startsWith("plugin_one")) {
                    it.apply plugin: OnePlugin
                } else if(it.name == 'plugin_assi') {
                    //it.apply plugin: AppAssiPlugin
                    rootExt.pluginAssiProject = it
                } else if(it.name == 'plugin_base') {
                    //it.apply plugin: LibBasePlugin
                    rootExt.pluginBaseProject = it
                } else if(it.name == 'sdk') {
                    rootExt.sdkProject = it
                } else if(it.name == 'sydemo') {
                    it.apply plugin: DemoPlugin
                    rootExt.outputPluginDir = new File(it.projectDir, PLUGIN_ASSETS_DIR)
                    rootExt.demoProject = it
                } else if(it.name.startsWith('plugin_lib')) {
                    // 在plugin_之前
                    rootExt.pluginLibProjects.add(it)
                } else if(it.name.startsWith('plugin_')) {
                    //it.apply plugin: AppPlugin
                    rootExt.pluginAppProjects.add(it)
                } else if(it.name.startsWith('lib')) {
                    rootExt.libProject.add(it)
                }

            }

        }
    }

    // 注意，Small里面可以控制所有Module的Support包的版本，这里暂时没有需要，不添加
    protected void configVersions(Project p, RootExtension.AndroidConfig androidConfig) {
        if(!p.hasProperty('android')) {
            return
        }

        com.android.build.gradle.BaseExtension android = p.android
        if(androidConfig.compileSdkVersion != 0) {
            android.compileSdkVersion = androidConfig.compileSdkVersion
        }
        if(androidConfig.buildToolsVersion != null) {
            android.buildToolsVersion = androidConfig.buildToolsVersion
        }
        if(androidConfig.minSdkVersion != 0) {
            android.defaultConfig.minSdkVersion = androidConfig.minSdkVersion
        }
        if(androidConfig.targetSdkVersion != 0) {
            android.defaultConfig.targetSdkVersion = androidConfig.targetSdkVersion
        }
    }

    @Override
    protected void createTask() {
        super.createTask()
    }
}

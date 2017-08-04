package com.ssjjsy.gradle

import com.ssjjsy.gradle.util.Log
import org.gradle.api.Project;

/**
 * Created by SMinsss on 2017/6/13 17:38.
 */
public class RootExtension extends BaseExtension {

    private static final String FD_BUILD_SSJJSY = 'build-ssjjsy'
    private static final String FD_PRE_JAR = 'ssjjsy-pre-jar'
    private static final String FD_PRE_AP = 'ssjjsy-pre-ap'
    private static final String FD_PRE_IDS = 'ssjjsy-pre-ids'
    private static final String FD_PRE_LINK = 'ssjjsy-pre-link'
    private static final String FD_BASE = 'base'
    private static final String FD_LIBS = 'libs'
    private static final String FD_JAR = 'jar'
    private static final String FD_AAR = 'aar'

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

    private File preBuildDir

    /** Directory of pre-build host and android support jars */
    private File preBaseJarDir

    /** Directory of pre-build libs jars */
    private File preLibsJarDir

    /** Directory of pre-build resources.ap_ */
    private File preApDir

    /** Directory of pre-build R.txt */
    private File preIdsDir

    /** Directory of prepared dependencies */
    private File preLinkAarDir
    private File preLinkJarDir

    protected String mP // the executing gradle project name
    protected String mT // the executing gradle task name

    public RootExtension(Project project) {
        super(project)

        initPath()

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

    private void initPath() {
        preBuildDir = new File(project.projectDir, FD_BUILD_SSJJSY)
        def interDir = new File(preBuildDir, FD_INTERMEDIATES)
        def jarDir = new File(interDir, FD_PRE_JAR)
        preBaseJarDir = new File(jarDir, FD_BASE)
        preLibsJarDir = new File(jarDir, FD_LIBS)
        preApDir = new File(interDir, FD_PRE_AP)
        preIdsDir = new File(interDir, FD_PRE_IDS)
        def preLinkDir = new File(interDir, FD_PRE_LINK)
        preLinkJarDir = new File(preLinkDir, FD_JAR)
        preLinkAarDir = new File(preLinkDir, FD_AAR)
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

    public File getPreBuildDir() {
        return preBuildDir
    }

    public File getPreBaseJarDir() {
        return preBaseJarDir
    }

    public File getPreLibsJarDir() {
        return preLibsJarDir
    }

    public File getPreApDir() {
        return preApDir
    }

    public File getPreIdsDir() {
        return preIdsDir
    }

    public File getPreLinkJarDir() {
        return preLinkJarDir
    }

    public File getPreLinkAarDir() {
        return preLinkAarDir
    }

    public boolean isPluginLibProject(Project project) {
        boolean found = false
        if(pluginLibProjects != null) {
            found = pluginLibProjects.contains(project)
        }
        return found
    }
}

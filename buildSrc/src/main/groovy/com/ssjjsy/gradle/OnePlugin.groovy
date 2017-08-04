package com.ssjjsy.gradle

import com.android.build.gradle.api.BaseVariant
import com.ssjjsy.gradle.transform.StripAarTransform
import com.ssjjsy.gradle.transform.TestTransform
import com.ssjjsy.gradle.util.Log
import com.ssjjsy.gradle.util.TaskUtils;
import com.android.build.gradle.internal.tasks.PrepareLibraryTask
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencySet
import org.gradle.api.internal.artifacts.dependencies.DefaultProjectDependency

/**
 * Created by Administrator on 2017/8/2.
 */

public class OnePlugin extends AndroidPlugin {

    protected Set<Project> mDependentLibProjects
    protected Set<Project> mTransitiveDependentLibProjects
    protected Set<Project> mProvidedProjects
    protected Set<Project> mCompiledProjects
    protected Set<Map> mUserLibAars
    protected Set<File> mLibraryJars

    @Override
    protected Class<? extends BaseExtension> getExtensionClass() {
        return OneExtension.class
    }

    @Override
    protected OneExtension getSyext() {
        return project.syext
    }

    @Override
    protected void configureReleaseVariant(BaseVariant variant) {
        super.configureReleaseVariant(variant)

        syext.buildCaches = new HashMap<String, File>()
        project.tasks.withType(PrepareLibraryTask.class).each {
            Log.header "---OnePlugin, prepareLibraryTask: ${it.name}"
            TaskUtils.collectAarBuildCacheDir(it, syext.buildCaches)
        }
    }

    @Override
    protected void afterEvaluate(boolean released) {
        super.afterEvaluate(released)

        DependencySet compilesDependencies = project.configurations.compile.dependencies
        Set<DefaultProjectDependency> allLibs = compilesDependencies.withType(DefaultProjectDependency.class)
        Set<DefaultProjectDependency> pluginLibs = []
        mUserLibAars = []
        mDependentLibProjects = []
        mProvidedProjects = []
        mCompiledProjects = []
        Log.header('OnePlugin afterEvaluate')
        allLibs.each {
            Log.header("libs name: ${it.name}")
            if(rootSyext.isPluginLibProject(it.dependencyProject)) {
                pluginLibs.add(it)
                mProvidedProjects.add(it.dependencyProject)
                mDependentLibProjects.add(it.dependencyProject)
            } else {
                mCompiledProjects.add(it.dependencyProject)
                collectAarsOfLibrary(it.dependencyProject, mUserLibAars)
            }
        }

        collectAarsOfLibrary(project, mUserLibAars)

        Log.header '-------'
        Log.header('-- mUserLibAars')
        mUserLibAars.each {map->
            map.each {entry->
                Log.header("map key: ${entry.key} , value: ${entry.value}")
            }
            Log.header('-- ')
        }
        Log.header('        ')

        Log.header '-------'
        Log.header('-- mProvidedProjects')
        mProvidedProjects.each {it->
            Log.header("projectName: ${it.name}")
        }
        Log.header('        ')

        Log.header '-------'
        Log.header('-- mCompiledProjects')
        mCompiledProjects.each {it->
            Log.header("compileProjectName: ${it.name}")
        }
        Log.header('        ')

        if(!released) return

        //android.registerTransform(new TestTransform())
        android.registerTransform(new StripAarTransform())

    }

    @Override
    protected void hookPreReleaseBuild() {
        super.hookPreReleaseBuild()

        def libAars = new HashSet()


    }

    protected static def collectAarsOfLibrary(Project lib, HashSet outAars) {
        // lib.* self
        outAars.add(group: lib.group, name: lib.name, version: lib.version)
        // lib.* self for android plugin 2.3.0+
        File dir = lib.projectDir
        outAars.add(group: dir.parentFile.name, name: dir.name, version: lib.version)
        Log.header "collectAarsOfLibrary, lib.group: ${lib.group}, lib.name: ${lib.name}, dir.parentFile.name: ${dir.parentFile.name} " +
                ",dir.name: ${dir.name}"
    }
}

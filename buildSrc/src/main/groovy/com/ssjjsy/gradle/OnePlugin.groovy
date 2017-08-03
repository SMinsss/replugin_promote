package com.ssjjsy.gradle

import com.android.build.gradle.api.BaseVariant
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
        Set<DefaultProjectDependency> someLibs = []
        mUserLibAars = []
        mDependentLibProjects = []
        mProvidedProjects = []
        mCompiledProjects = []
        Log.header('OnePlugin afterEvaluate')
        allLibs.each {
            Log.header("libs name: ${it.name}")
        }
    }
}

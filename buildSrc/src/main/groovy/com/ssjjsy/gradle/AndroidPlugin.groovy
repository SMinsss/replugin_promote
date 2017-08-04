package com.ssjjsy.gradle

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.tasks.PrepareLibraryTask
import org.gradle.api.Project;
import com.ssjjsy.gradle.util.Log

/**
 * Created by SMinsss on 2017/6/20 16:40.
 */
public class AndroidPlugin extends BasePlugin {

    protected boolean released

    @Override
    void apply(Project project) {
        super.apply(project)

        released = isBuildingRelease()
    }

    @Override
    protected Class<? extends BaseExtension> getExtensionClass() {
        return AndroidExtension.class
    }

    @Override
    protected AndroidExtension getSyext() {
        return project.syext
    }

    protected RootExtension getRootSyext() {
        return project.rootProject.syext
    }

    protected com.android.build.gradle.BaseExtension getAndroid() {
        return project.android
    }

    @Override
    protected void configureProject() {
        super.configureProject()

        project.beforeEvaluate {
            beforeEvaluate(released)
        }

        project.afterEvaluate {
            afterEvaluate(released)

            // com.android.library没有这个属性
            if(!android.hasProperty('applicationVariants')) return

            android.applicationVariants.all { BaseVariant variant ->
                Log.header 'variant name: ' + variant.name
                if(variant.buildType.minifyEnabled) {
                    // 可以混淆
                    Log.header('can proguard!')
                    configureProguard()
                } else {
                    Log.header('can not proguard!')
                }

                if(variant.buildType.name != 'release') {
                    if(!released) {
                        configureDebugVariant(variant)
                    }
                } else {
                    if(released) {
                        configureReleaseVariant(variant)
                    }
                }
            }

        }
    }

    protected void beforeEvaluate(boolean released) { }

    protected void afterEvaluate(boolean released) {
        // Automatic add `small' dependency
//        if (rootSmall.smallProject != null) {
//            project.dependencies.add(smallCompileType, rootSmall.smallProject)
//        } else {
//            project.dependencies.add(smallCompileType, "${SMALL_AAR_PREFIX}$rootSmall.aarVersion")
//        }

        def preBuild = project.tasks['preBuild']
        if (released) {
            preBuild.doFirst {
                hookPreReleaseBuild()
            }
        } else {
            preBuild.doFirst {
                hookPreDebugBuild()
            }
        }
        preBuild.doLast {
            if (!released) {
            }
        }
    }

    protected void configureProguard() { }

    protected void hookPreDebugBuild() { }

    protected void hookPreReleaseBuild() { }

    protected void configureDebugVariant(BaseVariant variant) { }

    protected void configureReleaseVariant(BaseVariant variant) {
        // Hook variant tasks
        variant.assemble.doLast {
            tidyUp()
        }
    }

    protected boolean isBuildingRelease() {
        def mT = rootSyext.mT
        if(mT == null) {
            return false
        } else {
            return true
        }

    }

}

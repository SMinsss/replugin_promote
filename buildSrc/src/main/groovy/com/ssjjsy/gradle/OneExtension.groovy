package com.ssjjsy.gradle;

import org.gradle.api.Project;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/2.
 */

public class OneExtension extends AndroidExtension {

    /** Map of build-cache file */
    Map buildCaches

    /** Paths of aar to split */
    Set<Map> splitAars

    public OneExtension(Project project) {
        super(project);
    }
}

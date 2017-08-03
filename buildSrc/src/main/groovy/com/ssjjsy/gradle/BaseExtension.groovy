package com.ssjjsy.gradle

import org.gradle.api.Plugin;
import org.gradle.api.Project

/**
 * Created by SMinsss on 2017/6/13 17:14.
 */
public class BaseExtension {

    Project project

    public BaseExtension(Project project) {
        this.project = project
    }

}

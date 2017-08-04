package com.ssjjsy.gradle

import com.ssjjsy.gradle.util.Log

/**
 * Created by Administrator on 2017/8/3.
 */

public class LibPlugin extends AndroidPlugin {

    @Override
    protected void afterEvaluate(boolean released) {
        super.afterEvaluate(released)
        // 这里为了说明，com.android.library没有
        Log.header "LibPlugin project: ${project.name}"
        if(android.hasProperty('applicationVariants')) {
            Log.header 'HasProperty: applicationVariants'
        } else {
            Log.header 'Do not has property: applicationVariants'
        }
    }
}

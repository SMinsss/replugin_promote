package com.ssjjsy.gradle.transform;

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.ssjjsy.gradle.OneExtension
import com.ssjjsy.gradle.util.AarPath
import com.ssjjsy.gradle.util.Log
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.api.Task

import org.apache.commons.codec.digest.DigestUtils

/**
 * Created by Administrator on 2017/8/4.
 */

public class TestTransform extends Transform {

    @Override
    String getName() {
        return 'smTest'
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_JARS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental)
            throws IOException, TransformException, InterruptedException {
        inputs.each { TransformInput input ->
            /**
             * 遍历目录
             */
            input.directoryInputs.each { DirectoryInput directoryInput ->
                Log.header("TestTransform directoryInputs: ${directoryInput.file.absolutePath}")
                /**
                 * 获得产物的目录
                 */
                File dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY);
                String buildTypes = directoryInput.file.name
                String productFlavors = directoryInput.file.parentFile.name
                //这里进行我们的处理 TODO
                Log.header "Copying ${directoryInput.name} to ${dest.absolutePath}"
                /**
                 * 处理完后拷到目标文件
                 */
                FileUtils.copyDirectory(directoryInput.file, dest);
            }

            /**
             * 遍历jar
             */
            input.jarInputs.each { JarInput jarInput ->
                Log.header("TestTransform jarInput: ${jarInput.file.absolutePath}")
                String destName = jarInput.name;
                /**
                 * 重名名输出文件,因为可能同名,会覆盖
                 */
                def hexName = DigestUtils.md5Hex(jarInput.file.absolutePath);
                if (destName.endsWith(".jar")) {
                    destName = destName.substring(0, destName.length() - 4);
                }
                /**
                 * 获得输出文件
                 */
                File dest = outputProvider.getContentLocation(destName + "_" + hexName, jarInput.contentTypes, jarInput.scopes, Format.JAR);

                //处理jar进行字节码注入处理TODO

                FileUtils.copyFile(jarInput.file, dest);
                Log.header "Copying ${jarInput.file.absolutePath} to ${dest.absolutePath}"
            }
        }
    }
}

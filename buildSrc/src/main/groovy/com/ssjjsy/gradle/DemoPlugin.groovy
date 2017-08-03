package com.ssjjsy.gradle

import com.android.build.gradle.api.BaseVariant
import com.ssjjsy.gradle.util.Log

import org.tmatesoft.svn.core.wc.*

/**
 * Created by SMinsss on 2017/6/16 17:33.
 */
public class DemoPlugin extends AndroidPlugin {

    @Override
    protected void configureProject() {
        super.configureProject()
        Log.header 'getSdkVersion: ' + getSdkVersion()
        Log.header 'getBaseVersion: ' + getBaseVersion()
        Log.header 'getAssiVersion: ' + getAssiVersion()
    }

    @Override
    protected Class<? extends BaseExtension> getExtensionClass() {
        return DemoExtension
    }

    @Override
    protected void hookPreReleaseBuild() {
        super.hookPreReleaseBuild()
        project.delete rootSyext.project.getProjectDir().absolutePath + '/bin'
    }

    @Override
    protected void configureReleaseVariant(BaseVariant variant) {
        super.configureReleaseVariant(variant)

        def variantData = variant.variantData
        def scope = variantData.scope

        def assemble = variant.assemble

        assemble.doLast {
            variant.outputs.each {output->
                File outputFile = output.outputFile
                Log.header("${assemble.name} outputFile: " + outputFile.name)
                project.copy {
                    from outputFile.absolutePath
                    into rootSyext.project.getProjectDir().absolutePath + '/bin'
                    rename (outputFile.name, generateApkName(outputFile.name))
                }
            }
        }

    }

    // 用写死的路径去读取，避免有时没有include project导致找不到project
    private String getSdkVersion() {
        String classPath = ".src.com.ssjjsy.net.Ssjjsy".replace(".", java.io.File.separator)
        String projectDir = (rootSyext.project.projectDir.absolutePath + '.sdk.src').replace('.', File.separator)
        String verPath = projectDir + classPath + ".java"
        Log.header('sdk path: ' + verPath)
        String verLine = new File(verPath).filterLine { it =~ /public static final String VERSION = / }
        String sdkVersion = "${verLine.split("\"")[1]}" // 版本
        return sdkVersion
    }

    private String getBaseVersion() {
        String classPath = '.src.com.ssjjsy.sdk.SsjjsySDK'.replace('.', File.separator)
        String projectDir = (rootSyext.project.projectDir.absolutePath + '.plugins.base.src').replace('.', File.separator)
        String verPath = projectDir + classPath + ".java"
        Log.header('base path: ' + verPath)
        String verLine = new File(verPath).filterLine { it =~ /public static String SDK_VERSION = / }
        String baseVersion = "${verLine.split("\"")[1]}" // 版本
        return baseVersion
    }

    private String getAssiVersion() {
        String classPath = ".src.com.ssjjsy.sdk.Plugin".replace(".", java.io.File.separator)
        String projectDir = (rootSyext.project.projectDir.absolutePath + '.plugins.assistant.src').replace('.', File.separator)
        String verPath = projectDir + classPath + ".java"
        Log.header('assi path: ' + verPath)
        String verLine = new File(verPath).filterLine { it =~ /public static final String VERSION = / }
        String assiVersion = "${verLine.split("\"")[1]}" // 版本
        return assiVersion
    }

    private String getTime() {
        return  new Date().format("yyyyMMdd", TimeZone.getDefault());
    }

    private String getSvnVersion() {
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager clientManager = SVNClientManager.newInstance(options);
        SVNStatusClient statusClient = clientManager.getStatusClient();
        SVNStatus status = statusClient.doStatus(project.rootProject.projectDir, false);
        SVNRevision revision = status.getCommittedRevision();
        return revision.getNumber() + '';
    }

    private String generateApkName(String origin) {
        String prefix = origin.split("\\.")[0]
        String newName = prefix + "_sdk" + getSdkVersion() + "_base" + getBaseVersion() +
                "_assi" + getAssiVersion() + '_' + getTime() + '_svn' + getSvnVersion() + '.apk'
        return newName
    }
}

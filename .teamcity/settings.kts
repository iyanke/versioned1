import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.04"

project {

    vcsRoot(HttpsGithubComInnayanVersioned1)

    buildType(Tool)
    buildType(VersionedSettingsConfig)
}

object Tool : BuildType({
    name = "tool"

    steps {
        script {
            scriptContent = "echo %teamcity.tool.maven.3.5.0-beta-1%"
        }
    }

    requirements {
        contains("system.agent.name", "A")
        exists("fff")
    }
})

object VersionedSettingsConfig : BuildType({
    name = "Versioned Settings config1"
})

object HttpsGithubComInnayanVersioned1 : GitVcsRoot({
    name = "https://github.com/innayan/versioned1/"
    url = "https://github.com/innayan/versioned1/"
    branch = "refs/heads/master"
    param("useAlternates", "true")
})

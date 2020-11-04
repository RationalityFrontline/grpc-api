import com.jfrog.bintray.gradle.BintrayExtension.PackageConfig
import com.jfrog.bintray.gradle.BintrayExtension.VersionConfig
import java.util.Date

plugins {
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}

group = "org.rationalityfrontline.grpc"
version = "1.33.1"

val NAME = project.name
val DESC = "Merged grpc-api and grpc-context into a single jar"
val GITHUB_REPO = "RationalityFrontline/grpc-api"

publishing {
    publications {
        create<MavenPublication>("mavenPublish") {
            artifact(file("libs/grpc-api-$version-sources.jar")) {
                classifier = "sources"
            }
            artifact(file("libs/grpc-api-$version.jar"))
            pom {
                name.set(NAME)
                description.set("$NAME $version - $DESC")
                url.set("https://github.com/$GITHUB_REPO")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        name.set("RationalityFrontline")
                        email.set("rationalityfrontline@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/$GITHUB_REPO")
                }
            }
        }
    }
}

bintray {
    fun env(propertyName: String): String {
        return if (project.hasProperty(propertyName)) {
            project.property(propertyName) as String
        } else "Unknown"
    }

    user = env("BINTRAY_USER")
    key = env("BINTRAY_KEY")
    publish = true
    override = true
    setPublications("mavenPublish")
    pkg(closureOf<PackageConfig>{
        repo = "grpc"
        name = NAME
        desc = DESC
        setLabels("grpc", "grpc-api", "grpc-context", "jpms")
        setLicenses("Apache-2.0")
        publicDownloadNumbers = true
        githubRepo = GITHUB_REPO
        vcsUrl = "https://github.com/$githubRepo"
        websiteUrl = vcsUrl
        issueTrackerUrl = "$vcsUrl/issues"
        version(closureOf<VersionConfig> {
            name = "${project.version}"
            desc = DESC
            released = "${Date()}"
            vcsTag = name
        })
    })
}
# grpc-api
[![Maven Central](https://img.shields.io/maven-central/v/org.rationalityfrontline.workaround/grpc-api.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.rationalityfrontline.workaround%22%20AND%20a:%22grpc-api%22)
[![Apache License 2.0](https://img.shields.io/github/license/rationalityfrontline/grpc-api)](https://github.com/RationalityFrontline/grpc-api/blob/master/LICENSE)

This library is a temporary workaround for the issue ["gRPC Java is not usable from Java 9 modules"](https://github.com/grpc/grpc-java/issues/3522). 
It solves the split package problem mentioned in the issue by merging `grpc-api` and `grpc-context` into a single jar.

## Usage

Just add the following code blocks to your build.gradle.kts file.
(First exclude your dependency of "grpc-context" and "grpc-api", then add this library as a dependency)

**Gradle Kotlin DSL:**

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.rationalityfrontline.workaround:grpc-api:1.54.0")
}

configurations.all {
    exclude(group = "io.grpc", module = "grpc-context")
    exclude(group = "io.grpc", module = "grpc-api")
}
```

## Reference

https://github.com/grpc/grpc-java/issues/3522

## License

grpc-api is released under the [Apache 2.0 license](https://github.com/RationalityFrontline/grpc-api/blob/master/LICENSE).

```
Copyright 2020-2023 RationalityFrontline

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
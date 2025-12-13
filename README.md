# 🌑 Shadow

A lightweight **Compose Multiplatform** library providing customizable shadow effects — supporting both **solid colors** and **shaders (like gradients)**.

Easily apply smooth, dynamic shadows to your composables with simple `Modifier` or `DrawScope` extensions.

## 🎯 Platform Support

| Platform | Targets | Status |
|----------|---------|--------|
| Android  | `androidTarget` | ✅ Supported |
| iOS      | `iosArm64`, `iosX64`, `iosSimulatorArm64` | ✅ Supported |
| Desktop  | `jvm` (Windows, macOS, Linux) | ✅ Supported |
| Web      | `js` (JavaScript/Canvas) | ✅ Supported |
| Web      | `wasmJs` (WebAssembly) | ✅ Supported |

---

## 👀 Preview
![Shadow Preview](media/preview.gif)

## 📦 Installation

[![Maven Central](https://img.shields.io/maven-central/v/dev.stetsiuk/compose-shadow.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/dev.stetsiuk/compose-shadow)

### Kotlin Multiplatform Projects

Add the dependency to your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("dev.stetsiuk:compose-shadow:1.0.1")
        }
    }
}
```

The Gradle plugin will automatically select the correct platform-specific artifact for each target.

### Platform-Specific Projects

If you're working on a single-platform project (non-KMP), you can use platform-specific artifacts:

#### Android Only
```kotlin
dependencies {
    implementation("dev.stetsiuk:compose-shadow-android:1.0.1")
}
```

#### Desktop Only (JVM)
```kotlin
dependencies {
    implementation("dev.stetsiuk:compose-shadow-desktop:1.0.1")
}
```

#### iOS (for shared iOS code)
```kotlin
dependencies {
    implementation("dev.stetsiuk:compose-shadow-iosarm64:1.0.1")        // For devices
    implementation("dev.stetsiuk:compose-shadow-iosx64:1.0.1")          // For Intel simulators
    implementation("dev.stetsiuk:compose-shadow-iossimulatorarm64:1.0.1") // For M1/M2 simulators
}
```

#### Web
```kotlin
dependencies {
    implementation("dev.stetsiuk:compose-shadow-js:1.0.1")      // JavaScript
    implementation("dev.stetsiuk:compose-shadow-wasm-js:1.0.1") // WebAssembly
}
```

### Using Version Catalog

```toml
[versions]
compose-shadow = "1.0.1"

[libraries]
compose-shadow = { module = "dev.stetsiuk:compose-shadow", version.ref = "compose-shadow" }
```

Then in your `build.gradle.kts`:
```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.shadow)
        }
    }
}
```

## 🚀 Usage

### ✅ With color

```kotlin
val shape = RoundedCornerShape(24.dp)
Spacer(
    modifier = Modifier
        .shadow(
            fillStyle = ShadowFillStyle.WithColor(Color.Black.copy(0.1f)),
            blurRadius = 24.dp,
            shape = shape,
            spread = 4.dp,
            translationX = 0.dp,
            translationY = 16.dp
        )
        .size(200.dp)
        .background(Color.White, shape)
)
```

### 🌈 With Shader (Vertical Gradient Example)

```kotlin
val shape = RoundedCornerShape(24.dp)
Spacer(
    modifier = Modifier
        .shadow(
            fillStyle = ShadowFillStyle.WithShader {
                LinearGradientShader(
                    from = Offset(size.width / 2, 0f),
                    to = Offset(size.width / 2, size.height),
                    colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                )
            },
            blurRadius = 24.dp,
            shape = shape,
            spread = 4.dp,
            translationX = 0.dp,
            translationY = 16.dp
        )
        .size(200.dp)
        .background(Color.White, shape)
)
```

### 🛠️ Direct Usage in DrawScope

```kotlin
val shape = RoundedCornerShape(24.dp)
Spacer(
    modifier = Modifier
        .drawBehind { 
            drawShadow(
                fillStyle = ShadowFillStyle.WithColor(Color.Black.copy(0.1f)),
                blurRadius = 24.dp.toPx(),
                shape = shape,
                spread = 4.dp.toPx(),
                translationX = 0.dp.toPx(),
                translationY = 16.dp.toPx()
            )
        }
        .size(200.dp)
        .background(Color.White, shape)
)
  ```      

## 🙌 Credits

Made by @vasyl-stetsiuk


## 📃 License

Apache License 2.0. See [LICENSE](LICENSE) for full details.

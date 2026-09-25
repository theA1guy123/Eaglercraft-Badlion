import java.io.File

pluginManagement {
	repositories {
		gradlePluginPortal()
		maven {
			name = "eagler-teavm"
			url = uri("https://eaglercraft-teavm-fork.github.io/maven/")
		}
		maven {
			name = "eagler-local"
			url = uri(File(rootDir, "gradle/local-libs"))
		}
		mavenCentral()
	}
}

rootProject.name = "eaglercraft-workspace"

include("target_lwjgl_desktop")
include("target_teavm_javascript")

// The WASM-GC target depends on the unpublished
// com.resentclient.oss.eaglercraft.build plugin, which is not available in any
// reachable Maven repository. Building only the JS web client (e.g. the GitHub
// Pages workflow) must not fail while Gradle configures this project, so allow
// opting out of it with EAGLER_SKIP_WASM.
if (System.getenv("EAGLER_SKIP_WASM").isNullOrEmpty()) {
	include("target_teavm_wasm_gc")
}

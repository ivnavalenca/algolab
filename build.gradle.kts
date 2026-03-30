plugins {
    application
    java
}

group = "br.upe"
version = "1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {

    /*
     * ============================================================
     * GRÁFICOS (JFreeChart)
     * ============================================================
     */
    implementation("org.jfree:jfreechart:1.5.4")

    /*
     * ============================================================
     * PDF (OpenPDF)
     * ============================================================
     */
    implementation("com.github.librepdf:openpdf:1.3.30")
}

application {
    mainClass.set("br.upe.analisealgoritmos.Principal")
}

/*
 * ============================================================
 * CONFIGURAÇÕES DE EXECUÇÃO
 * ============================================================
 */
tasks.withType<JavaExec> {
    standardInput = System.`in`
}

/*
 * ============================================================
 * UTF-8 (IMPORTANTE)
 * ============================================================
 */
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
name := "TriggeriseChallenge"

version := "0.1"

javacOptions ++= Seq("-Xlint", "-encoding", "UTF-8")
scalaVersion := "2.13.0"
// format: off
scalacOptions ++= Seq(
  "-encoding", "utf-8", // Specify character encoding used by source files.
  "-explaintypes", // Explain type errors in more detail.
  "-language:higherKinds", // Allow higher-kinded types
  "-language:postfixOps", // Explicitly enables the postfix ops feature
  "-language:implicitConversions", // Explicitly enables the implicit conversions feature
  "-Ybackend-parallelism", "6", // Maximum worker threads for backend.
  "-Ybackend-worker-queue", "10", // Backend threads worker queue size.
  "-Ymacro-annotations", // Enable support for macro annotations, formerly in macro paradise.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
  "-Xmigration:2.14.0", // Warn about constructs whose behavior may have changed since version.
  //"-Xfatal-warnings", "-Werror",       // Fail the compilation if there are any warnings.
  "-Xlint:_", // Enables every warning. scalac -Xlint:help for a list and explanation
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-Wdead-code", // Warn when dead code is identified.
  "-Wextra-implicit", // Warn when more than one implicit parameter section is defined.
  //"-Wnumeric-widen",                   // Warn when numerics are widened.
  "-Woctal-literal", // Warn on obsolete octal syntax.
  //"-Wself-implicit",                   // Warn when an implicit resolves to an enclosing self-definition.
  "-Wunused:_", // Enables every warning of unused members/definitions/etc
  "-Wunused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Wunused:params", // Enable -Wunused:explicits,implicits. Warn if an explicit/implicit parameter is unused.
  "-Wunused:linted", // -Xlint:unused <=> Enable -Wunused:imports,privates,locals,implicits.
  //"-Wvalue-discard",                   // Warn when non-Unit expression results are unused.
)
// format: on
// These lines ensure that in sbt console or sbt test:console the -Ywarn* and the -Xfatal-warning are not bothersome.
// https://stackoverflow.com/questions/26940253/in-sbt-how-do-you-override-scalacoptions-for-console-in-all-configurations
/*scalacOptions in (Compile, console) ~= (_ filterNot { option =>
  option.startsWith("-Ywarn") || option == "-Xfatal-warnings" || option.startsWith("-Xlint")
})*/
//scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

libraryDependencies ++= Seq(
  "com.beachape"          %% "enumeratum" % "1.5.13",
  "com.github.pureconfig" %% "pureconfig" % "0.12.1",
  "org.scalatest"         %% "scalatest"  % "3.1.0-RC3" % Test,
)

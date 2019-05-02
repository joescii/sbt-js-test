package com.joescii.sbtjs

import sbt._
import Keys._


sealed trait Framework // Mostly placeholder for now
sealed trait Browser

object SbtJsTestPlugin extends AutoPlugin with SbtJsTestKeys {
  import SbtJsTestTasks._

  object autoImport extends SbtJsTestKeys {
    object JsTestBrowsers {
      case object Firefox38 extends Browser
//      case object InternetExplorer8 extends Browser
      case object InternetExplorer11 extends Browser
      case object Chrome extends Browser
//      case object Edge extends Browser
    }

    object JsTestFrameworks {
      case object Jasmine2 extends Framework
    }
  }

  override def trigger = allRequirements
  override lazy val projectSettings = sbtJsTestSettings

  val mainSrc = (sourceDirectory in Compile)
  val rsrc = (unmanagedResourceDirectories in Compile)
  val testSrc = (sourceDirectory in Test)
  val rsrcTest = (unmanagedResourceDirectories in Test)
  lazy val sbtJsTestSettings:Seq[Def.Setting[_]] = List(
    jsResources := {
      ((mainSrc.value / "js") +: (mainSrc.value / "javascript") +: rsrc.value).flatMap(r => (r ** "*.js").get)
    },
    watchSources ++= jsResources.map(identity).value,

    jsTestResources := {
      ((testSrc.value / "js") +: (testSrc.value / "javascript") +: rsrcTest.value).flatMap(r => (r ** "*.js").get)
    },
    watchSources ++= jsTestResources.map(identity).value,

    jsTestColor := true,
    jsTestBrowsers := Seq(autoImport.JsTestBrowsers.Chrome),
    jsFrameworks := Seq(autoImport.JsTestFrameworks.Jasmine2),
    jsTestTargetDir := ((target in Test) (_ / "sbt-js-test")).value,
    jsAsyncWait := false,
    jsAsyncWaitTimeout := None,

    jsTest := jsTestTask.value,
    jsTestOnly := jsTestOnlyTask.evaluated,
    jsLs := jsLsTask.value
  )
}

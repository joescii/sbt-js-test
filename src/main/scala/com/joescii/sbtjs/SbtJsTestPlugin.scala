package com.joescii.sbtjs

import implicits._
import sbt.{Def, Plugin}
import sbt.Keys._

object SbtJsTestPlugin extends Plugin with SbtJsTestKeys {
  import SbtJsTestTasks._

  val sbtJsTestSettings:Seq[Def.Setting[_]] = List(
    jsResources := Seq.empty,
    watchSources <++= jsResources.map(identity),
    jsTestColor := true,
    jsTestBrowsers := Seq(Chrome),
    jsTestTargetDir <<= (target in sbt.Test) (_ / "sbt-js-test"),

    jsTest <<= jsTestTask,
    jsLs <<= jsLsTask
  )
}

package com.joescii.sbtjs

class OneFailOnePendingSpec extends SbtJsTestSpec("oneFailOnePending") {
  "The oneFailOnePending project" should {
    "unsuccessfully run 'jsTest'" in {
      result = runSbt("jsTest")
      result.futureValue._1 shouldNot equal (0)
    }

    "print progress on a single line" in {
      result.futureValue._2.find(!_.startsWith("[info]")) shouldEqual Some(".F*")
    }

    "announce that 2 specs ran with 1 failure" in {
      result.futureValue._2.reverse.apply(4) shouldEqual "[info] 3 specs, 1 failure, 1 pending spec"
    }

    "announce that the task was unsuccessful" in {
      result.futureValue._2.last should startWith ("[error]")
    }
  }
}

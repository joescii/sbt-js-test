package com.joescii.sbtjs

class BigSuiteSpec extends SbtJsTestSpec("bigSuite") {
  "The bigSuite project" should {
    "successfully run 'jsTest'" in {
      result = runSbt("jsTest")
      result.futureValue._1 shouldEqual 0
    }

    "announce that 88 specs ran with 0 failures" in {
      result.futureValue._2.reverse.apply(3) shouldEqual "[info] 88 specs, 0 failures, 4 pending specs"
    }

    "announce that the task was successful" in {
      result.futureValue._2.last should startWith ("[success]")
    }
  }
}

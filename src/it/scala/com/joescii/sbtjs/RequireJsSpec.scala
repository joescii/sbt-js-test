package com.joescii.sbtjs

class RequireJsSpec extends SbtJsTestSpec("requireJs") {
  "The requireJs project" should {

    "successfully run async test" in {
      result = runSbt("jsTest")
      result.futureValue._1 shouldEqual 1
    }

    "announce that 2 specs ran with 1 failures" in {
      result.futureValue._2.reverse.apply(2) shouldEqual "[info] 2 specs, 1 failure"
    }

  }
}
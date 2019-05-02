package com.joescii.sbtjs

class TestOnlySpec extends SbtJsTestSpec("testOnly") {
  "The testOnly project" should {
    "successfully run 'jsTestOnly pass.js'" in {
      result = runSbt("jsTestOnly pass.js")
      result.futureValue._1 shouldEqual 0
    }

    "announce that 1 spec ran with 0 failures" in {
      result.futureValue._2.reverse.apply(3) shouldEqual "[info] 1 spec, 0 failures"
    }

    "announce that the task was successful" in {
      result.futureValue._2.last should startWith ("[success]")
    }

    "unsuccessfully run 'jsTestOnly fail.js'" in {
      result = runSbt("jsTestOnly fail.js")
      result.futureValue._1 shouldNot equal (0)
    }

    "announce that 1 spec ran with 1 failure" in {
      result.futureValue._2.reverse.apply(3) shouldEqual "[info] 1 spec, 1 failure"
    }

    "announce that the task was unsuccessful" in {
      result.futureValue._2.last should startWith ("[error]")
    }

  }
}
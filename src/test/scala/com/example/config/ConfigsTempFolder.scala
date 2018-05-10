package com.example.config

import java.io.File

import org.junit.rules.TemporaryFolder
import org.scalatest.FunSpec

trait ConfigsTempFolder extends FunSpec {
  def withTempFolder(testCode: File => Any) {
    var tempFolder = new TemporaryFolder()
    try {
      tempFolder.create()
      testCode(tempFolder.getRoot) // "loan" the fixture to the test
    }
    finally tempFolder.delete()
  }

}

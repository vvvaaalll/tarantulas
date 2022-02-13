import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication('D:\\AndroidStudioProjects\\tarantulas\\app\\build\\outputs\\apk\\debug\\app-debug.apk', true)

Mobile.startExistingApplication('vloboda.tarantulaProject.myapplication')

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Full Name'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Full Name (1)'), 'testUser', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Email'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Email (1)'), 'test@test.com', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Password'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Password (1)'), '123456', 0)

Mobile.pressBack()

Mobile.tap(findTestObject('Object Repository/android.widget.Button - REGISTER'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.ImageView'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Log out'), 0)

Mobile.closeApplication()


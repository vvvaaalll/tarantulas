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

Mobile.startExistingApplication('vloboda.tarantulaProject.myapplication')

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Already registered Login Here (2)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Email (6)'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Email (7)'), 'test@test.com', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Password (6)'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Password (7)'), '123456', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - LOG IN (2)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView (1)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.ImageView (3)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.ImageView (4)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.LinearLayout'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.RelativeLayout'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.ImageButton (1)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Species (2)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Species (3)'), 0)

Mobile.setText(findTestObject(''), 'Caribena Versicolor', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - Name (2)'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Name (3)'), 'Sebastijan', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.EditText - County of origin (2)'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - County of origin (3)'), 'Caribbean sea', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.RadioButton - 5'), 0)

Mobile.pressBack()

Mobile.tap(findTestObject('Object Repository/android.widget.RadioButton - 3'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.CheckBox (2)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - SUBMIT (1)'), 0)

Mobile.tap(findTestObject(''), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.ImageView (5)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Tarantulas'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - DELETE (1)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - YES'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.ImageView (6)'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Log out (2)'), 0)

Mobile.closeApplication()


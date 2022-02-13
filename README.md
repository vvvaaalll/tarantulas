# tarantulas

Aplication tarantulas is an aplication made for keeping track of owned tarantulas. It is using Firebase FireStore for storing tarantulas data, FireStorage for storing uploaded images and lastly FireAuth for user authentification.

# Activities:

Main Activity with listed owned tarantulas <br />
<img src="https://user-images.githubusercontent.com/59445209/153006666-c55388c0-a7eb-445c-9316-364d620dc734.jpg" width=50% height=50%>

Dropdown menu for logging off, deleting account or returning to list of tarantulas <br />
<img src="https://user-images.githubusercontent.com/59445209/153006688-804ec750-314f-4cc2-9644-948b31d82df3.jpg" width=50% height=50%>

Activity for adding new tarantulas <br />
<img src="https://user-images.githubusercontent.com/59445209/153006716-93c72a83-78f7-4c83-b38e-eec5c145feaf.jpg" width=50% height=50%>

Register Activity <br />
<img src="https://user-images.githubusercontent.com/59445209/153006724-4bbdcbea-8b3b-4288-8a00-b7d37f457ae2.jpg" width=50% height=50%>

Login Activity <br />
<img src="https://user-images.githubusercontent.com/59445209/153006736-26ed7a25-6f99-42dc-b4e4-bfcbc36a0880.jpg" width=50% height=50%>

# MiTTPP Project:

Katalon tests are made for college subject Methods and techniques of software support testing. <br />
Tested aplication is provided within repository.

Used software
---
- Android Studio
- Katalon Studio
- Katalon Recorder
- Appium

Katalon Studio and Recorder
---
<img src="https://user-images.githubusercontent.com/59445209/153749101-9aa1445e-6297-4582-82db-926ea98d419a.jpg" width=30% height=30%>
Katalon Studio performs test cases execution and allows test cases to be exported for further usage. Katalon Recorder is used for recording test cases via provided UI. Katalon Studio version 8.2.5 was used for creating this project.

Android Studio
---
<img src="https://user-images.githubusercontent.com/59445209/153749348-d7f609ca-b459-457e-9529-558826472cdb.png" width=30% height=30%>
Android studio Bumblebee (version 2021.1.1) has been used for creating aplication, managing virtual android devices and instaling aplication to virtual android device. For virtual android device project uses Pixel 4 with API version 30.   

Testing Process
---
- Prepare all needed software
- Choose test cases that would be suitable for testing
- Document test cases
- Install aplication to device
- Start Katalon Studio
- Start Katalon recorder
- Do steps you want yout test to follow
- When finished with steps for given test case, stop recording session and save test case
- Check steps and run script
- If neccesary fill in tests
- Click on Action->Run->Android and choose android device/Virtual machine you want to run tests on
- Run tests

Test cases
---
- RegisterAndLogOut
  -  This test case checks aplication behavior  when registering new account and logging out right after

| Step | Action |
| ------ | ------ |
| Preconditions | User doesn't have profile |
| 1 | Start aplication|
| 2 | Fill in Full Name, E-mail and password |
| 3 | Click "Register" button |
| 4 | Click on "..." dropdown menu  |
| 5 | Cllick on "Log Out"|

- LogIn_LogOut
  -  This test case checks aplication behavior  when logging in to existing account and logging out right after

| Step | Action |
| ------ | ------ |
| Preconditions | User already has account |
| 1 | Start aplication|
| 2 | Click on "Already registered? Login Here" |
| 3 | Fill in E-mail and password |
| 4 | Click "Log In" button |
| 5 | Click on "..." dropdown menu  |
| 6 | Cllick on "Log Out"|

- LogIn_AddTarantula_ClickDeleteAndQuit
  -  This test case check aplication behavior  when logging in to existing account, opening AddTarantula Activity, adding tarantula and then presing delete tarantula button, but quitting action when given an option

| Step | Action |
| ------ | ------ |
| Preconditions | User already has account |
| 1 | Start aplication|
| 2 | Click on "Already registered? Login Here" |
| 3 | Fill in E-mail and password |
| 4 | Click "Log In" button |
| 5 | Click "+" in top bar |
| 6 | Skip adding tarantula image |
| 7 | Fill in all data (Species, Name, County of Origin, temper, venom and urticating hairs) |
| 8 | Click on "Submit"  |
| 9 | Click on "Delete" button next to tarantula to delete it |
| 10 | In dialog that popps out click on "No" |
| 11 | Click on "..." dropdown menu  |
| 12 | Click on "Log Out"|



- ExitApp_stayLoggedIn
  -  This test case checks aplication behavior  when logging in to existing account, reentering aplication which should keep you logged in. After reentering aplication, delete tarantula added in "LogIn_AddTarantula_ClickDeleteAndQuit" test case

| Step | Action |
| ------ | ------ |
| Preconditions | User already has account |
| 1 | Start aplication|
| 2 | Click on "Already registered? Login Here" |
| 3 | Fill in E-mail and password |
| 4 | Click "Log In" button |
| 5 | Exit aplication |
| 6 | Start aplication |
| 7 | Click on "Delete" button next to tarantula to delete it |
| 8 | Click on "Yes" in dialog |
| 9 | Click on "..." dropdown menu  |
| 10 | Click on "Log Out"|

- AddTarantulaWithImage_DeleteTarantula
  -  This test case check aplication behavior  when logging in to existing account, opening AddTarantula Activity, adding tarantula, pressing back and adding same tarantula again. After adding same tarantula twice delete one via pressing Delete button and chossing Yes in pop up dialog

| Step | Action |
| ------ | ------ |
| Preconditions | User already has account |
| 1 | Start aplication|
| 2 | Click on "Already registered? Login Here" |
| 3 | Fill in E-mail and password |
| 4 | Click "Log In" button |
| 5 | Clinc "+" in top bar |
| 6 | Add tarantula image |
| 7 | Fill in all data (Species, Name, County of Origin, temper, venom and urticating hairs) |
| 8 | Click on "Submit"  |
| 9 | Press back on phone |
| 10 | Click on "Submit"  |
| 11 | Click on "Delete" button next to tarantula to delete it |
| 12 | In dialog that popps out click on "Yes" |
| 13 | Click on "..." dropdown menu  |
| 14 | Cllick on "Log Out"|


- LogIn_deleteAcount_tryToLogin
  -  This test case checks aplication behavior  when logging in to existing account, deleting account and trying to log in with deleted acount information

| Step | Action |
| ------ | ------ |
| Preconditions | User already has account |
| 1 | Start aplication|
| 2 | Click on "Already registered? Login Here" |
| 3 | Fill in E-mail and password |
| 4 | Click "Log In" button |
| 5 | Click on "..." dropdown menu  |
| 6 | Click on "Delete account"|
| 7 | Fill in E-mail and password |
| 8 | Click "Log In" button |


Test cases
---
Download [NodeJS](https://nodejs.org/en/download/)\
Download [Android Studio](https://developer.android.com/studio/) \
Download [Java SDK](https://www.oracle.com/java/technologies/sdk-downloads.html) \
Install appium by entering cmd command: npm install -g appium  \
Download [Katalon Studio](https://www.katalon.com/download/) 

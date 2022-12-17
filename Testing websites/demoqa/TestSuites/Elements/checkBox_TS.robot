*** Settings ***
Documentation   Test Suite testing Text Box with form
Resource        TestCases/Elements/CheckBox/checkBox_TC.robot
Resource        resources/SetupsAndTeardowns/Setup.robot
Resource        resources/SetupsAndTeardowns/Teardown.robot
Suite Setup     Open User Web Page  ${web_url}  ${browser}
Suite Teardown  Close All User Web Page
Test Setup      Reload Page
#Test Teardown   This Is After Test

Force Tags      Elements - Check Box


*** Variables ***
${web_url}=     https://demoqa.com/checkbox
${browser}=     Chrome


*** Test Cases ***
Expand And Collapse All With Buttons
    Expand And Collapse All

Expand And Collapse All One By One
    Expand With Arrow Button
    Collapse With Arrow Button

You Have Selected
    Select All Folders

Select And Half Select Icon
    Select One Folder
*** Settings ***
Documentation   Test Suite testing Radio Button
Resource        TestCases/Elements/RadioButton/radioButton_TC.robot
Resource        resources/SetupsAndTeardowns/Setup.robot
Resource        resources/SetupsAndTeardowns/Teardown.robot
Suite Setup     Open User Web Page  ${web_url}  ${browser}
Suite Teardown  Close All User Web Page
Test Setup      Reload Page
#Test Teardown   This Is After Test

Force Tags      Elements - Radio Button


*** Variables ***
${web_url}=     https://demoqa.com/radio-button
${browser}=     Chrome


*** Test Cases ***
Select Radio Button
    Select Button And Validate Response

Disabled Radio Button
    Radio Button Is Disabled

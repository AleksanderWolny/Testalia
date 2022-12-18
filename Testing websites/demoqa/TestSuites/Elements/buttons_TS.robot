*** Settings ***
Documentation   Test Suite testing Buttons
Resource        TestCases/Elements/Buttons/buttons_TC.robot
Resource        resources/SetupsAndTeardowns/Setup.robot
Resource        resources/SetupsAndTeardowns/Teardown.robot
Suite Setup     Open User Web Page  ${web_url}  ${browser}
Suite Teardown  Close All User Web Page
Test Setup      Reload Page
#Test Teardown   This Is After Test

Force Tags      Elements - Web Tables


*** Variables ***
${web_url}=     https://demoqa.com/buttons
${browser}=     Chrome


*** Test Cases ***
Click Buttons With Mouse
    Double Click Mouse
    Right Click Mouse
    Click Mouse
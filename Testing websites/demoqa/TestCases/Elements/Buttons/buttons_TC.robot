*** Settings ***
Documentation   Test Cases for testing Buttons
Library         SeleniumLibrary
Library         BuiltIn
Resource        TestCases/Elements/Buttons/buttons_variables.robot


*** Keywords ***
Double Click Mouse
    [Documentation]     Double click button and check message
    element should be enabled  ${double_click_button}
    double click element  ${double_click_button}
    wait until element is visible  ${double_click_message}
    element text should be  ${double_click_message}  ${double_click_text}

Right Click Mouse
    [Documentation]     Right click button and check message
    element should be enabled  ${right_click_button}
    open context menu  ${right_click_button}
    wait until element is visible  ${right_click_message}
    element text should be  ${right_click_message}  ${right_click_text}

Click Mouse
    [Documentation]     Click button and check message
    element should be enabled  ${click_button}
    click element  ${click_button}
    wait until element is visible  ${click_message}
    element text should be  ${click_message}  ${click_text}

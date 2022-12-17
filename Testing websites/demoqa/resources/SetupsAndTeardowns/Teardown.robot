*** Settings ***
Documentation       Teardown after tests
Library             SeleniumLibrary


*** Keywords ***
Close All User Web Page
    [Documentation]     Close all opened browser
    close all browsers
*** Settings ***
Documentation       Setup before tests
Library             SeleniumLibrary


*** Keywords ***
Open User Web Page
    [Documentation]     Open Web Page user provided
    [Arguments]     ${web_url}  ${browser}
    open browser    url=${web_url}  browser=${browser}
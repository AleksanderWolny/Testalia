*** Settings ***
Documentation   Test Suite testing Links
Resource        TestCases/Elements/Links/links_TC.robot
Resource        resources/SetupsAndTeardowns/Setup.robot
Resource        resources/SetupsAndTeardowns/Teardown.robot
Suite Setup     Open User Web Page  ${web_url}  ${browser}
Suite Teardown  Close All User Web Page
Test Setup      Reload Page
#Test Teardown   This Is After Test

Force Tags      Elements - Web Tables


*** Variables ***
${web_url}=     https://demoqa.com/links
${browser}=     Chrome


*** Test Cases ***
#New Tab Link
#    Home Link Tab
#    Dynamic Home Link Tab
API Link Call
    Created Link
    No Content Link
    Moved Link
    Bad Request Link
    Unauthorized Link
    Forbidden Link
    Not Found Link
*** Settings ***
Documentation   Test Suite testing Web Tables
Resource        TestCases/Elements/WebTables/webTables_TC.robot
Resource        resources/SetupsAndTeardowns/Setup.robot
Resource        resources/SetupsAndTeardowns/Teardown.robot
Suite Setup     Open User Web Page  ${web_url}  ${browser}
Suite Teardown  Close All User Web Page
Test Setup      Reload Page
#Test Teardown   This Is After Test

Force Tags      Elements - Web Tables


*** Variables ***
${web_url}=     https://demoqa.com/webtables
${browser}=     Chrome


*** Test Cases ***
#Leave Form
#    Click Close Button And Leave Form
#
#Validate Form
#    Input Empty Data And Valid Data
#
#Add Remove and Edit Data From Table
#    Add New Row
#    Edit Row
#    Remove Row
#
#Search Input Table
#    Search For Specific Row
#
#Numbers Of Visible Rows On Page
#    Numbers Of Rows On Page
Go To Next Page And Previous Page
    Go To Page
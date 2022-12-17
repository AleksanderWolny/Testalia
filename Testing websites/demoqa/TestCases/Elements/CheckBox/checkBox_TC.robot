*** Settings ***
Documentation   Test Cases for testing Check Box
Library         SeleniumLibrary
Library         BuiltIn
Library         Collections
Library         TestCases/Elements/CheckBox/checkBox_code.py
Library         String
Resource        TestCases/Elements/CheckBox/checkBox_variables.robot


*** Keywords ***
Expand And Collapse All
    [Documentation]     Click expand all button, check if there is no folder with css parent-close, click collapse
    click button        ${expand_all_bt}
    sleep   1
    page should contain element     ${not_expanded_folder}  limit=0
    sleep   1
    click button        ${collapse_all_bt}
    sleep   1
    page should contain element     ${not_expanded_folder}  limit=1

Expand With Arrow Button
    [Documentation]     Click arrow button and expand folder
    ${count}=   get element count   ${not_expanded_folder}
    WHILE   ${count} > 0
        click element    ${arrow_button_close}
        ${count}=   get element count   ${not_expanded_folder}
    END

Collapse With Arrow Button
    [Documentation]     Click arrow button and collapse folder
    ${count}=   get element count   ${expanded_folder}
    WHILE   ${count} > 0
        ${arrow_elements}=      get webelements     ${arrow_button_open}
        FOR  ${ele}  IN  ${arrow_elements}
            click element    ${ele}
        END
        ${count}=   get element count   ${expanded_folder}
    END

Select All Folders
    [Documentation]     Click expand button, select first folder to select all, check matching output
    click button        ${expand_all_bt}
    @{list_folder}=    create list
    @{variable}=    get webelements  ${folders_name}
    FOR  ${v}  IN  @{variable}
        ${text}=    get text    ${v}
        ${new_text}=    CREATE FOLDER LIST  ${text}
        append to list  ${list_folder}  ${new_text}
    END
    click element   ${folder_not_checked}
    @{list_output}=    create list
    @{span_list}=   get webelements  ${resulst_spans}
    FOR  ${v}  IN  @{span_list}
        ${results_lists}=   get text  ${v}
        append to list  ${list_output}  ${results_lists}
    END
#    remove from list  ${list_output}  0
    should be equal  ${list_folder}  ${list_output}[1:]

Select One Folder
    [Documentation]     Select one folder inside tree, upper folder should have different icon
    click button        ${expand_all_bt}
    @{folders}=     get webelements     ${folder_not_checked}
    ${choose_folder}=   evaluate    random.choice($folders[1:])     random
    log   ${choose_folder}
    click element   ${choose_folder}
    ${count_checked}=   get element count   ${folder_checked}
    Should Be True  ${count_checked} > 0
    ${count_hafl_checked}=   get element count   ${folder_half_checked}
    Should Be True  ${count_hafl_checked} > 0

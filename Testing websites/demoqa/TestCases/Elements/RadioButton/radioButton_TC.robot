*** Settings ***
Documentation   Test Cases for testing Radio Button
Library         SeleniumLibrary
Resource        TestCases/Elements/RadioButton/radioButton_variables.robot


*** Keywords ***
Select Button And Validate Response
    [Documentation]     Select radio button and check response
    page should contain radio button        ${radio_button_yes_id}
    page should contain radio button        ${radio_button_impressive_id}
    page should contain radio button        ${radio_button_no_id}
    radio button should not be selected     ${radio_group_name}
    #   yes button
    click element                           ${label_button_yes}
    radio button should be set to           ${radio_group_name}     on
    ${confirm_text}=  get text  ${text_success}
    should be equal                         Yes     ${confirm_text}
    #   impressive button
    click element                           ${label_button_impressive}
    radio button should be set to           ${radio_group_name}     on
    ${confirm_text}=  get text  ${text_success}
    should be equal                         Impressive      ${confirm_text}

Radio Button Is Disabled
    [Documentation]     Verify that radio button is disabled
    page should contain radio button        ${radio_button_no_id}
    element should be disabled              ${radio_button_no_id}

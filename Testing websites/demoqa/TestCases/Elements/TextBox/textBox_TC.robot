*** Settings ***
Documentation   Test Cases for testing Text Box with form
Resource        TestCases/Elements/TextBox/textBox_variables.robot
Library         SeleniumLibrary
Library         BuiltIn


*** Keywords ***
Check Full Name Label And Input Text
    [Documentation]     Get label and input text
    ${label_text}=      get text    ${full_name_label}
    should be equal     ${label_text}   ${full_name_label_text}
    ${input_placeholder}=   get element attribute   ${full_name_input}  placeholder
    should be equal     ${input_placeholder}   ${full_name_input_placeholder}

Insert Into Full Name Input
    [Documentation]     Input text provided in Test Scenario into full name input
    [Arguments]     ${full_name_text}
    input text      ${full_name_input}  ${full_name_text}   clear=True

Check Email Label And Input Text
    [Documentation]     Get email text
    ${label_text}=      get text    ${email_label}
    should be equal     ${label_text}   ${email_label_text}
    ${input_placeholder}=   get element attribute   ${email_input}  placeholder
    should be equal     ${input_placeholder}   ${email_input_placeholder}

Insert Into Email Input
    [Documentation]     Input text provided in Test Scenario into email input
    [Arguments]     ${email_text}
    input text      ${email_input}  ${email_text}   clear=True

Check Current Address Label And Input Text
    [Documentation]     Get current adress text
    ${label_text}=      get text    ${current_adress_label}
    should be equal     ${label_text}   ${current_adress_label_text}
    ${input_placeholder}=   get element attribute   ${current_adress_input}  placeholder
    should be equal     ${input_placeholder}   ${current_adress_input_placeholder}

Insert Into Current Address Input
    [Documentation]     Input text provided in Test Scenario into current address input
    [Arguments]     ${adress_text}
    input text      ${current_adress_input}  ${adress_text}   clear=True

Check Permanent Address Label And Input Text
    [Documentation]     Get current adress text
    ${label_text}=      get text    ${permanent_address_label}
    should be equal     ${label_text}   ${permanent_address_label_text}

Insert Into Permanent Address Input
    [Documentation]     Input text provided in Test Scenario into current address input
    [Arguments]     ${permanent_text}
    input text      ${permanent_address_input}  ${permanent_text}   clear=True

Check Submit Button Text
    [Documentation]     Check submit button text
    ${bt_text}=     get text    ${submit_button}
    should be equal     ${bt_text}      ${submit_button_text}

Submit Form
    [Documentation]     Click submit button
    click button    ${submit_button}

Check Submit Output With All Data
    [Documentation]     Comparing output form with input data
    [Arguments]     ${full_name_text}  ${email_text}  ${adress_text}  ${permanent_text}
    wait until element is visible   ${output_elements}

    ${name_txt}=   catenate    SEPARATOR=  ${output_name_text}  ${full_name_text}
    ${get_text_name}=   get text    ${output_name}
    should be equal     ${get_text_name}    ${name_txt}

    ${email_txt}=   catenate    SEPARATOR=  ${output_email_text}  ${email_text}
    ${get_text_email}=   get text    ${output_email}
    should be equal     ${get_text_email}    ${email_txt}

    ${current_txt}=   catenate    SEPARATOR=  ${output_address_text}  ${adress_text}
    ${get_text_current}=   get text    ${output_address}
    should be equal     ${get_text_current}    ${current_txt}

    ${permanent_txt}=   catenate    SEPARATOR=  ${output_permanent_text}  ${permanent_text}
    ${get_text_permanent}=   get text    ${output_permanent}
    should be equal     ${get_text_permanent}    ${permanent_txt}

Submit Form With No Data
    [Documentation]     Output should not be visible with empty form
    element should not be visible   get webelements  ${output_elements}

Insert Wrong Email
    [Documentation]     Checking if red border appear over email input
    element attribute value should be   ${email_input}  class   ${email_input_wrong_value}

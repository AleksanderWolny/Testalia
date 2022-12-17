*** Settings ***
Documentation   Test Cases for testing Web Tables
Library         SeleniumLibrary
Library         BuiltIn
Resource        TestCases/Elements/WebTables/webTables_variables.robot


*** Keywords ***
Click Close Button And Leave Form
    [Documentation]     Enter form, then click button close to leave it
    click button  ${add_form_button}
    wait until element is visible  ${form_dialog}
    element should be visible  ${registration_form_label}
    click button  ${button_form_close}
    wait until element is not visible  ${form_dialog}
    element should not be visible  ${form_dialog}

Input Empty Data And Valid Data
    [Documentation]     Enter form and submit with empty data in every inputs.
    ...                 Border of each imput should turn into red and got image with wrong data.
    ...                 Next input correct, short data.
    ...                 Border now should be green with green check mark.
    ...                 Then leave form with submit button.
    click button  ${add_form_button}
    wait until element is visible  ${form_dialog}
    element should be visible  ${form_dialog}
    @{get_all_inputs}=  get webelements  ${all_form_inputs}
    #   default color
    Get Input Border Color And Background Image  ${default_color}  ${background-image_default}  @{get_all_inputs}
    #   red color
    click button  ${button_form_submit}
    sleep  1
    Get Input Border Color And Background Image  ${invalid_color}  ${background-image_url_invalid}  @{get_all_inputs}
    #   green color
    Text Input With Correct Short Data  ${short_text}  ${short_number}  ${email_text}  @{get_all_inputs}
    sleep  1
    Get Input Border Color And Background Image  ${valid_color}  ${background-image_url_valid}  @{get_all_inputs}
    click button  ${button_form_submit}
    element should not be visible  ${form_dialog}

Get Input Border Color And Background Image
    [Documentation]     Get border color and validate
    [Arguments]     ${color_validate}  ${img}  @{elements}
    FOR  ${ele}  IN  @{elements}
        ${border_color}=  call method  ${ele}  value_of_css_property  border-color
        ${background_img}=  call method  ${ele}  value_of_css_property  background-image
        log  ${background_img}
        log  ${img}
        should be equal  ${border_color}  ${color_validate}
        should be equal  ${background_img}  ${img}
    END

Text Input With Correct Short Data
    [Documentation]     Insert text into input, different input for email
    [Arguments]     ${text}  ${number}  ${email}  @{elements}
    FOR  ${ele}  IN  @{elements}
        clear element text  ${ele}
        ${element_id}=  get element attribute  ${ele}  id
        ${catenate_id}=  catenate  SEPARATOR=  id:  ${element_id}
        IF  '${catenate_id}' == '${email_input}'
            input text  ${ele}  ${email}
        ELSE IF  '${catenate_id}' == '${age_input}' or '${catenate_id}' == '${salary_input}'
            input text  ${ele}  ${number}
        ELSE
            input text  ${ele}  ${text}
        END
    END

Add New Row
    [Documentation]     Click add buton, fill form with data and submit. Check if row with data appear in table.
    click button  ${add_form_button}
    wait until element is visible  ${form_dialog}
    element should be visible  ${form_dialog}
    @{get_all_inputs}=  get webelements  ${all_form_inputs}
    Text Input With Correct Short Data  ${short_text}  ${short_number}  ${email_text}  @{get_all_inputs}
    click button  ${button_form_submit}
    wait until element is not visible  ${form_dialog}
    page should contain element  ${grid_cell_with_short_text}  limit=3
    page should contain element  ${grid_cell_with_short_email}  limit=1

Edit Row
    [Documentation]     Check if row exist, edit it and check again with new data.
    page should contain element  ${grid_cell_with_short_text}  limit=3
    page should contain element  ${grid_cell_with_short_email}  limit=1
    ${last_row_button_edit}=  get webelements  ${edit_button}
    click button  ${last_row_button_edit}[-1]
    wait until element is visible  ${form_dialog}
    element should be visible  ${form_dialog}
    @{get_all_inputs}=  get webelements  ${all_form_inputs}
    Text Input With Correct Short Data  ${short_text_2}  ${short_number_2}  ${email_text_2}  @{get_all_inputs}
    click button  ${button_form_submit}
    wait until element is not visible  ${form_dialog}
    page should not contain element  ${grid_cell_with_short_text}
    page should not contain element  ${grid_cell_with_short_email}
    page should contain element  ${grid_cell_with_short_text_2}  limit=3
    page should contain element  ${grid_cell_with_short_email_2}  limit=1

Remove Row
    [Documentation]     Check if row exist, remove it from table and check again.
    page should contain element  ${grid_cell_with_short_text_2}  limit=3
    page should contain element  ${grid_cell_with_short_email_2}  limit=1
    ${last_row_button_delete}=  get webelements  ${delete_button}
    click button  ${last_row_button_delete}[-1]
    page should not contain element  ${grid_cell_with_short_text_2}
    page should not contain element  ${grid_cell_with_short_email_2}

Search For Specific Row
    [Documentation]     Check if datas not exist in table. Insert two datas into form. Search for one email adress.
    #   page not containt datas
    page should not contain element  ${grid_cell_with_short_text}
    page should not contain element  ${grid_cell_with_short_email}
    page should not contain element  ${grid_cell_with_short_text_2}
    page should not contain element  ${grid_cell_with_short_email_2}
    #   add first data
    click button  ${add_form_button}
    wait until element is visible  ${form_dialog}
    element should be visible  ${form_dialog}
    @{get_all_inputs}=  get webelements  ${all_form_inputs}
    Text Input With Correct Short Data  ${short_text}  ${short_number}  ${email_text}  @{get_all_inputs}
    click button  ${button_form_submit}
    wait until element is not visible  ${form_dialog}
    #   add second data
    click button  ${add_form_button}
    wait until element is visible  ${form_dialog}
    element should be visible  ${form_dialog}
    @{get_all_inputs}=  get webelements  ${all_form_inputs}
    Text Input With Correct Short Data  ${short_text_2}  ${short_number_2}  ${email_text_2}  @{get_all_inputs}
    click button  ${button_form_submit}
    wait until element is not visible  ${form_dialog}
    #   page should containt both datas
    page should contain element  ${grid_cell_with_short_text}  limit=3
    page should contain element  ${grid_cell_with_short_email}  limit=1
    page should contain element  ${grid_cell_with_short_text_2}  limit=3
    page should contain element  ${grid_cell_with_short_email_2}  limit=1
    #   page should containt only one data
    input text  ${search_input}  ${email_text_2}
    page should not contain element  ${grid_cell_with_short_text}
    page should not contain element  ${grid_cell_with_short_email}
    page should contain element  ${grid_cell_with_short_text_2}  limit=3
    page should contain element  ${grid_cell_with_short_email_2}  limit=1

Numbers Of Rows On Page
    [Documentation]     Get numbers of all rows and compare with numbers of selected rows from button.
    FOR  ${option}  IN  @{select_options}
        select from list by value  ${select_nr_of_rows}  ${option}
        list selection should be  ${select_nr_of_rows}  ${option}
        page should contain element  ${table_rows}  limit=${option}
    END

Use Button To Change Page
    [Documentation]     Check if buttons are disabled. Change page row to 5, add few datas. Validate that next page is
    ...                 available. Click next page. Check if previous page is aviable.

Change Page With Number
    [Documentation]     Change page with number.
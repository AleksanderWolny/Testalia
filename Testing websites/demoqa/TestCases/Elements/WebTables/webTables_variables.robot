*** Settings ***
Documentation    Variables for Web Tables

*** Variables ***
#   buttons
${add_form_button}              id:addNewRecordButton
${search_input}                 id:searchBox
${select_nr_of_rows}            xpath://select[@aria-label='rows per page']
@{select_options}               5  10  20  25  50  100
${previous_button}              xpath://button[text()='Previous']
${next_button}                  xpath://button[text()='Next']


#   form - labeles
${form_dialog}                  xpath://div[@role='dialog']
${registration_form_label}      id:registration-form-modal

#   form - inputs
${all_form_inputs}              xpath://form[@id='userForm']//input
${first_name_input}             id:firstName
${last_name_input}              id:lastName
${email_input}                  id:userEmail
${age_input}                    id:age
${salary_input}                 id:salary
${department_input}             id:department


#   form - buttons
${button_form_close}            xpath://button[@class='close']
${button_form_submit}           id:submit

#   text to input
${short_text}                   aaaaaa
${short_number}                 11111
${email_text}                   a@a.aa
${short_text_2}                 bbbbbb
${short_number_2}               222222
${email_text_2}                 b@b.bb
${css_attribute}                border-color

#   input border color
${default_color}                rgb(206, 212, 218)
${invalid_color}                rgb(220, 53, 69)
${valid_color}                  rgb(40, 167, 69)
${background-image_default}     none
${background-image_url_invalid}  url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' stroke='%23dc3545' viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' r='4.5'/%3e%3cpath stroke-linejoin='round' d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' fill='%23dc3545' stroke='none'/%3e%3c/svg%3e")
${background-image_url_valid}   url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8' viewBox='0 0 8 8'%3e%3cpath fill='%2328a745' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 1.1-.8l1.1 1.4 3.4-3.8c.6-.63 1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3e%3c/svg%3e")

#   table
${table_rows}                   xpath://div[@class='rt-tr-group']//div[@role='row']
${grid_cell}                    xpath://div[@role='gridcell']
${grid_cell_with_short_text}    xpath://div[@class='rt-tr-group']//div[@class='rt-td' and text()='${short_text}']
${grid_cell_with_short_email}   xpath://div[@class='rt-tr-group']//div[@class='rt-td' and text()='${email_text}']
${grid_cell_with_short_text_2}  xpath://div[@class='rt-tr-group']//div[@class='rt-td' and text()='${short_text_2}']
${grid_cell_with_short_email_2}  xpath://div[@class='rt-tr-group']//div[@class='rt-td' and text()='${email_text_2}']
${delete_button}                xpath://span[@title='Delete']
${edit_button}                  xpath://span[@title='Edit']
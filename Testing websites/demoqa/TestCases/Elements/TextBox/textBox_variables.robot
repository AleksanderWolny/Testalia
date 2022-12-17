*** Settings ***
Documentation    Variables for Text Box form

*** Variables ***
#   Full Name label and input
${full_name_label}                  id:userName-label
${full_name_label_text}             Full Name
${full_name_input}                  id:userName
${full_name_input_placeholder}      Full Name

#   Email label and input
${email_label}                  id:userEmail-label
${email_label_text}             Email
${email_input}                  id:userEmail
${email_input_placeholder}      name@example.com
${email_input_wrong_value}      mr-sm-2 field-error form-control

#   Current Address label and input
${current_adress_label}                  id:currentAddress-label
${current_adress_label_text}             Current Address
${current_adress_input}                  id:currentAddress
${current_adress_input_placeholder}      Current Address

#   Permanent Address label and input
${permanent_address_label}                  id:permanentAddress-label
${permanent_address_label_text}             Permanent Address
${permanent_address_input}                  id:permanentAddress

#   Submit button
${submit_button}        id:submit
${submit_button_text}   Submit

#   Output after submit
${output_elements}          xpath://div[@id="output"]/div/*
${output_name}              id:name
${output_name_text}         Name:
${output_email}             id:email
${output_email_text}        Email:
${output_address}           xpath://p[@id="currentAddress"]
${output_address_text}      Current Address :
${output_permanent}         xpath://p[@id="permanentAddress"]
${output_permanent_text}    Permananet Address :
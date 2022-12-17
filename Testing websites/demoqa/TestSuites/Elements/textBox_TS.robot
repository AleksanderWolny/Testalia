*** Settings ***
Documentation   Test Suite testing Text Box with form
Resource        TestCases/Elements/TextBox/textBox_TC.robot
Resource        resources/SetupsAndTeardowns/Setup.robot
Resource        resources/SetupsAndTeardowns/Teardown.robot
Suite Setup     Open User Web Page  ${web_url}  ${browser}
Suite Teardown  Close All User Web Page

Force Tags      Elements - Text Box


*** Variables ***
${web_url}=     https://demoqa.com/text-box
${browser}=     Chrome

${full_name_text}=  Adam Małysz
${email_text}=      adam@malysz.pl
${adress_text}=     Lorem ipsum dolor sit amet, consectetur adipiscing elit.
${permanent_text}=  Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.
${wrong_email}=     adam@malysz

*** Test Cases ***
Submitting Form With No Data
    Check Submit Button Text
    Submit Form
    Submit Form With No Data

Filling Form With Incorrect Email
    Insert Into Email Input     ${wrong_email}
    Submit Form
    Insert Wrong Email


Filling Form With Correct Data
    Check Full Name Label And Input Text
    Insert Into Full Name Input     ${full_name_text}
    Check Email Label And Input Text
    Insert Into Email Input     ${email_text}
    Check Current Address Label And Input Text
    Insert Into Current Address Input   ${adress_text}
    Check Permanent Address Label And Input Text
    Insert Into Permanent Address Input     ${permanent_text}
    Check Submit Button Text
    Submit Form
    Check Submit Output With All Data  ${full_name_text}  ${email_text}  ${adress_text}  ${permanent_text}

#Change Size Of Text Area
*** Settings ***
Documentation    Variables for Buttons

*** Variables ***
#   buttons
${double_click_button}          id:doubleClickBtn
${right_click_button}           id:rightClickBtn
${click_button}                 xpath://button[text()='Click Me']

#   messages
${double_click_message}         id:doubleClickMessage
${right_click_message}          id:rightClickMessage
${click_message}                id:dynamicClickMessage

#   texts
${double_click_text}            You have done a double click
${right_click_text}             You have done a right click
${click_text}                   You have done a dynamic click
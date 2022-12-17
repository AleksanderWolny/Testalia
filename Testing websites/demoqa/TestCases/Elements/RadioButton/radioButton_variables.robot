*** Settings ***
Documentation    Variables for Radio Button

*** Variables ***
#   buttons
${radio_group_name}                 like

${label_button_yes}                 xpath://label[@for='yesRadio']
${radio_button_yes_id}              id:yesRadio
${radio_button_yes_name}            yesRadio

${label_button_impressive}          xpath://label[@for='impressiveRadio']
${radio_button_impressive_id}       id:impressiveRadio
${radio_button_impressive_name}     impressiveRadio

${radio_button_no_id}               id:noRadio
${label_button_no_id}               xpath://label[@for='noRadio']

#   labels
${text_success}                     xpath://span[@class='text-success']
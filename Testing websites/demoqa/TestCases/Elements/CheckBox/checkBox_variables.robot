*** Settings ***
Documentation    Variables for Check Box

*** Variables ***
#   buttons
${expand_all_bt}                        xpath://button[@title='Expand all']
${collapse_all_bt}                      xpath://button[@title='Collapse all']
${arrow_button_open}                    xpath://*[local-name()="svg" and @class="rct-icon rct-icon-expand-open"]
${arrow_button_close}                   xpath://*[local-name()="svg" and @class="rct-icon rct-icon-expand-close"]

#   folder icon
${expanded_folder}                      xpath://*[local-name()="svg" and @class="rct-icon rct-icon-parent-open"]
${not_expanded_folder}                  xpath://*[local-name()="svg" and @class="rct-icon rct-icon-parent-close"]
${folders_name}                         xpath://span[@class='rct-title']
${folder_not_checked}                   xpath://*[local-name()="svg" and @class="rct-icon rct-icon-uncheck"]
${folder_checked}                       xpath://*[local-name()="svg" and @class="rct-icon rct-icon-check"]
${folder_half_checked}                  xpath://*[local-name()="svg" and @class="rct-icon rct-icon-half-check"]

#   results
${resulst_spans}                        xpath://div[@id='result']/span
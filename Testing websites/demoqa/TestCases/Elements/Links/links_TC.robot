*** Settings ***
Documentation   Test Cases for testing Links
Library         SeleniumLibrary
Library         BuiltIn
Resource        TestCases/Elements/Links/links_variables.robot


*** Keywords ***
Home Link Tab
    [Documentation]     Click home link and check title and url or new tab page
    element should be enabled  ${home_link}
    click link  ${home_link}
    switch window  locator=NEW
    title should be  ${new_tab_title}
    location should be  ${new_tab_url}
    switch window  locator=MAIN

Dynamic Home Link Tab
    [Documentation]     Click dynamic home link and check title and url or new tab page
    element should be enabled  ${dynamic_home_link}
    click link  ${dynamic_home_link}
    switch window  locator=NEW
    title should be  ${new_tab_title}
    location should be  ${new_tab_url}
    switch window  locator=MAIN

Created Link
    [Documentation]     Click created link and check link response
    reload page
    element should be enabled  ${created_link}
    click link  ${created_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${created_response}

No Content Link
    [Documentation]     Click no content link and check link response
    reload page
    element should be enabled  ${no_content_link}
    click link  ${no_content_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${no_conttentresponse}

Moved Link
    [Documentation]     Click moved link and check link response
    reload page
    element should be enabled  ${moved_link}
    click link  ${moved_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${moved_response}

Bad Request Link
    [Documentation]     Click bad request link and check link response
    reload page
    element should be enabled  ${bad_request_link}
    click link  ${bad_request_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${bad_request_response}

Unauthorized Link
    [Documentation]     Click unauthorized link and check link response
    reload page
    element should be enabled  ${unauthorized_link}
    click link  ${unauthorized_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${unauthorized_response}

Forbidden Link
    [Documentation]     Click forbidden link and check link response
    reload page
    element should be enabled  ${forbidden_link}
    click link  ${forbidden_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${forbidden_response}

Not Found Link
    [Documentation]     Click not found link and check link response
    reload page
    element should be enabled  ${invalid_url_link}
    click link  ${invalid_url_link}
    wait until element is visible  ${link_response}
    element text should be  ${link_response}  ${not_found_response}
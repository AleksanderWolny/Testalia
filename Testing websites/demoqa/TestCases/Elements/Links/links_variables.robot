*** Settings ***
Documentation    Variables for Links

*** Variables ***
#   new tab
${new_tab_url}              https://demoqa.com/
${new_tab_title}            ToolsQA

#   links
${home_link}                id:simpleLink
${dynamic_home_link}        id:dynamicLink

${created_link}             id:created
${no_content_link}          id:no-content
${moved_link}               id:moved
${bad_request_link}         id:bad-request
${unauthorized_link}        id:unauthorized
${forbidden_link}           id:forbidden
${invalid_url_link}         id:invalid-url

#   link message
${link_response}            id:linkResponse
${created_response}         Link has responded with staus 201 and status text Created
${no_conttentresponse}      Link has responded with staus 204 and status text No Content
${moved_response}           Link has responded with staus 301 and status text Moved Permanently
${bad_request_response}     Link has responded with staus 400 and status text Bad Request
${unauthorized_response}    Link has responded with staus 401 and status text Unauthorized
${forbidden_response}       Link has responded with staus 403 and status text Forbidden
${not_found_response}       Link has responded with staus 404 and status text Not Found
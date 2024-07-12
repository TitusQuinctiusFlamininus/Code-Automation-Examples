*** Settings ***
Documentation     Login and Successful create a KNIME Space.
...
...               Keywords are imported from the resource file
Resource          keywords.resource
Default Tags      positive

*** Variables ***
${LOGIN URL}      https://hub.knime.com/
${BROWSER}        Firefox
${SPACE_NAME}     K_SPACE_1

*** Test Cases ***
Create KNIME Space Successfully
    Connect to KNIME Portal
    Fill Credential Fields and Login
    Head To User Profile
    Add KNIME Space And Navigate to Hub
    Verify Space Exists On Spaces Hub
    Delete Space From Spaces Hub
    Logout from KNIME Portal
    Disconnect all Browser Instance

Delete KNIME Space Successfully
    Connect to KNIME Portal
    Fill Credential Fields and Login
    Head To User Profile
    Add KNIME Space And Navigate to Hub
    Delete Space From Spaces Hub
    Verify Space Does Not Exist On Spaces Hub
    Logout from KNIME Portal
    Disconnect all Browser Instance
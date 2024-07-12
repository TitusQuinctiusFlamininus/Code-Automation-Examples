*** Settings ***
Documentation     Simple example using SeleniumLibrary.
Library           SeleniumLibrary

*** Keywords ***


Add KNIME Space And Navigate to Hub
    Create a Space
    Navigate From Space Creation to Spaces Hub

Connect to KNIME Portal
    Open Browser    ${LOGIN URL}    ${BROWSER}

Disconnect all Browser Instance
    Close All Browsers

Logout from KNIME Portal
    SeleniumLibrary.Click Element  xpath://header/nav/div[2]/div/button/div/div/div[2]
    Sleep   3s
    SeleniumLibrary.Click Element  xpath://div[4]/ul/li[2]/a/div/div/span
    Sleep   3s

Fill Credential Fields and Login
    Sleep   3s
    SeleniumLibrary.Click Element  xpath://div[3]//button
    Sleep   3s
    SeleniumLibrary.Click Element  xpath://header/nav/div[2]//button
    Sleep   4s
    SeleniumLibrary.Input Text     xpath://*[@id="edit-name"]   ${username}
    Sleep   1s
    SeleniumLibrary.Input Text     xpath://*[@id="edit-pass"]   ${password}
    Sleep   1s
    SeleniumLibrary.Click Element  xpath://*[@id="edit-submit"]
    Sleep   4s

 Head To User Profile
     SeleniumLibrary.Click Element  xpath://header/nav/div[2]/div/button/div/div/div[2]
     Sleep   3s
     SeleniumLibrary.Click Element  xpath://div[2]/ul/li[1]/a/div/div/span
     Sleep   3s

 Create a Space
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div/div[2]/div/div[2]/div/button
     Sleep   3s
     # Create a Unique Name so it is easier to destroy it
     SeleniumLibrary.Input Text     xpath://section[1]/div/div/div/div[1]/h3/div/div/textarea   ${SPACE_NAME}
     Sleep  3s
     SeleniumLibrary.Click Element  xpath://section[1]/div/div/div/div[1]/h3/div/div/div/button[1]
     Sleep   2s
     # Let's make the space public visibly
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div[2]/div[3]/div/button
     Sleep   3s
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div[2]/div[3]/div/div/div[2]/div/button
     Sleep   3s
     SeleniumLibrary.Input Text     xpath://section[2]/div/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/div[2]/div/div[3]/div/form/div/input   ${SPACE_NAME}
     Sleep   3s
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/div[2]/div/div[4]/button
     Sleep   3s

 Navigate From Space Creation to Spaces Hub
     # Let's go back to the main spaces page : we are currently in the space-editing area
     SeleniumLibrary.Click Element  xpath://section/nav/ul/li[3]/a
     Sleep   3s

 Verify Space Exists On Spaces Hub
     SeleniumLibrary.Page Should Contain  ${SPACE_NAME}
     Sleep   3s

 Verify Space Does Not Exist On Spaces Hub
     SeleniumLibrary.Page Should Not Contain  ${SPACE_NAME}
     Sleep   3s

 Delete Space From Spaces Hub
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div/div[2]/div/div[1]/ul/li[1]/a/div[2]/h3[contains(text(), '${SPACE_NAME}')]
     Sleep   3s
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div[2]/div[3]/div/button
     Sleep   3s
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div[2]/div[3]/div/div/div[1]/div/button
     Sleep   3s
     SeleniumLibrary.Input Text     xpath://section[2]/div/div[2]/div[2]/div[3]/div/div/div[1]/div[2]/div[2]/div/div[3]/div/form/div/input   ${SPACE_NAME}
     Sleep   3s
     SeleniumLibrary.Click Element  xpath://section[2]/div/div[2]/div[2]/div[3]/div/div/div[1]/div[2]/div[2]/div/div[4]/button
     Sleep   3s
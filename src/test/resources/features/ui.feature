@ui
Feature: Comprehensive Validation of Widget Functionality


  Scenario Outline: Validate Click-ability of Widget Footer Links
    Given user is on the widget page
    When user clicks on "<footer>" link
    Then url should be "<expected_url>"
    Examples:
      | footer             | expected_url                                                                                                                  |
      | Terms & Conditions | https://protecht.com/consumer-terms/                                                                                          |
      | Privacy Policy     | https://protecht.com/privacy-policy/                                                                                          |
      | Insurance Terms    | https://static.sandbox.protecht.com/policy/cc6b3cd9-a52f-475b-869e-e01e546f3000/coverage/0bff8dddad734837957499eb0f1a5c34.pdf |


  Scenario: Currency Format Update in Widget
    Given user is on the widget page
    When user changes the currency to GBP
    And clicks the configure button
    And user should verify that the currency formats in the widget are updated to match the GBP "£100.00" format
    And user adds additional items with costs "45" to the widget
    And user clicks the configure button
    Then user should validate that the price "£45.00" and cost "£3.16" of coverage in the widget have been updated accordingly
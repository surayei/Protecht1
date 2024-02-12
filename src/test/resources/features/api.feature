@api @ui
  Feature: I should be able to retrieve an order from the Orders API for
    the provided Order Number


    Scenario: Retrieve and Validate an Order
      Given the Authentication API is available
      When I retrieve an order with oder number "29a43e12-d5ab-4ab1-9b8f-8563428f3c98"
      Then I should receive a valid order response
      And the sum of item costs should equal the order subtotal
      And the policy_numbers for each policy should follow the format "TGFS-*"
      And each item should include a customer object that matches the top-level customer object for the order
      And each item should have an associated policy under the policies array

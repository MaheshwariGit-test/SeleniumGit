Feature: Error validation
 i want to use this templete for my feature 
  
@ErrorValidation
  Scenario: Submit the Order positive 
    Given I landed on Ecommerce page
    When Logged in with userName "<userName>" and passWord "<passWord>"
    Then "Incorrect email or password." message is displayed on LandingPage
    
    
     Examples:
      | userName         | passWord    | productName       |
      |suguki@gmail.com  | Dummy@12356 | ZARA COAT 3       |
      
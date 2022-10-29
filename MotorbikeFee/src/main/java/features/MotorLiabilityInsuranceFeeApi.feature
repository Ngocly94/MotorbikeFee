@MotorLiabilityInsuranceFeeApi
Feature: I want to send motorbike insurance liability fee api successfully

  Scenario Outline: I want to send motorbike insurance liability fee api successfully
    Given I have Url and Method and Request body motorbike insurance liability fee api
      | Url       | Method | RequestBodyFileName  |
      | liability | POST   | MotorFeeRequest.json |
    When I send motorbike insurance liability fee api with "<FieldName1>" and "<Value1>" and "<FieldName2>" and "<Value2>"
    Then I verify the "<Expected Status Code>" and "<Expected code>" and "<Expected feeVat>" and "<Expected fee>" and "<Expected vat>"

    Examples: 
      | FieldName1 | Value1 | FieldName2 | Value2 | Expected Status Code | Expected code | Expected feeVat | Expected fee | Expected vat |
      | motor_type |     01 | duration   |     12 |                  200 |           000 |           60500 |        55000 |         5500 |
      | motor_type |     01 | duration   |     24 |                  200 |           000 |          121000 |       110000 |        11000 |
      | motor_type |     01 | duration   |     36 |                  200 |           000 |          181500 |       165000 |        16500 |
      | motor_type |     02 | duration   |     12 |                  200 |           000 |           66000 |        60000 |         6000 |
      | motor_type |     02 | duration   |     24 |                  200 |           000 |          132000 |       120000 |        12000 |
      | motor_type |     02 | duration   |     36 |                  200 |           000 |          198000 |       180000 |        18000 |
      | motor_type |     03 | duration   |     12 |                  200 |           000 |          319000 |       290000 |        29000 |
      | motor_type |     03 | duration   |     24 |                  200 |           000 |          638000 |       580000 |        58000 |
      | motor_type |     03 | duration   |     36 |                  200 |           000 |          957000 |       870000 |        87000 |

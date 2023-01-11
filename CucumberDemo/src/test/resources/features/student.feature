Feature: Student feature
  Scenario: Get the data of the student
    Given Data is present
          | rno    | address      | name      |
          | 1      | Pol          | Tom       |
          | 2      | Ind          | Com       |
    When  user request the application to get the data of the student
    Then return all the data of the student

  Scenario: Create the new entry for the student
    When  user requested to add new student with rno 9 address GER name YYY
    Then new student should be added with rno 9

    Scenario: Update the data on basis of rno
      When user request to update rno 9 with Address BRU name ZZZ
      Then Data of student should be updated with Address BRU name ZZZ for rno 9

      Scenario: Delete the data of specific rno
        When user request to delete the student with rno 9
        Then data of the student with rno 9 should be deleted




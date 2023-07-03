Feature: Testing git repository APIs


  Background: Set the headers for github APIs
    Given I stored the headers
      | Accept                      | Authorization                                   | X-GitHub-Api-Version |
      | application/vnd.github+json | Bearer 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 | 2022-11-28           |

  @git
  Scenario: Create a git repository for authenticated user
    When I try to create a repository with the following data and headers
     """
    {
      "name":"Hello-World",
      "description":"This is your first repo!",
      "homepage":"https://github.com",
      "private":false,
      "is_template":true
    }
    """
    Then the operation should be successful
    And should include the following values
      | ssh_url   | git@github.com:uttam-kumar-sahu/Hello-World.git |
      | full_name | uttam-kumar-sahu/Hello-World                    |
    And I store the "id" of the response as "ID"
    And I try to get the repository with name "Hello-World" and Owner "uttam-kumar-sahu"
    Then the operation should be successful
    And The "id" of the response as string should be equal to the stored "ID"
    And should include the following values
      | ssh_url   | git@github.com:uttam-kumar-sahu/Hello-World.git |
      | full_name | uttam-kumar-sahu/Hello-World                    |
    When I try to delete the repository with name "Hello-World" and Owner "uttam-kumar-sahu"
    Then the content must be deleted with 204 status code

  @git
  Scenario: Create a git repository for authenticated user, create 2 Issues and close the issues
    When I try to create a repository with the following data and headers
     """
    {
      "name":"Hello-World",
      "description":"This is your first repo!",
      "homepage":"https://github.com",
      "private":false,
      "is_template":true
    }
    """
    Then the operation should be successful
    And should include the following values
      | ssh_url   | git@github.com:uttam-kumar-sahu/Hello-World.git |
      | full_name | uttam-kumar-sahu/Hello-World                    |
    And I store the "id" of the response as "ID"
    And I try to get the repository with name "Hello-World" and Owner "uttam-kumar-sahu"
    Then the operation should be successful
    And The "id" of the response as string should be equal to the stored "ID"
    And should include the following values
      | ssh_url   | git@github.com:uttam-kumar-sahu/Hello-World.git |
      | full_name | uttam-kumar-sahu/Hello-World                    |
    When I create an issue with repo name "Hello-World" and Owner "uttam-kumar-sahu" and the following body
    """
    {
      "title":"Found a bug",
      "body":"having a problem with this",
      "assignees":[
       "uttam-kumar-sahu"
      ],
      "labels":[
        "bug"
      ]
    }
    """
    Then the operation should be successful
    And I create an issue with repo name "Hello-World" and Owner "uttam-kumar-sahu" and the following body
    """
    {
      "title":"Found a secong bug",
      "body":"having another problem with this",
      "assignees":[
       "uttam-kumar-sahu"
      ],
      "labels":[
        "bug"
      ]
    }
    """
    Then the operation should be successful
    When I try to update an Issue with repo name "Hello-World" , Owner "uttam-kumar-sahu", issue number 1 and the following body
    """
    {
      "title":"Found a bug",
      "body":"having a problem with this",
      "assignees":[
       "uttam-kumar-sahu"
      ],
      "state" : "close",
      "labels":[
        "bug"
      ]
    }
    """
    Then the operation should be successful
    And should include the following values
      | state  | closed |
      | number | 1      |
    And I try to update an Issue with repo name "Hello-World" , Owner "uttam-kumar-sahu", issue number 2 and the following body
    """
    {
      "title":"Found a secong bug",
      "body":"having another problem with this",
      "assignees":[
       "uttam-kumar-sahu"
      ],
      "state" : "close",
      "labels":[
        "bug"
      ]
    }
    """
    Then the operation should be successful
    And should include the following values
      | state  | closed |
      | number | 2      |
    When I try to delete the repository with name "Hello-World" and Owner "uttam-kumar-sahu"
    Then the content must be deleted with 204 status code



# Kameleoon Trial Task
Task you can find on the page https://developers.kameleoon.com/back-end-trial-task.html 
Application allows you to create user, add quotes, viewing (including getting a random quote), modification, and deletion of quotes. Also voting on quotes.
View of the top and worse 10 quotes, the details of each quote.

## Create user
endpount: /users

JSON:  
``` 
{
    "name": "user",
    "email": "user@user.ru",
    "password": "user"
      
} 
   ``` 

## Add quotes
endpount: /users/{userId}/quotes

JSON:  
``` 
{
  "content": "text"
      
} 
   ``` 
   
## Modification quotes
endpount: /users/{userId}/quotes/{quoteId}

JSON:  
``` 
{
  "content": "new text"
      
} 
   ``` 
   
If you are not owner of the quote, you can not change the quote
  
## Deletion quotes
endpount: /users/{userId}/quotes/{quoteId}

If you are not owner of the quote, you can not delete the quote
  
## Get quote by id
endpount: /quotes/{quoteId}
  
Any users(also not authorized) can see quote by id
## View random quote
endpount: /quotes/random

Any users (also not authorized) can see random quote. If on the server no any quotes, you will get error with information about it

## View top quote
 endpount: /quotes/top
 
 Any users(also not authorized) can see top quote. By default, you can see 10 top quotes. But if you want, you can change request parameter. For example /quotes/top?top=15
 If you on the server no any quotes, you will get error with information about it

## View worse quote
 endpount: /quotes/worse
 
 Any users(also not authorized) can see worse quote. By default, you can see 10 worse quotes. But if you want, you can change request parameter. For example  /quotes/worse?worse=15
 If on the server no any quotes, you will get error with information about it
 
## Add like or dislike to quote
endpount: /users/{userId}/quotes/{quoteId}

JSON:  
``` 
{
        "like": true,
        "dislike": false

}
   ``` 
One user can put only one like, or dislike. Not possible to put like and dislike in the same time.
If a user put like, and then put dislike, like from the quote will be removed.


To deploy an application based on a docker container, use the command docker-compose up
Images you can find by the link https://hub.docker.com/repository/docker/evgeniyakuzmina/kameleoon-test-img/general 

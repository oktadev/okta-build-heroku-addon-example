## Build a Heroku Add-on

Heroku Add-ons add capabilities for third-party services to your Heroku applications.

Start here: [https://devcenter.heroku.com/articles/building-an-add-on](https://devcenter.heroku.com/articles/building-an-add-on)

In this repo, you'll find branches that walk you through creating a fully-functional async-enabled Heroku add-on iteratively.

It's a Spring Boot and Spring Security application that features:

* implementation of the endpoints that Heroku will call for async provisioning and deprovisioning
* ability to exchange an incoming authorization code from Heroku for a Heroku access token
* ability to set environment variables for an application that uses this add-on
* ability to indicate when provisioning is complete to Heroku

This [screencast](https://youtu.be/zNiLa9ulBd4) uses this code and demonstrates how to build a Heroku add-on from basic building blocks.

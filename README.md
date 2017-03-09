
# [JavaSkop 2017](http://jug.mk/javaskop17)

Spring Boot SSO implemented with Oauth2 and Zuul proxy, focuses on client developer simplicity while providing specific authorization flows for web and desktop apps, mobile and IoT devices.

## Spring Boot SSO in 15 min

\#SpringBoot #Oauth2 #Zuul #MySQL #react #node #Thymeleaf

### Projects
* 01 Proxy
* 02 OAuth2
* 03 Admin
* 04 API
* 05 GUI

### Description
Spring Boot SSO implemented with Oauth2 and Zuul proxy, with .jdbc() instead of .inMemory(), focuses on client developer simplicity while providing specific authorization flows for web and desktop apps, mobile and IoT devices. Implementation .inMemory() will be faster, but if multiple clients are consuming this then it is better to have it in .jdbc(). Will use an OAuth2 server as the authenticator, so that we can also use it to grant tokens for the backend resource server using MySQL as client, user and token store. The goal is to work around CORS and the Same Origin Policy restriction of the browser and allow the UI to call the API even though they don't share the same origin using Zuul proxy.

### Architecture overview
<img src="diagrams/architecture-overview.jpg" width="500">

### 05 GUI
`npm install`  
`npm run build`  
`npm run start`  

### Postman/RESTClient

Base64 encode for "Authorization"  
Mac/Linux `echo -n 'clientId:clientSecret' | openssl base64`

| Access and refresh token | Call API with access token   |
| :---: | :---: |
| <img src="imgs/postman1.png" width="350"> | <img src="imgs/postman2.png" width="350"> |


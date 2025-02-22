# My blog simple 2-page site.
This is the project with learning goals to study Spring MVC

# Description
The application is written using the Spring framework (6.2.1 version)
This application can be running in any servlet container. I used the Tomcat.

First of all the correct folder for upload images should be specified.

This property is for mapping in web app location
```properties
application.images.upload-directory-handler-path=/upload
```
This property is for source files you will upload to
```properties
application.images.upload-directory-location-path=file:/home/user/myblog/src/main/webapp/upload
```

This property defines default image. It will be set if no images was selected in post.
```properties
application.images.default-image-path=/images/default_image.jpg
```

The application works using `H2` in memory database. 
All settings for it are in `application.properties` file.

The application has minimal number unit tests.

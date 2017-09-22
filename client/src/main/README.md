Config server endpoint - used to get the configuration

GET /{application}/{profile}[/{label}]

spring.application.name=App
spring.profiles.active=Profile
{labels}


Examples:

/myapp/dev/master
/myapp/prod/v2
/myapp/default

GET /{application}-{profile}.(yml | properties)


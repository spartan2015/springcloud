#how it works

#Basic routing

    GET /foo -> foo is the application.name that will receive the request
    GET /foo/1 -> app name foo, and will send /1 request further to actual service

if you want the app to receive /categories/1 instead use: 
    
    zuul.stripPrefix=false

All services are added by default, but you can filter out:

    zuul.ignoredServices=<pattern>

##Precise Routing

    zuul.routes.<route name>.path=/somePath/**
    zuul.routes.<route name>.serviceId=some_service_id
    zuul.ignored-services=some_service_id
   
Yeah, all 3 must be there

##Filters

    class MyFilter extends ZuulFilter{
        public Object run(){
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest req = ctx.getRequest();
            HttpServletResponse res = ctx.getResponse();
            ctx.set("foo","bar");
            String foo = ctx.get("bar");
            return null; // null is ok
        }
        public boolean shouldFilter(){
            return true;
        }
        public String filterType(){
            // "pre|route|post|error"
            return "pre";
        }
        public int filterOrder(){
            return 0;
        }
    }

Add filter with spring configuration class:

    @Configuration
    public class MyConfig{
        @Bean
        public ZuulFilter myFilter(){
            return new MyFilter();
        }
    }

###pre-request-routing

###route filters

###post-request-routing

###error-routing

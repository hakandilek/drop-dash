# drop-dash

Java port of the linux-dash monitoring tool using the Dropwizard framework.
 
A low-overhead monitoring web dashboard for a GNU/Linux machine. Simply drop-in
the app and go!

[View original linux-dash Demo](http://afaq.dreamhosters.com/linux-dash/)

![Demonstration](http://afaq.dreamhosters.com/linux-dash.PNG)

## Dropwizard integration

Simply use the `DropwizardBundle` in your `Service.initialize` method.

    public void initialize(Bootstrap<DropdashConfiguration> bootstrap) {
        bootstrap.addBundle(new DropdashBundle<DropdashConfiguration>() {
            @Override
            public HttpConfiguration getHttp(DropdashConfiguration configuration) {
                return configuration.getHttp();
            }
        });
    }

**Please note: If you would like to limit access to the webpage, please use a Dropwizard 
`Authenticator` to secure the resources.**

## Support

*The information listed here is currently limited and will expand shortly.*

* OS
    * Arch
    * Debian 6, 7
    * Ubuntu 11.04+
    * Linux Mint 16+
* Apache 2
* Nginx
* PHP 5
* Modern browsers

## Credits:
* [dropwizard](http://www.dropwizard.io/)
* [linux-dash](https://github.com/afaqurk/linux-dash)
* [Dashboard Template](http://www.egrappler.com/templatevamp-free-twitter-bootstrap-admin-template/)
* [Bootstrap](http://getbootstrap.com)

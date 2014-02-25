# drop-dash (Beta)
=======

Java port of the linux-dash monitoring tool using the Dropwizard framework.
 
A low-overhead monitoring web dashboard for a GNU/Linux machine. Simply drop-in
the app and go!

[**View Demo**](http://afaq.dreamhosters.com/linux-dash/) | [**View Features**](#features) | [**Installation Instructions**](#installation) | [**Documentation**](https://github.com/hakandilek/drop-dash/wiki)

![Demonstration](http://afaq.dreamhosters.com/linux-dash.PNG)

## Features
* A beautiful web-based dashboard for monitoring server info
* Live, on-demand monitoring of RAM, Load, Uptime, Disk Allocation, Users and many more system stats
* Drop-in Dropwizard integration
* Click and drag to re-arrange widgets
* Support for wide range of linux server flavors [(See Support section)](#support)

## Dropwizard integration

Simply use the `DropwizardBundle` in your `Service.initialize` method.

    public void initialize(Bootstrap<DropdashConfiguration> bootstrap) {
        bootstrap.addBundle(new DropdashBundle<DropdashConfiguration>() {
            @Override
            public HttpConfiguration getHttp(DropdashConfiguration configuration) {
                return configuration.getHttp();
            }
            @Override
            public String getAuthenticationRealm(DropdashConfiguration configuration) {
                return configuration.getAuthenticationRealm();
            }

            @Override
            public String getAuthenticationUser(DropdashConfiguration configuration) {
                return configuration.getAuthenticationUser();
            }

            @Override
            public String getAuthenticationPassword(DropdashConfiguration configuration) {
                return configuration.getAuthenticationPassword();
            }
        });
    }

And update your `config.yml` file for authentication details.

Note: devconfig.yml has authentication for `dropuser:droppass`.

## Support

*The information listed here is currently limited and will expand shortly.*

* OS
    * Arch
    * Debian 6, 7
    * Ubuntu 11.04+
    * Linux Mint 16+
* Modern browsers
* Dropwizard 0.6.2
* Java 6+

## Credits:
* [dropwizard](http://www.dropwizard.io/)
* [linux-dash](https://github.com/afaqurk/linux-dash)
* [Dashboard Template](http://www.egrappler.com/templatevamp-free-twitter-bootstrap-admin-template/)
* [Bootstrap](http://getbootstrap.com)
* [Font Awesome](http://fontawesome.io/)

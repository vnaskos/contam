<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.bugbusters.contam.orm.business.BusinessDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="ThemeStarz">

    <link href='http://fonts.googleapis.com/css?family=Roboto:300,400,700' rel='stylesheet' type='text/css'>
    <link href="assets/fonts/font-awesome.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="assets/css/bootstrap-select.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/jquery.slider.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/owl.carousel.css" type="text/css">
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">

    <title>Contam.gr</title>

</head>

<body class="page-homepage navigation-fixed-bottom has-fullscreen-map map-google" id="page-top" data-spy="scroll" data-target=".navigation" data-offset="90">
<!-- Wrapper -->
<div class="wrapper">

    <div class="navigation">
        <div class="container">
            <header class="navbar" id="top" role="banner">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <div class="navbar-brand nav" id="brand">
                        <a href="index-google-map-fullscreen.html"><img src="assets/img/logo.png" alt="brand"></a>
                    </div>
                </div>
                <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Homepage</a></li>
                        <li class="has-child"><a href="#">Pages</a>
                            <ul class="child-navigation">
                                <li><a href="about-us.html">About Us</a></li>
                                <li class="has-child"><a href="#">Error Pages</a>
                                    <ul class="child-navigation">
                                        <li><a href="403.html">403</a></li>
                                        <li><a href="404.html">404</a></li>
                                        <li><a href="500.html">500</a></li>
                                    </ul>
                                </li>
                                <li><a href="faq.html">FAQ</a></li>
                                <li><a href="terms-conditions.html">Terms & Conditions</a></li>
                            </ul>
                        </li>
                        <li><a href="contact.html">Contact</a></li>
                    </ul>
                </nav><!-- /.navbar collapse-->
                <div class="add-your-property">
                    <a href="submit.jsp" class="btn btn-default"><i class="fa fa-plus"></i><span class="text">Add Your Property</span></a>
                </div>
            </header><!-- /.navbar -->
        </div><!-- /.container -->
    </div><!-- /.navigation -->

    <div class="container">
        <div class="geo-location-wrapper">
            <span class="btn geo-location"><i class="fa fa-map-marker"></i><span class="text">Find My Position</span></span>
        </div>
    </div>

    <!-- Map -->
    <div id="map" class="has-parallax"></div>
    <!-- end Map -->

    <!-- Search Box -->
    <div class="search-box-wrapper">
        <div class="search-box-inner">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-4">
                        <div class="search-box map">
                            <form role="form" id="form-map" action="search" class="form-map form-search">
                                <h2>Search</h2>
                                <div class="form-group">
                                    <input type="text" name="keyword" value="${keyword}" class="form-control" id="search-box-property-id" placeholder="Business">
                                </div>
                                <input type="hidden" id="latitude-field" name="x" value="${latitude}" />
                                <input type="hidden" id="longitude-field" name="y" value="${longitude}" />
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default">Search Now</button>
                                </div><!-- /.form-group -->
                            </form><!-- /#form-map -->
                        </div><!-- /.search-box.map -->
                    </div><!-- /.col-md-3 -->
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div><!-- /.search-box-inner -->
    </div>
    <!-- end Search Box -->

    <!-- Page Content -->
    
    <div id="page-content">
        <section id="price-drop" class="block">
            <div class="container">
                <header class="section-title">
                    <h2>Results</h2>
                    <a href="#" class="link-arrow">More Results</a>
                </header>
                
                <c:choose>
                    <c:when test="${results.size() > 0}">
                        <c:forEach items="${results}" var="result" varStatus="loop">
                            <c:choose>
                                <c:when test="${(loop.index+1) / 4 != 0}">
                                    <div class="row">
                                </c:when>
                            </c:choose>
                                <div class="col-md-3 col-sm-6">
                                    <div class="property">
                                        <a href="property-detail.html">
                                            <div class="property-image">
                                                <img alt="" src="assets/img/properties/property-06.jpg">
                                            </div>
                                            <div class="overlay">
                                                <div class="info">
                                                    <h3>${result.name}</h3>
                                                    <figure>${result.address}</figure>
                                                </div>
                                            </div>
                                        </a>
                                    </div><!-- /.property -->
                                </div><!-- /.col-md-3 -->
                            <c:choose>
                                <c:when test="${(loop.index+1) / 4 != 0}">
                                    </div><!-- /.row-->
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div><!-- /.container -->
        </section><!-- /#results -->
        <section id="our-services" class="block">
            <div class="container">
                <header class="section-title"><h2>Our Services</h2></header>
                <div class="row">
                    <div class="col-md-4 col-sm-4">
                        <div class="feature-box equal-height">
                            <figure class="icon"><i class="fa fa-folder"></i></figure>
                            <aside class="description">
                                <header><h3>Wide Range of Properties</h3></header>
                                <p>More than 50.000 businesses around the wolrd that they trust our services</p>
                                <a href="#" class="link-arrow">Read More</a>
                            </aside>
                        </div><!-- /.feature-box -->
                    </div><!-- /.col-md-4 -->
                    <div class="col-md-4 col-sm-4">
                        <div class="feature-box equal-height">
                            <figure class="icon"><i class="fa fa-users"></i></figure>
                            <aside class="description">
                                <header><h3>The 8</h3></header>
                                <p>The wonderfull team of 8 is here to help you at anytime</p>
                                <a href="#" class="link-arrow">Read More</a>
                            </aside>
                        </div><!-- /.feature-box -->
                    </div><!-- /.col-md-4 -->
                    <div class="col-md-4 col-sm-4">
                        <div class="feature-box equal-height">
                            <figure class="icon"><i class="fa fa-money"></i></figure>
                            <aside class="description">
                                <header><h3>Best Price Guarantee!</h3></header>
                                <p>Find the nearest and the businesses near to you</p>
                                <a href="#" class="link-arrow">Read More</a>
                            </aside>
                        </div><!-- /.feature-box -->
                    </div><!-- /.col-md-4 -->
                </div><!-- /.row -->
            </div><!-- /.container -->
        </section><!-- /#our-services -->
    </div>
    <!-- end Page Content -->
    <!-- Page Footer -->
    <footer id="page-footer">
        <div class="inner">
            <aside id="footer-main">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3 col-sm-3">
                            <article>
                                <h3>About Us</h3>
                                <p>Contam.gr is a   platform which  provides detailed,
                                geolocated information for all  kinds of businesses  in Greece. 
                                It has become unquestionably the No1 local search engine in Greece. 
                                Contam.gr' s  intelligent and flexible search engine, 
                                provides helpful local information to ready to 
                                buy customers and is definitely a necessary vehicle for anyone.
                                </p>
                                <hr>
                                <a href="#" class="link-arrow">Read More</a>
                            </article>
                        </div><!-- /.col-sm-3 -->
                        <div class="col-md-3 col-sm-3">
                         
                        </div><!-- /.col-sm-3 -->
                        <div class="col-md-3 col-sm-3">
                            <article>
                                <h3>Contact</h3>
                                <address>
                                    <strong>Contam.gr</strong><br>
                                    TSIMISKI 43 THESSALONIKI<br>
                                    TK:54248
                                </address>
                                 2310 941 991<br>
                                <a href="#">info@Contam.gr</a>
                            </article>
                        </div><!-- /.col-sm-3 -->
                        <div class="col-md-3 col-sm-3">
                            <article>
                                <h3>Useful Links</h3>
                                <ul class="list-unstyled list-links">
                                    <li><a href="#">Privacy Policy</a></li>
                                    <li><a href="#">FAQ</a></li>
                                    <li><a href="#">Terms and Conditions</a></li>
                                </ul>
                            </article>
                        </div><!-- /.col-sm-3 -->
                    </div><!-- /.row -->
                </div><!-- /.container -->
            </aside><!-- /#footer-main -->
            <aside id="footer-thumbnails" class="footer-thumbnails"></aside><!-- /#footer-thumbnails -->
            <aside id="footer-copyright">
                <div class="container">
                    <span>Copyright © 2016. All Rights Reserved.</span>
                    <span class="pull-right"><a href="#page-top" class="roll">Go to top</a></span>
                </div>
            </aside>
        </div><!-- /.inner -->
    </footer>
    <!-- end Page Footer -->
</div>

<div id="overlay"></div>

<script type="text/javascript" src="assets/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyB1ozxp7wn3dRuCQbaCRJ63_YV8enwMP98"></script>
<script type="text/javascript" src="assets/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/smoothscroll.js"></script>
<script type="text/javascript" src="assets/js/markerwithlabel_packed.js"></script>
<script type="text/javascript" src="assets/js/infobox.js"></script>
<script type="text/javascript" src="assets/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="assets/js/icheck.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.vanillabox-0.1.5.min.js"></script>
<script type="text/javascript" src="assets/js/retina-1.1.0.min.js"></script>
<script type="text/javascript" src="assets/js/jshashtable-2.1_src.js"></script>
<script type="text/javascript" src="assets/js/jquery.numberformatter-1.2.3.js"></script>
<script type="text/javascript" src="assets/js/tmpl.js"></script>
<script type="text/javascript" src="assets/js/jquery.dependClass-0.1.js"></script>
<script type="text/javascript" src="assets/js/draggable-0.1.js"></script>
<script type="text/javascript" src="assets/js/jquery.slider.js"></script>
<script type="text/javascript" src="assets/js/markerclusterer_packed.js"></script>
<script>
    var locations = [
    <c:choose>
        <c:when test="${results.size() > 0}">
            <c:forEach items="${results}" var="result">
                ["${result.address}", "${result.description}", "${result.name}", ${result.latitude}, ${result.longitude}, "property-detail.html", "assets/img/properties/property-01.jpg", "assets/img/property-types/apartment.png"],
            </c:forEach>
        </c:when>
    </c:choose>
    ];
</script>
<script type="text/javascript" src="assets/js/custom-map.js"></script>
<script type="text/javascript" src="assets/js/custom.js"></script>
<!--[if gt IE 8]>
<script type="text/javascript" src="assets/js/ie.js"></script>
<![endif]-->
<script>
    _latitude = ${latitude};
    _longitude = ${longitude};
    
    function geoFindMe() {
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(success, error, geoOptions);
        } else {
            console.log("Geolocation services are not supported by your web browser.");
        }
    }

    function success(position) {
        _latitude = position.coords.latitude;
        _longitude = position.coords.longitude;
        $("#latitude-field").val(_latitude);
        $("#longitude-field").val(_longitude);
        initMap();
        console.log("lat: " + _latitude + " long: " + _longitude);
    }

    function error(error) {
        initMap();
        console.log("Unable to retrieve your location due to " + error.code + ": " + error.message);
    }

    var geoOptions = {
        enableHighAccuracy: true,
        maximumAge: 30000,
        timeout: 27000
    };
    
    function initMap() {
        createHomepageGoogleMap(_latitude,_longitude);
        $(window).load(function(){
            initializeOwl(false);
        });
    }
    
    geoFindMe();
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 숫자 표시에 콤마 찍기위한 포맷 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Male-Fashion | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__option">
            <div class="offcanvas__links">
                <a href="#">Sign in</a>
                <a href="#">FAQs</a>
            </div>
            <div class="offcanvas__top__hover">
                <span>Usd <i class="arrow_carrot-down"></i></span>
                <ul>
                    <li>USD</li>
                    <li>EUR</li>
                    <li>USD</li>
                </ul>
            </div>
        </div>
        <div class="offcanvas__nav__option">
            <a href="#" class="search-switch"><img src="${pageContext.request.contextPath}/resources/img/icon/search.png" alt=""></a>
            <a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/heart.png" alt=""></a>
            <a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/cart.png" alt=""> <span>0</span></a>
            <div class="price">$0.00</div>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__text">
            <p>Free shipping, 30-day return or refund guarantee.</p>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-7">
                        <div class="header__top__left">
                            <p>Free shipping, 30-day return or refund guarantee.</p>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-5">
                        <div class="header__top__right">
                            <div class="header__top__links">
                                <a href="#">Sign in</a>
                                <a href="#">FAQs</a>
                            </div>
                            <div class="header__top__hover">
                                <span>Usd <i class="arrow_carrot-down"></i></span>
                                <ul>
                                    <li>USD</li>
                                    <li>EUR</li>
                                    <li>USD</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="header__logo">
                        <a href="./inex.html"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li><a href="./inex.html">Home</a></li>
                            <li class="active"><a href="./shop.html">Shop</a></li>
                            <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="./about.html">About Us</a></li>
                                    <li><a href="./shop-details.html">Shop Details</a></li>
                                    <li><a href="./shopping-cart.html">Shopping Cart</a></li>
                                    <li><a href="./checkout.html">Check Out</a></li>
                                    <li><a href="./blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <li><a href="./blog.html">Blog</a></li>
                            <li><a href="./contact.html">Contacts</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3 col-md-3">
                    <div class="header__nav__option">
                        <a href="#" class="search-switch"><img src="${pageContext.request.contextPath}/resources/img/icon/search.png" alt=""></a>
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/heart.png" alt=""></a>
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/cart.png" alt=""> <span>0</span></a>
                        <div class="price">$0.00</div>
                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>
    </header>
    <!-- Header Section End -->

    <!-- Shop Details Section Begin -->
	<c:forEach var="m" items="${list}" varStatus="s">
	    <section class="shop-details">
	        <div class="product__details__pic">
	            <div class="container">
	                <div class="row">
	                    <div class="col-lg-12">
	                        <div class="product__details__breadcrumb">
	                            <a href="./index.html">Home</a>
	                            <a href="./shop.html">Shop</a>
	                            <span>Product Details</span>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-lg-12 col-md-9">
	                        <div class="tab-content">
	                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
	                                <div class="product__details__pic__item">
	                                    <img src="${pageContext.request.contextPath}/upload/${m.filename}" alt="">
	                                </div>
	                            </div>                          
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="product__details__content">
	            <div class="container">
	                <div class="row d-flex justify-content-center">
	                    <div class="col-lg-8">
	                        <div class="product__details__text">
	                            <h4>${m.goodsName}</h4>
	                            <div class="rating">
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star-o"></i>
	                                <span> - 5 Reviews</span>
	                            </div>
	                            <h3><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</h3>
	                            <p>Coat with quilted lining and an adjustable hood. Featuring long sleeves with adjustable
	                                cuff tabs, adjustable asymmetric hem with elastic side tabs and a front zip fastening
	                            with placket.</p>

	                            <div class="product__details__cart__option">	                 
	                                <!-- 상품 품절상태일 경우 메세지 출력 -->
									<c:if test="${m.soldout eq 'Y'}">
										<span style=color:red;><strong>죄송합니다! 상품이 품절되었습니다.</strong></span>
									</c:if>
	                                <c:if test="${m.soldout eq 'N'}">
	                                <div class="quantity">
	                                    <div class="pro-qty">
	                                        <input type="text" value="1">
	                                    </div>
	                                </div>
	                                <!-- 장바구니(비회원, 회원) 분기 -->
	                                <div class="product__details__btns__option">
	                                <c:choose>
										<c:when test="${loginCustomer == null }">
											<a href="${pageContext.request.contextPath }/cart/nonMemberCartList?action=addCart&goodsCode=${m.goodsCode}"><i class="fa fa-heart"></i> Add To Cart</a>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath }/cart/customerCartList?action=addCart&goodsCode=${m.goodsCode}"><i class="fa fa-heart"></i> Add To Cart</a>
										</c:otherwise>
									</c:choose>	                                         	
	                            	</div>										
										<a href="${pageContext.request.contextPath}/order/addOrder?goodsCode=${m.goodsCode}" class="primary-btn">Buy Now</a>		
									</c:if>
	                            </div>	                            
	                            <div class="product__details__last__option">
	                                <h5><span>Guaranteed Safe Checkout</span></h5>
	                                <img src="${pageContext.request.contextPath}/resources/img/shop-details/details-payment.png" alt="">
	                                <ul>
	                                    <li><span>SKU:</span> 3812912</li>
	                                    <li><span>Categories:</span> Clothes</li>
	                                    <li><span>Tag:</span> Clothes, Skin, Body</li>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col-lg-12">
	                        <div class="product__details__tab">
	                            <ul class="nav nav-tabs" role="tablist">
	                                <li class="nav-item">
	                                    <a class="nav-link active" data-toggle="tab" href="#tabs-5"
	                                    role="tab">Description</a>
	                                </li>
	                                <li class="nav-item">
	                                    <a class="nav-link" data-toggle="tab" href="#tabs-6" role="tab">Customer
	                                    Previews(5)</a>
	                                </li>
	                                <li class="nav-item">
	                                    <a class="nav-link" data-toggle="tab" href="#tabs-7" role="tab">Additional
	                                    information</a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tabs-5" role="tabpanel">
	                                    <div class="product__details__tab__content">
	                                        <p class="note">Nam tempus turpis at metus scelerisque placerat nulla deumantos
	                                            solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis
	                                            ut risus. Sedcus faucibus an sullamcorper mattis drostique des commodo
	                                        pharetras loremos.</p>
	                                        <div class="product__details__tab__content__item">
	                                            <h5>Products Infomation</h5>
	                                            <p>A Pocket PC is a handheld computer, which features many of the same
	                                                capabilities as a modern PC. These handy little devices allow
	                                                individuals to retrieve and store e-mail messages, create a contact
	                                                file, coordinate appointments, surf the internet, exchange text messages
	                                                and more. Every product that is labeled as a Pocket PC must be
	                                                accompanied with specific software to operate the unit and must feature
	                                            a touchscreen and touchpad.</p>
	                                            <p>As is the case with any new technology product, the cost of a Pocket PC
	                                                was substantial during it’s early release. For approximately $700.00,
	                                                consumers could purchase one of top-of-the-line Pocket PCs in 2003.
	                                                These days, customers are finding that prices have become much more
	                                                reasonable now that the newness is wearing off. For approximately
	                                            $350.00, a new Pocket PC can now be purchased.</p>
	                                        </div>
	                                        <div class="product__details__tab__content__item">
	                                            <h5>Material used</h5>
	                                            <p>Polyester is deemed lower quality due to its none natural quality’s. Made
	                                                from synthetic materials, not natural like wool. Polyester suits become
	                                                creased easily and are known for not being breathable. Polyester suits
	                                                tend to have a shine to them compared to wool and cotton suits, this can
	                                                make the suit look cheap. The texture of velvet is luxurious and
	                                                breathable. Velvet is a great choice for dinner party jacket and can be
	                                            worn all year round.</p>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="tab-pane" id="tabs-6" role="tabpanel">
	                                    <div class="product__details__tab__content">
	                                        <div class="product__details__tab__content__item">
	                                            <h5>Products Infomation</h5>
	                                            <p>A Pocket PC is a handheld computer, which features many of the same
	                                                capabilities as a modern PC. These handy little devices allow
	                                                individuals to retrieve and store e-mail messages, create a contact
	                                                file, coordinate appointments, surf the internet, exchange text messages
	                                                and more. Every product that is labeled as a Pocket PC must be
	                                                accompanied with specific software to operate the unit and must feature
	                                            a touchscreen and touchpad.</p>
	                                            <p>As is the case with any new technology product, the cost of a Pocket PC
	                                                was substantial during it’s early release. For approximately $700.00,
	                                                consumers could purchase one of top-of-the-line Pocket PCs in 2003.
	                                                These days, customers are finding that prices have become much more
	                                                reasonable now that the newness is wearing off. For approximately
	                                            $350.00, a new Pocket PC can now be purchased.</p>
	                                        </div>
	                                        <div class="product__details__tab__content__item">
	                                            <h5>Material used</h5>
	                                            <p>Polyester is deemed lower quality due to its none natural quality’s. Made
	                                                from synthetic materials, not natural like wool. Polyester suits become
	                                                creased easily and are known for not being breathable. Polyester suits
	                                                tend to have a shine to them compared to wool and cotton suits, this can
	                                                make the suit look cheap. The texture of velvet is luxurious and
	                                                breathable. Velvet is a great choice for dinner party jacket and can be
	                                            worn all year round.</p>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="tab-pane" id="tabs-7" role="tabpanel">
	                                    <div class="product__details__tab__content">
	                                        <p class="note">Nam tempus turpis at metus scelerisque placerat nulla deumantos
	                                            solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis
	                                            ut risus. Sedcus faucibus an sullamcorper mattis drostique des commodo
	                                        pharetras loremos.</p>
	                                        <div class="product__details__tab__content__item">
	                                            <h5>Products Infomation</h5>
	                                            <p>A Pocket PC is a handheld computer, which features many of the same
	                                                capabilities as a modern PC. These handy little devices allow
	                                                individuals to retrieve and store e-mail messages, create a contact
	                                                file, coordinate appointments, surf the internet, exchange text messages
	                                                and more. Every product that is labeled as a Pocket PC must be
	                                                accompanied with specific software to operate the unit and must feature
	                                            a touchscreen and touchpad.</p>
	                                            <p>As is the case with any new technology product, the cost of a Pocket PC
	                                                was substantial during it’s early release. For approximately $700.00,
	                                                consumers could purchase one of top-of-the-line Pocket PCs in 2003.
	                                                These days, customers are finding that prices have become much more
	                                                reasonable now that the newness is wearing off. For approximately
	                                            $350.00, a new Pocket PC can now be purchased.</p>
	                                        </div>
	                                        <div class="product__details__tab__content__item">
	                                            <h5>Material used</h5>
	                                            <p>Polyester is deemed lower quality due to its none natural quality’s. Made
	                                                from synthetic materials, not natural like wool. Polyester suits become
	                                                creased easily and are known for not being breathable. Polyester suits
	                                                tend to have a shine to them compared to wool and cotton suits, this can
	                                                make the suit look cheap. The texture of velvet is luxurious and
	                                                breathable. Velvet is a great choice for dinner party jacket and can be
	                                            worn all year round.</p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
    </c:forEach>
    <!-- Shop Details Section End -->

    <!-- Related Section Begin -->
    <section class="related spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="related-title">Hot Product</h3>
                </div>
            </div>
            <div class="row">
            	<c:forEach var="m" items="${topList}">
	                <div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="${pageContext.request.contextPath}/upload/${m.filename}">
	                            <span class="label">New</span>
	                            <ul class="product__hover">
	                                <li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/heart.png" alt=""></a></li>
	                                <li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/compare.png" alt=""> <span>Compare</span></a></li>
	                                <li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/icon/search.png" alt=""></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6>${m.goodsName}</h6>
	                            <a href="#" class="add-cart">+ Add To Cart</a>
	                            <div class="rating">
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                            </div>
	                            <h5><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</h5>
	                            <div class="product__color__select">
	                                <label for="pc-1">
	                                    <input type="radio" id="pc-1">
	                                </label>
	                                <label class="active black" for="pc-2">
	                                    <input type="radio" id="pc-2">
	                                </label>
	                                <label class="grey" for="pc-3">
	                                    <input type="radio" id="pc-3">
	                                </label>
	                            </div>
	                        </div>
	                    </div>
	                </div>                               
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Related Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__logo">
                            <a href="#"><img src="${pageContext.request.contextPath}/resources/img/footer-logo.png" alt=""></a>
                        </div>
                        <p>The customer is at the heart of our unique business model, which includes design.</p>
                        <a href="#"><img src="${pageContext.request.contextPath}/resources/img/payment.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
                    <div class="footer__widget">
                        <h6>Shopping</h6>
                        <ul>
                            <li><a href="#">Clothing Store</a></li>
                            <li><a href="#">Trending Shoes</a></li>
                            <li><a href="#">Accessories</a></li>
                            <li><a href="#">Sale</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2 col-md-3 col-sm-6">
                    <div class="footer__widget">
                        <h6>Shopping</h6>
                        <ul>
                            <li><a href="#">Contact Us</a></li>
                            <li><a href="#">Payment Methods</a></li>
                            <li><a href="#">Delivary</a></li>
                            <li><a href="#">Return & Exchanges</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
                    <div class="footer__widget">
                        <h6>NewLetter</h6>
                        <div class="footer__newslatter">
                            <p>Be the first to know about new arrivals, look books, sales & promos!</p>
                            <form action="#">
                                <input type="text" placeholder="Your email">
                                <button type="submit"><span class="icon_mail_alt"></span></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="footer__copyright__text">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        <p>Copyright ©
                            <script>
                                document.write(new Date().getFullYear());
                            </script>2020
                            All rights reserved | This template is made with <i class="fa fa-heart-o"
                            aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        </p>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>

</html>
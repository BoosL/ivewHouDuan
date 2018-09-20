<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String uesPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/TestWeb";
String uesPath = "http://139.159.142.120:80/TestWeb";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=750, target-densitydpi=device-dpi, user-scalable=no">
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/TestWeb/js/jquery-3.1.1.min.js "></script>
<title></title>
<style>
* {
	border: 0;
	padding: 0;
	margin: 0;
}

li {
	list-style: none;
}

body {
	margin: 0;
	padding: 0;
	width: 100%;
}

div {
	margin-left: 2%;
	width: 96%;
	margin-top: 20px;
	background-color: #ccc;
	margin-bottom: 20px;
}

.imgBox img {
	width: 100%;
}

.radioBox video {
	width: 100%;
}

video[ishivideo="true"]::-webkit-media-controls {
	display: none !important;
}

video[ishivideo="true"]::-moz-media-controls {
	display: none !important;
}

video[ishivideo="true"]::-o-media-controls {
	display: none !important;
}

video[ishivideo="true"]::media-controls {
	display: none !important;
}

video::-webkit-media-controls {
	display: none !important;
}

.radioBox {
	position: relative;
}

.radioBox .startP {
	width: 20px;
	height: 20px;
	border-radius: 50%;
	background: #fff;
	position: absolute;
	left: 50%;
	margin-left: -10px;
	top: 50%;
	margin-top: -10px;
}

/*图片轮播*/
#slider {
	width: 100%;
	margin: 0 auto;
}

.pagination li {
	background-color: #ddd;
}

.pagination li.active {
	background-color: #000;
}

.responsive {
	width: 100%;
	height: auto;
}

.clearfix:after {
	content: "";
	display: table;
	clear: both;
}

#slider img {
	display: block;
}

.textBox p {
	width: 100%;
	font-size: 26px;
	font-family: 微软雅黑;
	background: #fff;
	font-style: normal;
}

.checkBox, .singleBox {
	width: 100%;
}

.checkBox ul, .singleBox ul {
	width: 100%;
	overflow: hidden;
}

.checkBox ul li, .singleBox ul li {
	width: 25%;
	float: left;
	margin-bottom: 30px;
}

.checkBox img, .singleBox img {
	width: 100%;
	display: block;
}

.checkBox label, .singleBox label {
	width: 100%;
	display: block;
	word-break: break-all;
}

.checkBox input[type='checkbox'] {
	width: 38px;
	height: 38px;
}

.singleBox input[type='radio'] {
	width: 38px;
	height: 38px;
}

.requiredBox, .unrequiredBox {
	min-height: 100px;
}

.requiredBox input, .unrequiredBox input {
	width: 70%;
	height: 80px;
	margin-left: 5%;
}

.requiredBox span {
	color: red;
}

.requiredBox .text, .unrequiredBox .text {
	width: 30%;
	height: 80px;
	margin-top: 10px;
}

.requiredBox .required, .unrequiredBox .unrequired {
	width: 70%;
	height: 80px;
	margin-top: 10px;
}

.requiredBox>div, .unrequiredBox>div {
	float: left;
	margin: 0;
}

.wrap {
	position: relative;
}
</style>
</head>

<body>
	<input type="hidden" id="luodiyeId" value="${mapInfo.luodiyeid }" >
	<input type="hidden" id="projectId" value="${mapInfo.pid }" >
	<input type="hidden" id="linkId" value="${mapInfo.ldylinkid }" >
	<input type="hidden" id="linkIName" value="${mapInfo.link }" >
	<input type="hidden" id="channelId" value="${mapInfo.channelid }" >
	<input type="hidden" id="channelname" value="${mapInfo.channelname }">
	
	<div class="wrap">
		<c:forEach items="${items }" var="item">
			<c:choose>
				<c:when test="${item.itemclass eq '图片' }">
					<div onfocus="console.log(1)" class="imgBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<img src="<%=uesPath %>${item.picurl }" />
					</div>
				</c:when>
				<c:when test="${item.itemclass eq '视频' }">
					<div onfocus="console.log(2)" class="radioBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<video muted controls="controls"
							src="<%=uesPath %>${item.videourl }"
							autoplay="true">
						</video>
						<span class="startP"></span>
					</div>
				</c:when>
				<c:when test="${item.itemclass eq '轮播图' }">
					<div onfocus="console.log(3)" class="imgScrollBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<div id="slider">
							<ul class="slides">
								<c:if test="${not empty item.picurls }">
									<c:forEach items="${item.listUrl }" var="url" >
										<li>
											<img class="responsive" src="<%=uesPath %>${url }">
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<ul class="pagination">
								<c:if test="${not empty item.picurls }">
									<c:forEach items="${item.listUrl }" var="url">
										<c:choose>
											<c:when test="${url_index == 0}">
												<li class="active"></li>
											</c:when>
											<c:otherwise>
												<li></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>	
				</c:when>
				<c:when test="${item.itemclass eq '文本' }">
					<div onfocus="console.log(5)" class="textBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<p>${item.ltext }</p>
					</div>
				</c:when>
				<c:when test="${item.itemclass eq '表单多选' }">
					<div onfocus="console.log(4)" class="checkBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<p>${item.ltitle }
						<p>
						<ul>
							<c:forEach items="${item.tagList }" var="tag">
								<li>
									<img src="<%=uesPath %>${tag.imgUrl }" alt=""> 
									<label for="1"> 
										<input type="checkbox" name="1" value="1"  /> ${tag.text }
									</label>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:when>
				<c:when test="${item.itemclass eq '表单单选' }">
					<div class="singleBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<p>${item.ltitle }
						<p>
						<ul>
							<c:forEach items="${item.tagList }" var="tag">
								<li>
								<img src="<%=uesPath %>${tag.imgUrl }" alt=""> 
									<label for="r2"> <input type="radio" name="1" value="2"  /> ${tag.text }
								</label>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:when>
				<c:when test="${item.itemclass eq '表单框' }">
					<c:if test="${item.ismust eq '是' }">
						<div class="requiredBox" id="${item.ldyitemid }" name="${item.ltitle }">
							<div class="text"></div>
							<div class="required">
								<span>*</span><input type="text">
							</div>
						</div>
					</c:if>
					<c:if test="${item.ismust eq '否' }">
						<div class="unrequiredBox" id="${item.ldyitemid }" name="${item.ltitle }">
							<div class="text"></div>
							<div class="unrequired">
								<input type="text">
							</div>
						</div>
					</c:if>
				</c:when>
				<c:otherwise>
					<div class="linkageBox" id="${item.ldyitemid }" name="${item.ltitle }">
						<select id="s1" class="select-css"></select> <select id="s2" class="select-css"></select> <select id="s3" class="select-css"></select>
						<!--<select id="s4" class="select-css"></select>-->
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- <div onfocus="console.log(1)" class="imgBox" id="1">
			<img src="11.jpg" />
		</div>
		<div onfocus="console.log(2)" class="radioBox" id="2">
			<video muted controls="controls"
				src="https://media.w3.org/2010/05/sintel/trailer.mp4"
				autoplay="true">
			</video>
			<span class="startP"></span>
		</div>
		<div onfocus="console.log(3)" class="imgScrollBox" id="3">
			<div id="slider">
				<ul class="slides">
					<li><img class="responsive" src="1.png"></li>
					<li><img class="responsive" src="2.png"></li>
					<li><img class="responsive" src="3.png"></li>
					<li><img class="responsive" src="4.png"></li>
				</ul>
				<ul class="pagination">
					<li class="active"></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
		<div onfocus="console.log(5)" class="textBox" id="4">
			<p>你的身份楼上的哈佛说的话佛山动画公司的你的身份楼上的哈佛说的话佛山动画公司的</p>
		</div>
		<div onfocus="console.log(4)" class="checkBox" id="5">
			<p>xxxxxxxxxxxxxx
			<p>
			<ul>
				<li><img src="1.png" alt=""> <label for="1"> <input
						type="checkbox" name="1" value="1" id="1" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="2"> <input
						type="checkbox" name="1" value="2" id="2" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="3"> <input
						type="checkbox" name="1" value="2" id="3" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="4"> <input
						type="checkbox" name="1" value="2" id="4" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="5"> <input
						type="checkbox" name="1" value="2" id="5" /> I have a bike
				</label></li>
			</ul>
		</div>
		<div class="singleBox" id="6">
			<p>xxxxxxxxxxxxxx
			<p>
			<ul>
				<li><img src="1.png" alt=""> <label for="r1"> <input
						type="radio" name="1" value="1" id="r1" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="r2"> <input
						type="radio" name="1" value="2" id="r2" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="r3"> <input
						type="radio" name="1" value="2" id="r3" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="r4"> <input
						type="radio" name="1" value="2" id="r4" /> I have a bike
				</label></li>
				<li><img src="1.png" alt=""> <label for="r5"> <input
						type="radio" name="1" value="2" id="r5" /> I have a bike
				</label></li>
			</ul>
		</div>
		<div class="requiredBox" id="7">
			<div class="text"></div>
			<div class="required">
				<span>*</span><input type="text">
			</div>
		</div>
		<div class="unrequiredBox" id="8">
			<div class="text"></div>
			<div class="unrequired">
				<input type="text">
			</div>
		</div>
		<div class="linkageBox" id="9">
			<select id="s1" class="select-css"></select> <select id="s2"
				class="select-css"></select> <select id="s3" class="select-css"></select>
			<select id="s4" class="select-css"></select>
		</div> -->
	</div>
	<script src="/TestWeb/js/location_data.js"></script>
	<script type="text/javascript">
			$(function(){
				location_data = location_data[1].provinces;
				var getData = function(selectId) {
					var pre = $("#"+selectId).prevAll();
					var tempdata = location_data;
					for (var i = pre.length - 1; i >= 0; i--) {
						var selected = $("#"+pre[i].id).val();
						if (selected == null || selected == -1) {
							return null;
						}
						tempdata = tempdata[selected][childern[pre.length-i-1]];
					}
					return tempdata;
				}
			
				var getModel = function (data) {
					var model = "";
					if (data) {
						for (var i = 0; i < data.length; i++) {
							model += "<option value='"+ i +"'>"+ data[i].name +"</option>"
						}
					}
					return model;
				}

				for(var i=0;i<$(".linkageBox").length;i++){
					$($(".linkageBox")[i]).find("#s1").append(getModel(getData("s1")));
					$($(".linkageBox")[i]).find("#s2").append(getModel(getData("s2")));
					$($(".linkageBox")[i]).find("#s3").append(getModel(getData("s3")));
					$($(".linkageBox")[i]).find("#s4").append(getModel(getData("s4")));
					$($(".linkageBox")[i]).find(".select-css").change(function(){
						$(this).nextAll().empty();
						var index = $(".select-css").index(this);
						if ($(this).nextAll().length > 0) {
						for(var i = 0; i < $(this).nextAll().length; i++) {
								var $next = $(this).nextAll()[i];
								$($next).append(getModel(getData($next.id)));
							}
						}
					})
				}

				
				
			})
				
		</script>
	<script type="text/javascript">
			;
			(function($, window, document, undefined) {

				"use strict";
				var pluginName = "easySlider",
					defaults = {
						slideSpeed: 500,
						autoSlide: true,
						paginationSpacing: "15px",
						paginationDiameter: "12px",
						paginationPositionFromBottom: "0px",
						controlsClass: ".controls",
						slidesClass: ".slides",
						paginationClass: ".pagination"
					};

				// The actual plugin constructor
				function Plugin(element, options) {
					this.element = element;
					this.settings = $.extend({}, defaults, options);
					this._default = defaults;
					this._name = pluginName;
					this.init();
				}

				// Avoid Plugin.prototype conflicts
				$.extend(Plugin.prototype, {
					init: function() {
						this.setProperties();
						this.positionPagination();
						this.slideParameters.setCurrentSlideNumber.call(this, 1);

						if(this.settings.autoSlide === true) {
							this.slideParameters.setAutoStatus.call(this, true);
							this.animations.autoSlide.call(this);
						} else {
							this.slideParameters.setAutoStatus(this, false);
						}

						this.events.clickRight.call(this);
						this.events.clickLeft.call(this);
						this.events.clickPaginate.call(this);
					},
					convertStringToInteger: function(string) {
						return parseInt(string);
					},
					setProperties: function() {
						$("#slider").css({
							"position": "relative",
							"overflow": "hidden"
						});
						$(this.settings.slidesClass).css({
							"position": "relative",
							"width": this.slideParameters.getMaxSlidePercentage.call(this).toString() + "%"
						});
						$(this.settings.controlsClass).css({
							"cursor": "pointer"
						});
						$(this.settings.controlsClass + " " + "li").css({
							"position": "absolute"
						});
						$(this.settings.slidesClass + " " + "li").css({
							"width": 100 / this.slideParameters.getNumberOfSlides.call(this).toString() + "%",
							"float": "left"
						});
						$(this.settings.paginationClass).css({
							"position": "absolute",
							"left": "50%",
							"bottom": this.settings.paginationPositionFromBottom,
							"margin": 0
						});
						$(this.settings.paginationClass + " " + "li").css({
							"margin-right": this.settings.paginationSpacing,
							"float": "left",
							"cursor": "pointer",
							"width": this.settings.paginationDiameter,
							"height": this.settings.paginationDiameter,
							"border-radius": "9999px"
						});
					},
					positionPagination: function() {
						var numberOfSlides = this.slideParameters.getNumberOfSlides.call(this);
						var marginLeft = -(numberOfSlides * (this.convertStringToInteger(this.settings.paginationDiameter)) + (numberOfSlides - 1) * (this.convertStringToInteger(this.settings.paginationSpacing))) / 2;

						$(this.settings.paginationClass).css("margin-left", marginLeft);
					},
					slideParameters: {
						setCurrentSlideNumber: function(currentSlideNumber) {
							this.currentSlideNumber = currentSlideNumber;
						},
						getCurrentSlideNumber: function() {
							return this.currentSlideNumber;
						},
						setAutoStatus: function(autoStatus) {
							console.log(this)
							console.log(autoStatus);
							this.autoStatus = autoStatus;
						},
						getAutoStatus: function() {
							console.log(this.autoStatus);
							return this.autoStatus;
						},
						getNumberOfSlides: function() {
							return $(this.settings.slidesClass).children().length;
						},
						getSlideWidth: function() {
							return $(this.settings.slidesClass + " " + "li").width();
						},
						getSlideHeight: function() {
							// console.log($(this.settings.slidesClass + " " + "li").height());
							return $(this.settings.slidesClass + " " + "li").height();
						},
						getSliderHeight: function() {
							// console.log($(this.element).height());
							return $(this.element).height();
						},
						getMaxSlidePercentage: function() {
							return this.slideParameters.getNumberOfSlides.call(this) * 100;
						}
					},
					events: {
						clickRight: function() {
							var _this = this;

							$(this.settings.controlsClass + " " + "li:last-child").click(
								function() {
									_this.animations.slideRight.call(_this, 1);
									_this.slideParameters.setAutoStatus.call(_this, false);
								}
							);
						},
						clickLeft: function() {
							var _this = this;

							$(this.settings.controlsClass + " " + "li:first-child").click(
								function() {
									_this.animations.slideLeft.call(_this, 1);
									_this.slideParameters.setAutoStatus.call(_this, false);
								}
							);
						},
						clickPaginate: function() {
							var _this = this;

							$(this.settings.paginationClass + " " + "li").click(
								function() {

									var slideDistance = Math.abs(_this.slideParameters.getCurrentSlideNumber.call(_this) - ($(this).index() + 1));
									_this.slideParameters.setAutoStatus.call(_this, false);

									//slide right or left depending on pagination element clicked with respect to current slide
									if(_this.slideParameters.getCurrentSlideNumber.call(_this) <= $(this).index()) {
										_this.animations.slideRight.call(_this, slideDistance);
									} else {
										_this.animations.slideLeft.call(_this, slideDistance);
									}
								}
							);
						}
					},
					animations: {
						slideRight: function(slideDistance) {
							if(this.slideParameters.getCurrentSlideNumber.call(this) === this.slideParameters.getNumberOfSlides.call(this)) {
								//go to first slide, when slideshow has reached max distance
								$(this.settings.slidesClass).animate({
									right: "0%"
								}, this.settings.slideSpeed);
								this.slideParameters.setCurrentSlideNumber.call(this, 1);
							} else {
								//go to next slide
								$(this.settings.slidesClass).animate({
									right: "+=" + slideDistance * 100 + "%"
								}, this.settings.slideSpeed);
								this.slideParameters.setCurrentSlideNumber.call(this, this.slideParameters.getCurrentSlideNumber.call(this) + slideDistance);
							}
							this.animations.activatePagination.call(this, this.slideParameters.getCurrentSlideNumber.call(this));
						},
						slideLeft: function(slideDistance) {
							if(this.slideParameters.getCurrentSlideNumber.call(this) === 1) {
								//go to last slide, when slideshow has reached first slide
								$(this.settings.slidesClass).animate({
									right: (this.slideParameters.getMaxSlidePercentage.call(this) - 100).toString() + "%"
								}, this.settings.slideSpeed);
								this.slideParameters.setCurrentSlideNumber.call(this, this.slideParameters.getNumberOfSlides.call(this));
							} else {
								//go to next slide
								$(this.settings.slidesClass).animate({
									right: "-=" + slideDistance * 100 + "%"
								}, this.settings.slideSpeed);
								this.slideParameters.setCurrentSlideNumber.call(this, this.slideParameters.getCurrentSlideNumber.call(this) - slideDistance);
							}
							this.animations.activatePagination.call(this, this.slideParameters.getCurrentSlideNumber.call(this));
						},
						activatePagination: function(currentSlideNumber) {
							var i;
							var total = this.slideParameters.getNumberOfSlides.call(this);
							for(i = 1; i <= total; i++) {
								$(this.settings.paginationClass + " " + "li:nth-child" + "(" + i.toString() + ")").removeClass("active");
							}
							$(this.settings.paginationClass + " " + "li:nth-child" + "(" + currentSlideNumber.toString() + ")").addClass("active");
							this.slideParameters.setCurrentSlideNumber.call(this, currentSlideNumber);
						},
						autoSlide: function() {
							var _this = this;

							var stop = setInterval(function() {
								slide();
							}, 1500);

							function slide() {
								if(_this.slideParameters.getAutoStatus.call(_this) === false) {
									console.log("hi");
									clearInterval(stop);
								} else {
									_this.animations.slideRight.call(_this, 1);
								}
							}
						}
					}
				});

				// A really lightweight plugin wrapper around the constructor,
				// preventing against multiple instantiations
				$.fn[pluginName] = function(options) {
					return this.each(function() {
						if(!$.data(this, "plugin_" + pluginName)) {
							$.data(this, "plugin_" +
								pluginName, new Plugin(this, options));
						}
					});
				};

			})(jQuery, window, document);
		</script>
		<script type="text/javascript">
			//$.post("http://172.16.14.137:8080/Poster/adadm/get", {
			//id: 1
			//},
			//function(data, status) {
			////alert("Data: " + data + "\nStatus: " + status);
			//});

			//myDate.getYear(); //获取当前年份(2位)
			//myDate.getFullYear(); //获取完整的年份(4位,1970-????)
			//myDate.getMonth(); //获取当前月份(0-11,0代表1月)
			//myDate.getDate(); //获取当前日(1-31)
			//myDate.getTime(); //获取当前时间(从1970.1.1开始的毫秒数)
			//myDate.toLocaleString(); //获取日期与时间
			//myDate.toLocaleDateString(); //获取当前日期

			// window.onbeforeunload = function() {
			//			setTimeout(function() {
			//				setTimeout(beforeloadResult, 50)
			//			}, 50);
			//			console.log(myDate.toLocaleString());
			//			return '确认离开网页？';

			//};

			// function beforeloadResult() {
			// 	alert("你取消了离开网页！");
			// 	console.log(myDate.toLocaleString());
			// }

			// $('body').bind('touchstart', function(e) {
			// 	startX = e.originalEvent.changedTouches[0].pageX,
			// 		startY = e.originalEvent.changedTouches[0].pageY;
			// });

			// $('body').bind('touchmove', function(e) {
			//获取滑动屏幕时的X,Y
			// endX = e.originalEvent.changedTouches[0].pageX,
			// endY = e.originalEvent.changedTouches[0].pageY;
			//获取滑动距离
			// distanceX = endX - startX;
			// distanceY = endY - startY;
			//判断滑动方向
			//			if(Math.abs(distanceX) > Math.abs(distanceY) && distanceX > 0) {
			//				console.log('往右滑动');
			//			} else if(Math.abs(distanceX) > Math.abs(distanceY) && distanceX < 0) {
			//				console.log('往左滑动');
			//			} else {
			//				console.log('点击未滑动');
			//			}
			// 	if(Math.abs(distanceX) < Math.abs(distanceY) && distanceY < 0) {
			// 		console.log('往上滑动');
			// 	} else if(Math.abs(distanceX) < Math.abs(distanceY) && distanceY > 0) {
			// 		console.log('往下滑动');
			// 	}
			// });
            
			var domScroll = {
				start: function() {
					this.onload();
					this.videoPlay();
					this.imgScroll();
				},
				onload: function() {
					var me = this;
				},
				videoPlay: function() {
					for(var i = 0; i < $("video").length; i++) {
						$($("video")[i]).siblings(".startP").hide();
						//console.log(($($("video")[i]).find(".startP"))[0])

						$("video")[i].play();
						var target = $("video")[i];
						$($("video")[i]).click(function() {
							if($(this).siblings(".startP").css("display") == "none") {
								$(this).siblings(".startP").show();
								target.pause();
							}
						})
						$($("video")[i]).siblings(".startP").click(function() {
							$(this).hide();
							target.play();
						})
						/*if($("video")[i].ended==true){
							$($("video")[i]).siblings(".startP").show();
							target.pause();
						}*/
						$("video")[i].addEventListener("ended", function() {
							console.log($(target).siblings(".startP").css("display"))
							$(target).siblings(".startP").show();
						})
					}
				},
				imgScroll: function() {
					for(var i = 0; i < $(".imgScrollBox").length; i++) {
						$($(".imgScrollBox")[i]).easySlider({})
					}
				}
				

			}
			$(function() {
				domScroll.start();
			})
		</script>
		<script type="text/javascript">
			$(function() {
				var https={
					//baseUrl:"http://172.16.14.137:81",
					//submitUrl:"http://172.16.14.137:8080/Poster/"
					baseUrl:"http://139.159.142.120:80/redis/",
					submitUrl:"http://139.159.142.120:80/Poster/"
				}
				var userTime;
	            var userId;
	            var userIp;
	            var isSubmit="否"
				$(".wrap>div").each(function() {
					$(this).attr("show", "n");
					$(this).attr("hide", "n");
				})

				function _getRandomString(len){
					len = len || 32;
				    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1
				    var maxPos = $chars.length;
				    var pwd = '';
				    for (i = 0; i < len; i++) {
				        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
				    }
				    return pwd;
				}
				//获取用户ip
				function getIp(){
					 userIp=returnCitySN["cip"];
         			//console.log(returnCitySN["cip"]);			        
				}
				window.onload = function() {
					document.documentElement.scrollTop =0;
					document.body.scrollTop=0;
					userTime = new Date();
					// modelInTime=inTime.toLocaleString();
					usertime= (new Date(formatDateTime(userTime)).getTime() / 1000);
					console.log("用户进入时间" + usertime)
					if($.cookie("id")==null){
				     	userId=(new Date(formatDateTime(userTime)).getTime() / 1000)+ _getRandomString(16)
				    }else{
				    	userId=$.cookie("id");
				    }
				    getIp();
				   
				}

				var maxTime = [];
				var lastDomIndex=[];
				var userLastOutTime="";
				//creatArray();
				//scrollSendData();
				init();
				
				//模拟用户关闭事件
				window.onbeforeunload = function() {
					initSendData();
					scrollBottomSendData();
					userLastOutTime = new Date();
					// modelInTime=inTime.toLocaleString();
					userLastOutTimeF= (new Date(formatDateTime(userLastOutTime)).getTime() / 1000);
					stopTime=userLastOutTimeF-usertime;

					closeAjax(sendLastDom(),getMaxTime(),stopTime,isSubmit,userIp);
				}
				$(".submit").click(function(){
					isSubmit="是";
					var addrModel=$(".wrap .linkageBox.form")[0];
					var addrInfoItem={
						province:"",
						city:"",
						district:""
					};
					addrInfoItem.province=$(addrModel).find("#s1").find("option:selected").text();
					addrInfoItem.city=$(addrModel).find("#s2").find("option:selected").text();
					addrInfoItem.district=$(addrModel).find("#s3").find("option:selected").text();
					var formInfoItems=[];
					$(".wrap>div.form").each(function(index, element) {
						if($(this).hasClass("requiredBox")||$(this).hasClass("unrequiredBox")){
							formInfoItem={
								title:"",
								value:""
							}
							formInfoItem.title=$(this).find(".text").text();
							formInfoItem.value=$(this).find("input").val();
							formInfoItems.push(formInfoItem);
						}
					})
					var formDate={
						formInfo:formInfoItems,
						addrInfo:addrInfoItem,
						ip:userIp,
						uniqueid:userId,
						ldylinkid:1
					}
					submitAjax(formDate);
				})
				
				//用户提交事件
				
				//记录页面加载时视觉区域的模块第一次进入的时间
				function init() {
					$(".wrap>div").each(function(index, element) {
						var blockHeight = $(this).outerHeight(true);
						var blockOTop = $(this).offset().top;
						var winScrollTop = $(window).scrollTop();
						var winView = $(window).height();
						var top = $(this)[0].getBoundingClientRect().top //元素顶端到可见区域顶端的距离
						var se = document.documentElement.clientHeight
						if($(this).attr("show") == "n" && (blockHeight + blockOTop <= $(window).scrollTop() + $(window).height())) {
							console.log($(this).attr("id"))
							creatInTime($(this), index)
							$(this).attr("show", "y");
						}
					});
				}

				//当没有滚动条时 ，把关闭时间作为模块第一次移出时间
				function initSendData(){
					$(".wrap>div").each(function(index, element) {
						if($(this).attr("show")=="y"&&$(this).attr("hide")=="n"){
							var firstOutTime = new Date();
							// modelInTime=inTime.toLocaleString();
							var firstoutTime = (new Date(formatDateTime(firstOutTime)).getTime() / 1000);
							var dataInfo={
								luodiyeId : 1,
								modInfo:[]
							}
							var moudleOutTime={
								modId:"",
								time:"",
								leave:1
							}
									
							moudleOutTime.time=firstoutTime-$(this).attr("in");
							moudleOutTime.modId=$(this).attr("id");
							dataInfo.modInfo.push(moudleOutTime);
							var itemName={
								name:"",
								time:""
							};
							itemName.name=$(this).attr("name");
							itemName.time=firstoutTime-$(this).attr("in");
							maxTime.push(itemName);
							sendmoudelsData(dataInfo);
						}
					})
				}

				//当有滚动条时,滚动条到底部，且模块第一次进入但是没有滑出
				function scrollBottomSendData(){
					var arrayBottomData=[];
					$(".wrap>div").each(function(index, element) {
						if($(this).attr("hide")=="n"&&$(this).attr("show")=="y"&&isReachBottom()){
							var bottomOutTime = new Date();
							// modelInTime=inTime.toLocaleString();
							var bottomoutTime = (new Date(formatDateTime( bottomOutTime )).getTime() / 1000);
							var dataInfo={
								luodiyeId : 1,
								modInfo:[]
							}
							var bmoudleOutTime={
								modId:"",
								time:"",
								leave:1
							}
							bmoudleOutTime.time=bottomoutTime-$(this).attr("in");
							bmoudleOutTime.modId=$(this).attr("id");
							dataInfo.modInfo.push(bmoudleOutTime);
							var itemName={
								name:"",
								time:""
							};
							itemName.name=$(this).attr("name");
							itemName.time=bottomoutTime-$(this).attr("in");
							maxTime.push(itemName);
							sendmoudelsData(dataInfo);
						}
					})	
				
				}

				//用户点击关闭事件把可视区域里的最后一个模块传到后台
				
				function  sendLastDom(){
					$(".wrap>div").each(function(index, element) {
						var blockHeight = $(this).outerHeight(true);
						var blockOTop = $(this).offset().top;
						var winScrollTop = $(window).scrollTop();

						var winView = $(window).height();
						var top = $(this)[0].getBoundingClientRect().top //元素顶端到可见区域顶端的距离
						var se = Math.floor(document.documentElement.clientHeight)
						if((blockHeight + top <= se)||(isReachBottom()&&index == $(".wrap>div").length - 1)) {
							lastDomIndex.push(index);

						}    
				    }) 
				    return ($($(".wrap>div")[lastDomIndex[lastDomIndex.length-1]]).attr("name"));  	
				}
				
				//用户点击关闭按钮时，取maxTime数组里的最大值
				function getMaxTime(){
					var Maxtimes=[];
					var MaxN="";
					for(var i=0;i<maxTime.length;i++){
						Maxtimes.push(maxTime[i].time);
					}
					var theMax=Math.max.apply(Math, Maxtimes);
					for(var j=0;j<maxTime.length;j++){
						if(maxTime[j].time==theMax){
							MaxN=maxTime[j].name;
						}
					}
					//console.log(Math.max.apply(Math, maxTime))
					return MaxN;
				}
				
				//监听滚动条滚动事件，当模块进入可视区域，发送id和时间差，和把每个模块第一次进出时间差放进maxTime数组，便于后续求最大值
				$(window).scroll(function() {
					scrollSendData();
				});

			    
				//监听滚动条滚动到底部的兼容写法
				
				function getScrollTop() {
					// 考虑到浏览器版本兼容性问题，解析方式可能会不一样
					return document.documentElement.scrollTop || document.body.scrollTop
				}

				function getWinHeight() {
					// 浏览器可见内容高度 || 浏览器所有内容高度(考虑到浏览器版本兼容性问题，解析方式可能会不一样)
					return document.documentElement.clientHeight || document.body.clientHeight
				}

				function getScrollHeight() {
					let bodyScrollHeight = 0
					let documentScrollHeight = 0
					if(document.body) {
						bodyScrollHeight = document.body.scrollHeight
					}
					if(document.documentElement) {
						documentScrollHeight = document.documentElement.scrollHeight
					}
					// 当页面内容超出浏览器可视窗口大小时，Html的高度包含body高度+margin+padding+border所以html高度可能会大于body高度
					return(bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight
				}

				function isReachBottom() {
					const scrollTop = getScrollTop() // 获取滚动条的高度
					const winHeight = getWinHeight() // 一屏的高度
					const scrollHeight = getScrollHeight() // 获取文档总高度
					return scrollTop >= parseInt(scrollHeight) - winHeight
				}

				function scrollSendData() {
					$(".wrap>div").each(function(index, element) {
						var blockHeight = $(this).outerHeight(true);
						var blockOTop = $(this).offset().top;
						var winScrollTop = $(window).scrollTop();

						var winView = $(window).height();
						var top = $(this)[0].getBoundingClientRect().top //元素顶端到可见区域顶端的距离
						var se = Math.floor(document.documentElement.clientHeight)

						
						if(index == $(".wrap>div").length - 1 && isReachBottom()&&($(this).attr("hide") == "n" && top > 0 && top > se && ($(this).attr("show") == "y"))) {
							creatOutTime($(this), index);
							var lastFirstTime = parseInt($(this).attr("out")) - parseInt($(this).attr("in"));
							var itemName={
								name:"",
								time:""
							};
							itemName.name=$(this).attr("name");
							itemName.time=lastFirstTime;
							maxTime.push(itemName);
							// console.info(index+":"+firstTime)
							console.log(maxTime)
							
							sendmoudelData($(this).attr("id"),lastFirstTime);

							$(this).attr("hide", "y");
						}
						

						if($(this).attr("show") == "n" && (blockHeight + top <= se)) {
							console.log($(this).attr("id"))
							creatInTime($(this), index)
							$(this).attr("show", "y");

						}

						if(($(this).attr("hide") == "n" && top < 0 && (top <= (-blockHeight))) || ($(this).attr("hide") == "n" && top > 0 && top > se && ($(this).attr("show") == "y"))) {
							creatOutTime($(this), index);
							var firstTime = parseInt($(this).attr("out")) - parseInt($(this).attr("in"));	
							var itemName={
								name:"",
								time:""
							};
							itemName.name=$(this).attr("name");
							itemName.time=firstTime;
							maxTime.push(itemName);
							// console.info(index+":"+firstTime)
							console.log(maxTime)

							sendmoudelData($(this).attr("id"),firstTime);
							$(this).attr("hide", "y");
						}
					});
				}

				function sendmoudelData(id,time){
						var dataInfo={
							luodiyeId : 1
						}

						var sendData=[];
							var dataItem={
								modId:0,
								time:""	,
								leave:1
							};
							dataItem.modId=id;
							dataItem.time=time;
							sendData.push(dataItem);

						dataInfo.modInfo = sendData;	
						$.ajax({
						    type: "post",
						    url:https.baseUrl+"/setRedisData/setModInfo",
						    data:JSON.stringify(dataInfo),
						    contentType: "application/json;charset=utf-8",
						    dataType: "json",
						    success: function (data) {
						        
						    },
						    error: function (e) {
						       
						    }
						});	
				}

				function sendmoudelsData(dataObject){
					$.ajax({
					    type: "post",
					    url:https.baseUrl+"/setRedisData/setModInfo",
					    data:JSON.stringify(dataObject),
					    contentType: "application/json;charset=utf-8",
					    dataType: "json",
					    success: function (data) {
					        
					    },
					    error: function (e) {
					       
					    }
					});	
				}
				function creatInTime(objet, index) {
					var inTime = new Date();
					// modelInTime=inTime.toLocaleString();
					var arrayIn = (new Date(formatDateTime(inTime)).getTime() / 1000);
					//console.log(modelInTime)
					objet.attr("in", arrayIn)
					//console.log(index+":"+objet.data("in"))
					//console.log(objet.data("in"))
					//objet.attr("in",array)
				}

				function creatOutTime(objet, index) {
					var outTime = new Date();
					modelOutTime = (new Date(formatDateTime(outTime)).getTime() / 1000);
					//var array=new Array();
					objet.attr("out", modelOutTime);
					// if(objet.attr("out")){
					// 	var firstTime=parseInt(objet.attr("out"))-parseInt(objet.attr("in"));
					// console.info(index+":"+firstTime)
					// }

					// objet.attr("out",array)
				}

				function formatDateTime(theDate) {
					var _hour = theDate.getHours();
					var _minute = theDate.getMinutes();
					var _second = theDate.getSeconds();
					var _year = theDate.getFullYear()
					var _month = theDate.getMonth();
					var _date = theDate.getDate();
					if(_hour < 10) {
						_hour = "0" + _hour
					}
					if(_minute < 10) {
						_minute = "0" + _minute
					}
					if(_second < 10) {
						_second = "0" + _second
					}
					_month = _month + 1;
					if(_month < 10) {
						_month = "0" + _month
					}
					if(_date < 10) {
						_date = "0" + _date
					}
					return _year + "-" + _month + "-" + _date + " " + _hour + ":" + _minute + ":" + _second;
				}

                function closeAjax(jumpAddr,stopAddr,stopTime,isSub,ip){
                	dataObject={
                		userInfo:{
                			id:userId,
                			linkName:$("#linkIName").val(),
                			className:$("#channelname").val(),
                			comeDate:"",
                			jumpAddr:jumpAddr,
                			stopAddr:stopAddr,
                			stopTime:stopTime,
                			isSub:isSub
                		},
                		
                		pId:$("#projectId").val()*1,
            			linkId:$("#linkId").val()*1,
            			channelId:$("#channelId").val()*1,
            			ip:ip,
            			luodiyeId:$("#luodiyeid").val()*1
                	}
                	$.ajax({
					    type: "post",
					    url:https.baseUrl+"/setRedisData/setCountData",
					    data:JSON.stringify(dataObject),
					    contentType: "application/json;charset=utf-8",
					    dataType: "json",
					    success: function (data) {
					        
					    },
					    error: function (e) {
					       
					    }
					});	
                }

                function submitAjax(data){
                	$.ajax({
					    type: "post",
					    url:https.submitUrl+"ldysubmit/add",
					    data:JSON.stringify(data),
					    contentType: "application/json;charset=utf-8",
					    dataType: "json",
					    success: function (data) {
					        
					    },
					    error: function (e) {
					       
					    }
					});	
                	
                }
				 
				 //    pushHistory();  var lastDomIndex=[];
				 //    window.addEventListener("popstate", function(e) {  
				 //        //alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能  
				        
				 //        })
				        
				        
				        
				        
					// }, false);  
				 //    function pushHistory() {  
				 //        var state = {  
				 //            title: "title",  
				 //            url: "#"  
				 //        };  
				 //        window.history.pushState(state, "title", "#");  
				 //    }  
				      
				

				// function creatArray(){
				// 	$(".wrap>div").each(function(index,element){
				// 		var arrayIn=[];
				// 		var arrayOut=[];
				// 		$(this).data("in",arrayIn);
				// 		$(this).data("out",arrayOut);
				// 	})
				// }

				//			}
			})
		</script>

</body>
</html>
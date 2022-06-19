

<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Chef</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap-readable-theme.css" />" rel="stylesheet">
    <style type="text/css">
    	
    	.b-example-divider {
		  height: 3rem;
		  background-color: rgba(0, 0, 0, .1);
		  border: solid rgba(0, 0, 0, .15);
		  border-width: 1px 0;
		  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
		}
		
		.bi {
		  vertical-align: -.125em;
		  fill: currentColor;
		}
		
		.feature-icon {
		  display: inline-flex;
		  align-items: center;
		  justify-content: center;
		  width: 4rem;
		  height: 4rem;
		  margin-bottom: 1rem;
		  font-size: 2rem;
		  color: #fff;
		  border-radius: .75rem;
		}
		
		.icon-link {
		  display: inline-flex;
		  align-items: center;
		}
		.icon-link > .bi {
		  margin-top: .125rem;
		  margin-left: .125rem;
		  transition: transform .25s ease-in-out;
		  fill: currentColor;
		}
		.icon-link:hover > .bi {
		  transform: translate(.25rem);
		}
		
		.icon-square {
		  display: inline-flex;
		  align-items: center;
		  justify-content: center;
		  width: 3rem;
		  height: 3rem;
		  font-size: 1.5rem;
		  border-radius: .75rem;
		}
		
		.rounded-4 { border-radius: .5rem; }
		.rounded-5 { border-radius: 1rem; }
		
		.text-shadow-1 { text-shadow: 0 .125rem .25rem rgba(0, 0, 0, .25); }
		.text-shadow-2 { text-shadow: 0 .25rem .5rem rgba(0, 0, 0, .25); }
		.text-shadow-3 { text-shadow: 0 .5rem 1.5rem rgba(0, 0, 0, .25); }
		
		.card-cover {
		  background-repeat: no-repeat;
		  background-position: center center;
		  background-size: cover;
		}
    	
    </style>
  
</head>
<body onload="connect()">
<h1>Chef</h1>
<div>
            <div id="conversationDiv">
                <p id="response"></p>
            </div>
</div>

<div class="container">
	
	<c:choose>
	    <c:when test="${empty orders}">
	       <p> Currently no order is placed </p>
	    </c:when>    
	    <c:otherwise>
	    	
	    	<div class="row">
          
					<c:forEach items="${orders}" var="order">
						<div class="col-lg-4 col-md-6 mb-4">
			              <div class="card h-100">
			                <h4 class="card-title">Order Number ${order.orderId}</h4>
			                <div class="card-body">
			                  <ol class="list-group list-group-numbered">
					          <c:forEach items="${order.items}" var="item">
								  <li class="list-group-item d-flex justify-content-between align-items-start">
								    <div class="ms-2 me-auto">
								      <div class="fw-bold">${item.title}</div>
								    </div>
								    <span class="badge bg-primary rounded-pill">${item.count}</span>
								  </li>
								 </c:forEach>
								</ol>			                  
			                </div>
			                <div class="card-footer">
									<button type="submit"><a href="<spring:url value="/chef/order/${order.orderId}"/>">Order Ready</a></button>
			                </div>
			              </div>
			            </div>
					</c:forEach>
	
	          </div>
	    </c:otherwise>
	</c:choose>
	
</div>


<script src="<c:url value="/resources/js/sockjs.min.js" />"></script>
		  <script src="<c:url value="/resources/js/stomp.min.js" />"></script>
		   <script src="<c:url value="/resources/js/notification.js" />"></script>
</body>
</html> 


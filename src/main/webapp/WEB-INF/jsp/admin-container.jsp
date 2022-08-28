         <button type="button" class="btn btn-primary create-new-item" data-toggle="modal" data-target="#ModalLoginForm">
		    + Add New Item
		</button>
         
         <table class="table table-striped table-hover table-users">
    			<thead>
    				<tr>
					      <th scope="col">Name</th>
					      <th scope="col">Price</th>
					      <th scope="col">Summary</th>
					      <th scope="col">Image</th>
					      <th scope="col">Category</th>
    				</tr>
    			</thead>

    			<tbody>
    			
    			<c:forEach items="${adminviews}" var="adminView">
    				<tr>
                        
				      <td>${adminView.title}</td>
				      <td>${adminView.price}</td>
				      <td>${adminView.summary}</th>
				      <td>${adminView.img}</td>
				      <td>${adminView.menuName}</td>
                    	  					
    					<td><a class="btn mini blue-stripe" href="#">Edit</a></td>

                        <td><a href="/admin/item/delete/${adminView.itemId}" class="confirm-delete btn mini red-stripe" role="button" data-title="johnny" data-id="1">Delete</a></td>
                    </tr>
    			</c:forEach>
                
	               </tbody>

    		</table>



<%@include file="./add-new-item-modal.jsp"%>




<%-- 

<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

		<link href="<c:url value="/resources/css/bootstrap-readable-theme.css" />" rel="stylesheet">
    </head>
    <body >
        <table class="table table-hover">
			  <thead>
			    <tr>
			      <th scope="col">ItemId</th>
			      <th scope="col">Name</th>
			      <th scope="col">Price</th>
			      <th scope="col">Type</th>
			      <th scope="col">Summary</th>
			      <th scope="col">Image</th>
			      <th scope="col">Menu Id</th>
			      <th scope="col">Category</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${adminviews}" var="adminView">
			    <tr>
			      <th scope="row">${adminView.itemId}</th>
			      <td>${adminView.title}</td>
			      <td>${adminView.price}</td>
			      <td>${adminView.type}</td>
			      <td>${adminView.summary}</th>
			      <td>${adminView.img}</td>
			      <td>${adminView.menuId}</td>
			      <td>${adminView.menuName}</td>
			    </tr>
			    </c:forEach>
			  </tbody>
			</table>
        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		  <script src="<c:url value="/resources/js/popper.min.js" />"></script>
		   <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    </body>
</html>
 --%>
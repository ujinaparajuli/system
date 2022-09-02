         <button type="button" class="btn btn-link create-new-item" data-toggle="modal" data-target="#ModalLoginForm">
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
				      
				      <td><button type="button" class="btn btn-link" data-toggle="modal" 
				      				data-target="#ModalEditForm" 
				      				data-id="${adminView.itemId}" 
				      				data-title="${adminView.title}" 
				      				data-price="${adminView.price}" 
				      				data-summary="${adminView.summary}" 
				      				data-menu="${adminView.menuName}" 
				      				data-img="${adminView.img}">Edit</button>
                    	  					
                       <td>
                       <button type="button" class="btn btn-link" data-toggle="modal" data-target="#deleteItemModal" data-id="${adminView.itemId}">Delete</button></td>
                    </tr>
    			</c:forEach>
                
	               </tbody>

    		</table>



<%@include file="./add-new-item-modal.jsp"%>
<%@include file="./edit-item-modal.jsp"%>
<%@include file="./delete-item-modal.jsp"%>
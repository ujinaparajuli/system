<div id="ModalLoginForm" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title">Add Item</h1>
            </div>
            <div class="modal-body">
                <form:form role="form" method="POST" action="/admin/add/item" modelAttribute="addItem" enctype="multipart/form-data">
                    <input type="hidden" name="_token" value="">
                    <div class="form-group">
                        <form:label class="control-label" path="title">Item Name</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="name" path="title" value="" />
           
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="summary">Summary/Description</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="summary" path="summary" value=""></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="menuName">Category</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="category" path="menuName"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="img">Image Name</form:label>
                        <div>
                            <form:input type="file" class="form-control input-lg" name="img" path="img"></form:input>
                        </div>                        
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="price">Price</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="price" path="price"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <button type="submit" class="btn btn-success">
                                Add
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
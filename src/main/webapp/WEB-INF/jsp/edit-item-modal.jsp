<div id="ModalEditForm" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title">Edit Item</h1>
            </div>
            <div class="modal-body">
                <form:form role="form" method="POST" class="edit-item-form" modelAttribute="addItem">
                    <input type="hidden" name="_token" value="">
                    <div class="form-group">
                        <form:label class="control-label" path="title">Item Name</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="name" id="title" path="title" />
           
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="summary">Summary/Description</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="summary" id="summary" path="summary" ></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="menuName">Category</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="category" id="menu" path="menuName"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="img">Image Name</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="img" id="img" path="img"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="price">Price</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="price" id="price" path="price"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <button type="submit" class="btn btn-success">
                                Update
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="false" %>

<h1>
	user 등록
</h1>

<form action="${pageContext.request.contextPath }/index" method="post">
	<div class="box-body">
		<div class="form-group">
			<label>name</label> <input type="text" name="name" class="form-control"/>
		</div>
		<div class="form-group">
			<label>age</label><input type="text" class="form-control" name="age"/>
				
		</div>
		<div class="form-group">
			<label>hobby</label><input type="text" name="hobby" class="form-control" />
		</div>
		
	</div>
	<div class="box=footer">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>
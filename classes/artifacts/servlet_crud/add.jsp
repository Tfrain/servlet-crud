<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<meta charset="utf-8">
		<title>学生管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	</head>

	<body style="padding-top: 20px;">
		<div class="container">
			<div class="col-md-8 col-md-offset-2">
				<h1>添加学生</h1><br>
				<form action="add" method="post">
					<div class="form-group">
						<label>学生编号</label>
						<input type="text" class="form-control" name="studentNumber">
					</div>
					<div class="form-group">
						<label>学生姓名</label>
						<input type="text" class="form-control" name="studentName">
					</div>
					<div class="form-group">
						<label>学生密码</label>
						<input type="password" class="form-control" name="studentPassword">
					</div>
					<div class="form-group">
						<label class="radio-inline">
								  <input type="radio" name="studentSex" value="1" checked="checked"> 男
								</label>
						<label class="radio-inline">
								  <input type="radio" name="studentSex" value="0"> 女
								</label>
					</div>
					<div class="form-group">
						<label class="radio-inline">
								  <input type="radio" name="studentDatabase" value="1" checked="checked"> 数据库 student
								</label>
						<label class="radio-inline">
								  <input type="radio" name="studentDatabase" value="2"> 数据库 newstudent
								</label>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-info">添加学生</button>
					</div>
				</form>
			</div>
		</div>
	</body>

</html>

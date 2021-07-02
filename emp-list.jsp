<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
            <title>Employee Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand">Employee Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${user != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${user != null}">
                            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>First Name</label> <input type="text" value="<c:out value='${user.fname}' />" class="form-control" name="fname" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Last Name</label> <input type="text" value="<c:out value='${user.lname}' />" class="form-control" name="lname">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Salary</label> <input type="text" value="<c:out value='${user.salary}' />" class="form-control" name="salary">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Department</label> <input type="text" value="<c:out value='${user.department}' />" class="form-control" name="dept" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Position</label> <input type="text" value="<c:out value='${user.position}' />" class="form-control" name="position" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Email Address</label> <input type="text" value="<c:out value='${user.emailid}' />" class="form-control" name="emailid" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Contact Number</label> <input type="text" value="<c:out value='${user.num}' />" class="form-control" name="num" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Picture</label> <input type="file" value="<c:out value='${user.pic}' />" class="form-control" name="pic" required="required">
                        </fieldset>
                    
                        <button type="submit" class="btn btn-success">Add Employee</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

</html>
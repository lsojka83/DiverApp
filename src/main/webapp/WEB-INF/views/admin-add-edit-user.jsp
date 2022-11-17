<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD Użytkowników</h1>

    </div>

<form:form action="/admin/addedituser" method="post" modelAttribute="user">
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Edycja</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                        <tbody>
                        <tr>
                            <td>Email</td>
                            <td>
                                <form:input path="email" type="email" name="email" placeholder="Email"  />
                                <form:errors path="email"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Nazwa</td>
                            <td>
                                <form:input path="username" type="text" name="username" placeholder="Nazwa"  />
                                <form:errors path="username"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Hasło</td>
                            <td>

                                <div class="form-group">
                                    <input type="password" name="password" placeholder="Podaj nowe hasło" />
                                </div>

                                <div class="form-group">
                                    <input type="password" name="password2" placeholder="Powtórz nowe hasło" />
                                </div>
                                <form:errors path="password"/>

                                <c:if test="${not empty invalidPassword}">
                                    ${invalidPassword}
                                </c:if>
                            </td>
                        </tr>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    <form:hidden path="id"/>

    <form:hidden path="roles"/>

    <form:hidden path="enabled"/>
    <form:hidden path="uuid"/>
    <form:hidden path="active"/>
    <form:hidden path="sentResetRequest"/>
    <form:hidden path="createdOn"/>

        <div class="text-center">
            <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit" name="confirm"
                    value="yes">Zapisz</button>
            <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit" name="confirm"
            value="no" formnovalidate>Zrezygnuj</button>
        </div>
</form:form>

</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@ include file="/WEB-INF/views/jspf/admin-footer.jspf" %>

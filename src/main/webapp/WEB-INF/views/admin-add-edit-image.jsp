<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD Obrazów</h1>
    </div>

    <form:form name="fileUpload" method="POST" action="/admin/addeditimage" modelAttribute="image" enctype="multipart/form-data">

     <form:input path="name" type="text"/>
     <label for="name">Nazwa obrazu</label>
     <form:errors path="name"/>
      <br>
    <label>Wybierz plik:</label> <br>
    <input type="file" name="multipartFile" class="btn btn-warning"/>
     <br>

    <form:hidden path="id"/>
    <form:hidden path="link"/>
    <form:hidden path="createdOn"/>

    <p></p>

                <button class="btn btn-success" type="submit" name="confirm"
                        value="yes">Zapisz</button>
                <button class="btn btn-danger" type="submit" name="confirm"
                value="no" formnovalidate>Zrezygnuj</button>

    </form:form>




</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@ include file="/WEB-INF/views/jspf/admin-footer.jspf" %>

